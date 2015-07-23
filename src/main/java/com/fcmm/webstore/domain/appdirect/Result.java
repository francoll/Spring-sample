package com.fcmm.webstore.domain.appdirect;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
	private boolean success = true;
	private String message = null;
	private String errorCode = null;
	private String accountIdentifier = null;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean pSuccess) {
		success = pSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String pMessage) {
		message = pMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String pErrorCode) {
		errorCode = pErrorCode;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String pAccountIdentifier) {
		accountIdentifier = pAccountIdentifier;
	}
	
	
}
