package com.fcmm.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.exception.AccountNotFoundException;
import com.fcmm.webstore.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;

	@RequestMapping
	public String list(Model pModel) {
		pModel.addAttribute("accounts", accountService.getAllAccounts());
		return "accounts";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/registrationOpenid")
	public String registrationOpenId(HttpServletRequest request, Model pModel) {
		String userId = (String) request.getSession().getAttribute("USER_OPENID_CREDENTIAL");
		if (userId != null) {
			Account newAccount;
			try {
				newAccount = accountService.getAccountByOpenid(userId);
				
				pModel.addAttribute("account", newAccount);
				return "redirect:openIdConfirm";
				
			} catch (AccountNotFoundException e) {
				logger.error(e);
				pModel.addAttribute("message", "Please register using your OpenID.");
				return "redirect:login";
			}
			
		} else {
			pModel.addAttribute("message", "Please register using your OpenID.");
			return "redirect:login";
		}
	}
}
