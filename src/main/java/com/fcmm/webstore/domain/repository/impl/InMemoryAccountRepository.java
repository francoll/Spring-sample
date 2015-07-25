package com.fcmm.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.domain.repository.AccountRepository;
import com.fcmm.webstore.exception.AccountNotFoundException;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

	private List<Account> listOfAccounts = new ArrayList<Account>();
	
	public InMemoryAccountRepository() {
		Account me = new Account("the_boss", "HYPOTHETICAL EDITION");
		me.setfName("Preset");
		me.setlName("Admin");
		me.setUsername("admin");
		me.setPassword("admin");
		me.setEnabled(true);
		me.setOpenId("https://www.appdirect.com/openid/id/04f29118-a8b9-4539-9e44-4ac872188bbf");
		me.addRole(new SimpleGrantedAuthority("ROLE_ADMIN"));

		listOfAccounts.add(me);
	}
	
	@Override
	public List<Account> getAllAccounts() {
		return listOfAccounts;
	}

	@Override
	public Account getAccountById(String pAccountId) throws AccountNotFoundException {
		Account AccountById = null;

		for (Account Account : listOfAccounts) {
			if (Account != null && Account.getAccountId() != null 
					&& Account.getAccountId().equals(pAccountId)) {
				AccountById = Account;
				break;
			}
		}

		if (AccountById == null) {
			throw new AccountNotFoundException("No Account found with the Account id: " + pAccountId);
		}

		return AccountById;
	}
	
	@Override
	public Account getAccountByUuid(String pUUID) throws AccountNotFoundException {
		Account AccountById = null;

		for (Account Account : listOfAccounts) {
			if (Account != null && Account.getUuid() != null 
					&& Account.getUuid().equals(pUUID)) {
				AccountById = Account;
				break;
			}
		}

		if (AccountById == null) {
			throw new AccountNotFoundException("No Account found with the UUID: " + pUUID);
		}

		return AccountById;
	}
	
	@Override
	public void addAccount(Account pAccount) {
		listOfAccounts.add(pAccount);
	}

	@Override
	public Account getAccountByOpenid(String pOpenId){
		Account AccountById = null;

		for (Account Account : listOfAccounts) {
			if (Account != null && Account.getOpenId() != null 
					&& Account.getOpenId().equals(pOpenId)) {
				AccountById = Account;
				break;
			}
		}

		return AccountById;
	}

	@Override
	public Account getAccountByUsername(String pUsername) {
		Account AccountByName = null;

		for (Account Account : listOfAccounts) {
			if (Account != null && Account.getUsername() != null 
					&& Account.getUsername().equals(pUsername)) {
				AccountByName = Account;
				break;
			}
		}

		return AccountByName;
	}

}
