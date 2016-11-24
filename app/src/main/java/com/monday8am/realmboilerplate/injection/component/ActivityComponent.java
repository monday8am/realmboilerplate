package com.monday8am.realmboilerplate.injection.component;

import com.monday8am.realmboilerplate.injection.PerActivity;
import com.monday8am.realmboilerplate.injection.module.ActivityModule;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    // TODO: Review how subcomponents work!
    //void inject (MainActivity activity);
}
