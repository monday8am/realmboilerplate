package com.monday8am.realmboilerplate.injection.component;

import com.monday8am.realmboilerplate.injection.module.AppModule;
import com.monday8am.realmboilerplate.injection.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anton on 20/11/2016.
 */

@Singleton
@Component (modules = {AppModule.class, NetModule.class})
public interface NetComponent {

}
