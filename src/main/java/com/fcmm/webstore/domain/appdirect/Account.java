package com.fcmm.webstore.domain.appdirect;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Account implements UserDetails{
	

	private static final long serialVersionUID = -5012843367210793611L;
	
	private String accountId = null;
	private String username = null;
	private String password = null;
	private String fName = null;
	private String lName = null;
	private String edition = null;
	private String email = null;
	private String uuid = null;
	private String openId = null;
	private boolean enabled = false;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
	
	public Account(String pAccountId) {
		accountId = pAccountId;
	}
	
	public Account(String pAccountId, String pEdition) {
		accountId = pAccountId;
		edition = pEdition;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String pAccountId) {
		accountId = pAccountId;
	}
	
	public String getEdition() {
		return edition;
	}

	public void setEdition(String pEdition) {
		edition = pEdition;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String pFName) {
		fName = pFName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String pLName) {
		lName = pLName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String pOpenId) {
		openId = pOpenId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String pEmail) {
		email = pEmail;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String pUuid) {
		uuid = pUuid;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String pPassword) {
		password = pPassword;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String pUsername) {
		username = pUsername;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean pEnabled) {
		enabled = pEnabled;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean pAccountNonLocked) {
		accountNonLocked = pAccountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean pAccountNonExpired) {
		accountNonExpired = pAccountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean pCredentialsNonExpired) {
		credentialsNonExpired = pCredentialsNonExpired;
	}
	
	public void addRole(SimpleGrantedAuthority pRole){
		roles.add(pRole);
	}
	
	public void removeRole(SimpleGrantedAuthority pRole){
		roles.remove(pRole);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public boolean equals(Object pObj) {
		if (this == pObj)
			return true;
		if (pObj == null)
			return false;
		if (getClass() != pObj.getClass())
			return false;
		Account other = (Account) pObj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + "]";
	}

}
