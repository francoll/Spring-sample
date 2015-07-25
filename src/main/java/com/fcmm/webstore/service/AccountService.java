package com.fcmm.webstore.service;

import java.util.List;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.exception.AccountNotFoundException;

public interface AccountService{
	
	List <Account> getAllAccounts();
	
	Account getAccountById(String pAccountID) throws AccountNotFoundException;
	
	void addAccount(Account pAccount);

	Account getAccountByUuid(String pUUID) throws AccountNotFoundException;
	
	Account getAccountByOpenid(String pOpenId) throws AccountNotFoundException;

	Account getAccountByUserName(String pUserName);
}
