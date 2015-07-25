package com.fcmm.webstore.domain.repository;

import java.util.List;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.exception.AccountNotFoundException;

public interface AccountRepository {
	List <Account> getAllAccounts();
	
	Account getAccountById(String pAccountID) throws AccountNotFoundException;
	Account getAccountByUuid(String pUUID) throws AccountNotFoundException;
	Account getAccountByOpenid(String pOpenId);
	Account getAccountByUsername(String pUsername);
	
	void addAccount(Account pAccount);
}
