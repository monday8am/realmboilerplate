package com.monday8am.realmboilerplate.injection.component;

import android.content.Context;

import com.monday8am.realmboilerplate.injection.ApplicationContext;
import com.monday8am.realmboilerplate.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Standard Application scoped component.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

}
