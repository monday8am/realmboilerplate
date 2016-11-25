package com.monday8am.realmboilerplate.ui.main;

import com.monday8am.realmboilerplate.data.model.NYTimesStory;
import com.monday8am.realmboilerplate.ui.base.MvpView;

import java.util.List;

/**
 * Contract between MainActivity and MainPresenter.
 */

public interface MainContract {

    interface Presenter  {

        void refreshList();

        void listItemSelected (int itemPosition);

        void titleSpinnerSectionSelected(String sectionName);
    }

    interface View extends MvpView {

        void showList(List<NYTimesStory> items);

        void configureToolbar(List<String> sections);

        void hideRefreshing();

        void showNetworkLoading(Boolean networkInUse);
    }
}
