package com.ayouris.nawat.controller.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		handle(request, response, authentication);

		System.out.println("onAuthenticationSuccess.Fired");
		System.out.println(this.getClass().toString() + " :  Connected user username : " + authentication.getName());

		System.out.println("CustomSuccessHandler : " + authentication.getDetails());
		// getSessionManager().setCurrentUser(null);
		clearAuthenticationAttributes(request);

	}

}
