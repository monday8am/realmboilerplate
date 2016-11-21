package com.monday8am.realmboilerplate;

import android.app.Application;
import android.content.Context;

import com.monday8am.realmboilerplate.injection.component.AppComponent;
import com.monday8am.realmboilerplate.injection.component.DaggerAppComponent;
import com.monday8am.realmboilerplate.injection.component.DaggerNetComponent;
import com.monday8am.realmboilerplate.injection.component.NetComponent;
import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.injection.module.NetModule;

import timber.log.Timber;

public class RealmBoilerplateApp extends Application {

    private NetComponent mNetComponent;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //Fabric.with(this, new Crashlytics());
        }

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public static RealmBoilerplateApp get(Context context) {
        return (RealmBoilerplateApp) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
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
