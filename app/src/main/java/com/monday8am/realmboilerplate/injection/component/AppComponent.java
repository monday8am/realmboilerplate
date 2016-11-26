package com.monday8am.realmboilerplate.injection.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.monday8am.realmboilerplate.data.DataManager;
import com.monday8am.realmboilerplate.data.local.PreferencesHelper;
import com.monday8am.realmboilerplate.data.local.RealmDatabaseHelper;
import com.monday8am.realmboilerplate.data.remote.NYTimesService;
import com.monday8am.realmboilerplate.injection.ApplicationContext;
import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.ui.base.BaseActivity;
import com.monday8am.realmboilerplate.util.RxEventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Standard Application scoped component.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    // exported for child-components
    Application application();
    @ApplicationContext Context context();
    RealmDatabaseHelper realmDatabaseHelper();
    PreferencesHelper preferencesHelper();
    NYTimesService nyTimesService ();
    DataManager dataManager();
    RxEventBus eventBus();
}
