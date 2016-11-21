package com.monday8am.realmboilerplate.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 * More info about @Scope: http://docs.oracle.com/javaee/6/api/javax/inject/Scope.html
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
