package com.fcmm.webstore.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final String DEFAULT_URL = "https://www.appdirect.com/openid/id";
	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="openid", required = false) String pOpenIdUrl, Model pModel) {
		if (pOpenIdUrl != null){
			logger.info("Received " + pOpenIdUrl);
			pModel.addAttribute("openId_url", pOpenIdUrl);
		}
		else{
			pModel.addAttribute("openId_url", DEFAULT_URL);
		}
		
		return "login";
	}

	@RequestMapping(value = "/spring_security_login", method = RequestMethod.GET)
	public String openIdLogin(@RequestParam(value="continue", required = false) String pDestUrl, Model pModel) {
		if (pDestUrl != null)
		{
			return pDestUrl;
		}
		return "accounts";
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model pModel) {
		pModel.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model pModel) {
		return "welcome";
	}
}
