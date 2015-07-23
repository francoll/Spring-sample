package com.fcmm.webstore.controller;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcmm.webstore.domain.appdirect.Result;

@Controller
@RequestMapping(value = "appdirect")
public class AppDirectRestController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "createSub", method = RequestMethod.GET)
	public @ResponseBody Result createSubscription(@RequestParam("url") String pUrl) {
		
		logger.info("Received " + pUrl);
		
		Result result = new Result();
		result.setAccountIdentifier(UUID.randomUUID().toString());
		result.setMessage("Account creation successful");
		return result;
	}
	
	@RequestMapping(value = "changeSub", method = RequestMethod.GET)
	public @ResponseBody Result changeSubscription(@RequestParam("url") String pUrl) {
		
		logger.info("Received " + pUrl);
		
		Result result = new Result();
		result.setAccountIdentifier(UUID.randomUUID().toString());
		result.setMessage("Account change successful");
		return result;
	}
	
}
