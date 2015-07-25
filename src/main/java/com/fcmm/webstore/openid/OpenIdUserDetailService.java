package com.fcmm.webstore.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fcmm.webstore.domain.appdirect.Account;
import com.fcmm.webstore.exception.AccountNotFoundException;
import com.fcmm.webstore.service.AccountService;

@Service
public class OpenIdUserDetailService implements UserDetailsService {

	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String pOpenId) {
        Account userAccount = null;

        try {
        	userAccount = accountService.getAccountByUserName(pOpenId);
        	if (userAccount == null){
        		userAccount = accountService.getAccountByOpenid(pOpenId);
        	}
			
		} catch (AccountNotFoundException e) {
			throw new UsernameNotFoundException(pOpenId);
		}
        if (!userAccount.isEnabled()) {
            throw new DisabledException("User is disabled");
        }
        return userAccount;
    }
}