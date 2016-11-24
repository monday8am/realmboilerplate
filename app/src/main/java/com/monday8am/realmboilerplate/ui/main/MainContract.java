package com.monday8am.realmboilerplate.ui.main;

import com.monday8am.realmboilerplate.ui.base.MvpView;

/**
 * Specific contract between MainActivity and MainPresenter.
 */

public interface MainContract {

    interface View extends MvpView {

    }

    interface Presenter extends com.monday8am.realmboilerplate.ui.base.Presenter<MvpView> {

    }
}
