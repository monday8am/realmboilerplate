package com.monday8am.realmboilerplate.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.monday8am.realmboilerplate.data.local.RealmDatabaseHelper;
import com.monday8am.realmboilerplate.data.remote.NYTimesService;
import com.monday8am.realmboilerplate.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Standard Application scoped module.
 */

@Module
public class ApplicationModule {

    private String mBaseUrl;
    private Application mApplication;

    public ApplicationModule(Application application, String baseUrl) {
        mApplication = application;
        this.mBaseUrl = baseUrl;
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
        Realm.init(application);
        RealmConfiguration conf = new RealmConfiguration.Builder().build();
        return new RealmDatabaseHelper(conf);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    NYTimesService providesNYTimesService (Retrofit retrofit) {
        return retrofit.create(NYTimesService.class);
    }
}
