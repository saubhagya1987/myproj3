package com.versawork.http.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.versawork.http.controller.ActivityLogController;
import com.versawork.http.controller.LoginController;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.service.ActivityLogService;
import com.versawork.http.utils.Activity;

@Component
public class SessionFilter implements Filter {

	@Autowired
	MessageSource sessionConfigMessageSource;

	@Autowired
	ActivityLogController activityLogController;

	@Autowired
	Gson gson;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

		HttpServletRequest request = null;
		HttpServletResponse response = null;
		/*
		 * LOGGER.debug("!!!!!! Production is set to : " +
		 * sessionConfigMessageSource.getMessage("Production", null,
		 * Locale.getDefault()));
		 */
		String production = sessionConfigMessageSource.getMessage("Production", null, Locale.getDefault());
		try {
			if (production.equalsIgnoreCase("true")) {
				request = (HttpServletRequest) req;
				response = (HttpServletResponse) res;

				HttpSession session = request.getSession();

				if (session.getAttribute("Id") != null
						|| session.getAttribute("Id").toString().equalsIgnoreCase(session.getId())) {
					LOGGER.debug("Valid session");
					chain.doFilter(req, res);
				} else {
					LOGGER.debug("invalid session");
					// response.sendError(401, "Authentication Failed");

					NsRequest nsRequest = gson.fromJson(req.getReader(), NsRequest.class);
					activityLogController.saveActivityLog(nsRequest, null, Activity.SESSION_EXPIRE);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} else {
				chain.doFilter(req, res);
			}
		} catch (Exception e) {
			// response.sendError(401, "Authentication Failed");
			//LOGGER.error("Error in Session Filter  " + ExceptionUtils.getFullStackTrace(e));
			NsRequest nsRequest = gson.fromJson(req.getReader(), NsRequest.class);
			activityLogController.saveActivityLog(nsRequest, null, Activity.SESSION_EXPIRE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}