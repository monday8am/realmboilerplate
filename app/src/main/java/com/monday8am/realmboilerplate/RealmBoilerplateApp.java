package com.monday8am.realmboilerplate;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.monday8am.realmboilerplate.injection.component.ApplicationComponent;
import com.monday8am.realmboilerplate.injection.component.DaggerApplicationComponent;
import com.monday8am.realmboilerplate.injection.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import timber.log.Timber;

public class RealmBoilerplateApp extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }

        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, "http://api.nytimes.com/"))
                .build();

        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                super.handleError(e);
                Timber.e(e.toString());
            }
        });
    }

    public static RealmBoilerplateApp get(Context context) {
        return (RealmBoilerplateApp) context.getApplicationContext();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }
}
