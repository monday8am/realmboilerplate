package com.monday8am.realmboilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import com.monday8am.realmboilerplate.data.local.RealmDatabaseHelper;
import com.monday8am.realmboilerplate.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

/**
 * Standard Application scoped module.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RealmDatabaseHelper provideRealmDatabase(Application application) {
        RealmConfiguration conf = new RealmConfiguration.Builder().build();
        return new RealmDatabaseHelper(application, conf);
    }
}
