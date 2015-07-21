package com.fcmm.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;

	public boolean preHandle(HttpServletRequest pRequest, HttpServletResponse pResponse, Object pHandler)
			throws Exception {
		String givenPromoCode = pRequest.getParameterValues("promo") == null ? ""
				: pRequest.getParameterValues("promo")[0];

		if (pRequest.getRequestURI().endsWith("products/specialOffer")) {
			if (givenPromoCode.equals(promoCode)) {
				pResponse.sendRedirect(pRequest.getContextPath() + "/" + offerRedirect);
			} else {
				pResponse.sendRedirect(errorRedirect);
			}
			return false;
		}

		return true;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String pPromoCode) {
		this.promoCode = pPromoCode;
	}

	public String getErrorRedirect() {
		return errorRedirect;
	}

	public void setErrorRedirect(String pErrorRedirect) {
		this.errorRedirect = pErrorRedirect;
	}

	public String getOfferRedirect() {
		return offerRedirect;
	}

	public void setOfferRedirect(String pOfferRedirect) {
		this.offerRedirect = pOfferRedirect;
	}
}
