package com.monday8am.realmboilerplate.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Qualifier to identify activity scoped providers.
 * More info about @Qualifier: http://docs.oracle.com/javaee/6/api/javax/inject/Qualifier.html
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
