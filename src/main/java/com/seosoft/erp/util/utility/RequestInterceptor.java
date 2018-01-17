package com.seosoft.erp.util.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class RequestInterceptor implements Filter {

    @Override
    public void init(FilterConfig config) {
        // Initialize global variables if necessary.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Do your job here which should run before the request processing.
    	System.out.println("------------------------------------------------------------");
    	System.out.println("REQUEST INTERCEPTOR :" + request);
    	System.out.println("------------------------------------------------------------");
        chain.doFilter(request, response);
        // Do your job here which should run after the request processing.
    }

    @Override
    public void destroy() {
        // Cleanup global variables if necessary.
    }

}