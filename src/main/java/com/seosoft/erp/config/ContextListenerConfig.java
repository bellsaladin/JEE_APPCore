package com.seosoft.erp.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.omnifaces.util.Messages;

import com.seosoft.erp.util.utility.Utils;

@WebListener
public class ContextListenerConfig implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
		System.setProperty("org.apache.el.parser.SKIP_IDENTIFIER_CHECK", "true");

		Messages.setResolver(new Messages.Resolver() {
			@Override
			public String getMessage(String message, Object... params) {
				return Utils.getBundleByKey(message, params);
			}
		});
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// NOOP
	}

}