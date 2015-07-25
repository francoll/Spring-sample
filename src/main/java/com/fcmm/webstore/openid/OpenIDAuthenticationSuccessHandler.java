package com.fcmm.webstore.openid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class OpenIDAuthenticationSuccessHandler extends

SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		// provide implementation to set user data in session

		// redirecting to landing page
		getRedirectStrategy().sendRedirect(request, response, "/accounts");
		super.onAuthenticationSuccess(request, response, authentication);

	}
}
