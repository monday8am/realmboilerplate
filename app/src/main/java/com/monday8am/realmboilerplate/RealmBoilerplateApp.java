package com.monday8am.realmboilerplate;

import android.app.Application;

import com.monday8am.realmboilerplate.injection.component.DaggerNetComponent;
import com.monday8am.realmboilerplate.injection.component.NetComponent;
import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.injection.module.NetModule;

/**
 * Created by anton on 20/11/2016.
 */

public class RealmBoilerplateApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
