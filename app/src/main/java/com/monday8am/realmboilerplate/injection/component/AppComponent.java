package com.monday8am.realmboilerplate.injection.component;

import android.content.Context;

import com.monday8am.realmboilerplate.injection.ApplicationContext;
import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.ui.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Standard Application scoped component.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

    @ApplicationContext
    Context context();

}
