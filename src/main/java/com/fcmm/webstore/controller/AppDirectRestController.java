package com.fcmm.webstore.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.domain.appdirect.Result;
import com.fcmm.webstore.exception.AccountNotFoundException;
import com.fcmm.webstore.service.AccountService;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

@Controller
@RequestMapping(value = "appdirect")
public class AppDirectRestController {

	private static final String key = "testapp-31275";
	private static final String secret = "ATvHW3VeWHcaiz1k";

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "createSub", method = RequestMethod.GET)
	public @ResponseBody Result createSubscription(@RequestParam("url") String pUrl, 
			HttpServletRequest pRequest, HttpServletResponse pResponse) {

		logger.info("Received " + pUrl);
		String event = null;
		String appDirectId = null;
		String editionCode = null;
		Account account = null;
		Result result = new Result();

		if (!verify(pRequest)){
			try {
				pResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				return null;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		try {
			// get event details
			event = doHttpUrlConnectionAction(pUrl);
			logger.info("event response: " + event);
			Element root = getRoot(event);
			
			Element creator = root.getFirstChildElement("creator");
			appDirectId = creator.getFirstChildElement("uuid").getValue();
			editionCode = root.getFirstChildElement("payload").getFirstChildElement("order")
					.getFirstChildElement("editionCode").getValue();
			
			try{
				// Should always fail, but just in case
				account = accountService.getAccountByUuid(appDirectId);
			}
			catch (AccountNotFoundException anfe){
				// as expected, we need to create the account
				account = new Account(UUID.randomUUID().toString());
				account.setfName(creator.getFirstChildElement("firstName").getValue());
				account.setlName(creator.getFirstChildElement("lastName").getValue());
				account.setEmail(creator.getFirstChildElement("email").getValue());
				account.setUuid(creator.getFirstChildElement("uuid").getValue());
				account.setOpenId(creator.getFirstChildElement("openId").getValue());
				
				accountService.addAccount(account);
			}
			
			account.setEdition(editionCode);
			
			result.setAccountIdentifier(account.getAccountId());
			result.setMessage("Account creation successful");

		} catch (Exception e) {
			createParseError(result, e);
		}
		
		return result;
	}

	@RequestMapping(value = "changeSub", method = RequestMethod.GET)
	public @ResponseBody Result changeSubscription(@RequestParam("url") String pUrl) {

		String event = null;
		String accountId = null;
		String editionCode = null;
		Account account = null;
		Result result = new Result();
		
		logger.info("Received " + pUrl);
		
		try {
			// get event details
			event = doHttpUrlConnectionAction(pUrl);
			logger.info("event response: " + event);
			Element root = getRoot(event);
			Element payload = root.getFirstChildElement("payload");
			accountId = payload.getFirstChildElement("account")
					.getFirstChildElement("accountIdentifier").getValue();
			editionCode = payload.getFirstChildElement("order")
					.getFirstChildElement("editionCode").getValue();			
			try{
				account = accountService.getAccountById(accountId);
			}
			catch (AccountNotFoundException anfe){
				result.setSuccess(false);
				result.setErrorCode("ACCOUNT_NOT_FOUND");
				result.setMessage("Account " + accountId + " not found");
				return result;
			}
			
			account.setEdition(editionCode);
			
		} catch (Exception e) {
			createParseError(result, e);
		}
		
		result.setAccountIdentifier(accountId);
		result.setMessage("Account change successful");
		return result;
	}
	
	@RequestMapping(value = "cancelSub", method = RequestMethod.GET)
	public @ResponseBody Result cancelSubscription(@RequestParam("url") String pUrl, 
			HttpServletRequest pRequest, HttpServletResponse pResponse) {

		logger.info("Received " + pUrl);
		String event = null;
		String accountId = null;
		Account account = null;
		Result result = new Result();

		if (!verify(pRequest)){
			try {
				pResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				return null;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		try {
			// get event details
			event = doHttpUrlConnectionAction(pUrl);
			logger.info("event response: " + event);
			Element root = getRoot(event);
			
			accountId = root.getFirstChildElement("payload").getFirstChildElement("account")
					.getFirstChildElement("accountIdentifier").getValue();
			
			try{
				account = accountService.getAccountById(accountId);
			}
			catch (AccountNotFoundException anfe){
				result.setSuccess(false);
				result.setErrorCode("ACCOUNT_NOT_FOUND");
				result.setMessage("Account " + accountId + " not found");
				return result;
			}
			
			account.setEdition("**CANCELLED**");
			
			result.setAccountIdentifier(accountId);
			result.setMessage("Account cancel successful");

		} catch (Exception e) {
			createParseError(result, e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "status", method = RequestMethod.GET)
	public @ResponseBody Result updateStatus(@RequestParam("url") String pUrl) {

		String event = null;
		String accountId = null;
		String openId = null;
		String status = null;
		Account account = null;
		Result result = new Result();
		
		logger.info("Received " + pUrl);
		
		try {
			// get event details
			event = doHttpUrlConnectionAction(pUrl);
			logger.info("event response: " + event);
			Element root = getRoot(event);
			Element payload = root.getFirstChildElement("payload");
			accountId = payload.getFirstChildElement("account")
					.getFirstChildElement("accountIdentifier").getValue();
			status = payload.getFirstChildElement("account")
					.getFirstChildElement("status").getValue();
			try{
				account = accountService.getAccountById(accountId);
			}
			catch (AccountNotFoundException anfe){
				result.setSuccess(false);
				result.setErrorCode("ACCOUNT_NOT_FOUND");
				result.setMessage("Account " + accountId + " not found");
				return result;
			}
			
			account.setEdition(account.getEdition() + " - " + status);
			
		} catch (Exception e) {
			createParseError(result, e);
		}
		
		result.setAccountIdentifier(accountId);
		result.setMessage("Account change successful");
		return result;
	}
	
	private boolean verify(HttpServletRequest pRequest) {
		//Construct the message object. Use null for the URL and let the code construct it.
		OAuthMessage message= OAuthServlet.getMessage(pRequest, null);

		//Construct an accessor and a consumer
		net.oauth.OAuthConsumer consumer= new net.oauth.OAuthConsumer(null, key, secret, null);
		OAuthAccessor accessor= new OAuthAccessor(consumer);

		//Now validate. Weirdly, validator has a void return type. It throws exceptions
		//if there are problems.
		SimpleOAuthValidator validator=new SimpleOAuthValidator();
		
		try {
			validator.validateMessage(message,accessor);
		} catch (OAuthException e) {
			logger.error(e);
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	private Element getRoot(String event) throws ParsingException, ValidityException, IOException {
		Builder parser = new Builder();
		Document doc = parser.build(event, null);
		Element root = doc.getRootElement();
		
		return root;
	}

	private void createParseError(Result result, Exception e) {
		logger.error("Could not read event", e);
		result.setSuccess(false);
		result.setErrorCode("EVENT_READ_ERROR");
		result.setMessage("Could not fetch event details");
	}
	
	/**
	 * Sign the event URL and fetch details
	 * 
	 * @param desiredUrl
	 * @return String response
	 * @throws Exception
	 */
	private String doHttpUrlConnectionAction(String desiredUrl) throws Exception {
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			OAuthConsumer consumer = new DefaultOAuthConsumer(key, secret);

			// create the HttpURLConnection
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			consumer.sign(connection);

			// give it 15 seconds to respond
			connection.setReadTimeout(15 * 1000);
			connection.connect();

			// read the output from the server
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

}
