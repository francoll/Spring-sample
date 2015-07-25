package com.fcmm.webstore.exception;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 4387860734872063373L;
	private String AccountId;

	public AccountNotFoundException(String pAccountId) {
		this.AccountId = pAccountId;
	}

	public String getAccountId() {
		return AccountId;
	}

}
