package com.monday8am.realmboilerplate;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.monday8am.realmboilerplate.injection.component.AppComponent;
import com.monday8am.realmboilerplate.injection.component.DaggerAppComponent;
import com.monday8am.realmboilerplate.injection.component.DaggerNetComponent;
import com.monday8am.realmboilerplate.injection.component.NetComponent;
import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.injection.module.NetModule;

import io.fabric.sdk.android.Fabric;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import timber.log.Timber;

public class RealmBoilerplateApp extends Application {

    private NetComponent mNetComponent;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
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

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }
}
