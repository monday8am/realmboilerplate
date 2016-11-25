package com.monday8am.realmboilerplate.ui.main;

import android.support.annotation.NonNull;

import com.monday8am.realmboilerplate.data.DataManager;
import com.monday8am.realmboilerplate.data.model.NYTimesStory;
import com.monday8am.realmboilerplate.injection.ConfigPersistent;
import com.monday8am.realmboilerplate.ui.base.BaseMvpPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.realm.RealmResults;
import rx.Subscription;
import rx.functions.Action1;

/**
 * MvpPresenter class for controlling the Main Activity
 */

@ConfigPersistent
public class MainPresenter extends BaseMvpPresenter<MainContract.View>
        implements MainContract.Presenter {

    private final DataManager mDataManager;
    private List<NYTimesStory> mStoriesData;
    private Map<String, String> mSections;
    private MainContract.View mMvpView;

    private Subscription mLoaderSubscription;
    private Subscription mListDataSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(final MainContract.View mvpView) {
        super.attachView(mvpView);
        mMvpView = mvpView;

        mSections = mDataManager.getSections();

        // Sort mSections alphabetically, but always have Home at the top
        ArrayList<String> sectionList = new ArrayList<>(mSections.values());
        Collections.sort(sectionList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if (lhs.equals("Home")) return -1;
                if (rhs.equals("Home")) return 1;
                return lhs.compareToIgnoreCase(rhs);
            }
        });

        mvpView.configureToolbar(sectionList);

        mLoaderSubscription = mDataManager.isNetworkUsed()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean networkInUse) {
                        mvpView.showNetworkLoading(networkInUse);
                    }
                });

        sectionSelected(mDataManager.getCurrentSectionKey());
    }

    @Override
    public void detachView() {
        super.detachView();

        if (mLoaderSubscription != null) mLoaderSubscription.unsubscribe();
        if (mListDataSubscription != null) mListDataSubscription.unsubscribe();
    }

    @Override
    public void refreshList() {

    }

    @Override
    public void listItemSelected(int itemPosition) {
        mDataManager.reloadNewsFeed();
        mMvpView.hideRefreshing();
    }

    @Override
    public void titleSpinnerSectionSelected(String sectionName) {
        for (String key : mSections.keySet()) {
            if (mSections.get(key).equals(sectionName)) {
                sectionSelected(key);
                break;
            }
        }
    }

    private void sectionSelected(@NonNull String sectionKey) {
        mDataManager.setSelectedSection(sectionKey);
        if (mListDataSubscription != null) {
            mListDataSubscription.unsubscribe();
        }

        mListDataSubscription = mDataManager.getSelectedNewsFeed()
                .subscribe(new Action1<RealmResults<NYTimesStory>>() {
                    @Override
                    public void call(RealmResults<NYTimesStory> stories) {
                        mStoriesData = stories;
                        mMvpView.showList(stories);
                    }
                });
    }
}
