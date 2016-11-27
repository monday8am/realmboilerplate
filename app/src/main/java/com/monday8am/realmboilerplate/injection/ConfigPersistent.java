package com.monday8am.realmboilerplate.injection;

import com.monday8am.realmboilerplate.injection.component.ConfigPersistentComponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * {@link ConfigPersistentComponent}
 * More info about @Scope: http://docs.oracle.com/javaee/6/api/javax/inject/Scope.html
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigPersistent {
}