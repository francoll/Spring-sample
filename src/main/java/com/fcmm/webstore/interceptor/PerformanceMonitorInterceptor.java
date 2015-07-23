package com.fcmm.webstore.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PerformanceMonitorInterceptor implements HandlerInterceptor {

	ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();
	Logger logger = Logger.getLogger(this.getClass());

	public boolean preHandle(HttpServletRequest pRequest, HttpServletResponse pResponse, Object pHandler)
			throws Exception {
		StopWatch stopWatch = new StopWatch(pHandler.toString());
		stopWatch.start(pHandler.toString());
		stopWatchLocal.set(stopWatch);

		logger.info("Accessing URL path: " + getURLPath(pRequest));
		logger.info("Request content type: " + pRequest.getHeader("Accept"));
		logger.info("Request processing started on: " + getCurrentTime());
		return true;
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse pResponse, Object pHandler,
			ModelAndView pModelAndView) throws Exception {
		logger.info("Request processing ended on " + getCurrentTime());
	}

	public void afterCompletion(HttpServletRequest pRequest, HttpServletResponse pResponse, Object pHandler,
			Exception pException) throws Exception {
		StopWatch stopWatch = stopWatchLocal.get();
		stopWatch.stop();

		logger.info("Total time taken for processing: " + stopWatch.getTotalTimeMillis() + " ms");
		stopWatchLocal.set(null);
		logger.info("=======================================================");
	}

	private String getURLPath(HttpServletRequest pRequest) {
		String currentPath = pRequest.getRequestURI();
		String queryString = pRequest.getQueryString();
		queryString = queryString == null ? "" : "?" + queryString;
		return currentPath + queryString;
	}

	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}

}
