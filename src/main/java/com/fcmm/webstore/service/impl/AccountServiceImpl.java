package com.fcmm.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.domain.repository.AccountRepository;
import com.fcmm.webstore.exception.AccountNotFoundException;
import com.fcmm.webstore.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.getAllAccounts();
	}

	@Override
	public Account getAccountById(String pAccountID) throws AccountNotFoundException {
		return accountRepository.getAccountById(pAccountID);
	}

	@Override
	public void addAccount(Account pAccount) {
		accountRepository.addAccount(pAccount);
	}

	@Override
	public Account getAccountByUuid(String pUUID) throws AccountNotFoundException {
		return accountRepository.getAccountByUuid(pUUID);
	}

	@Override
	public Account getAccountByOpenid(String pOpenId){
		return accountRepository.getAccountByOpenid(pOpenId);
	}

	@Override
	public Account getAccountByUserName(String pUserName) {
		return accountRepository.getAccountByUsername(pUserName);
	}

}
