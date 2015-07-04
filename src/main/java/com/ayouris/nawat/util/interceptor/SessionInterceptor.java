package com.ayouris.nawat.util.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SessionInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -2936634715898026294L;

}
