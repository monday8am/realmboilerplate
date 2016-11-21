package com.monday8am.realmboilerplate.injection.module;

import android.app.Activity;
import android.content.Context;

import com.monday8am.realmboilerplate.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anton on 21/11/2016.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
