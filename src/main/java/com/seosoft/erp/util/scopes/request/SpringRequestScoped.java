package com.seosoft.erp.util.scopes.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/**
 * @author m.nikolaev Date: 21.11.12 Time: 1:01
 */
@Qualifier
@Scope("request")
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringRequestScoped {
}
