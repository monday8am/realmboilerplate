package com.monday8am.realmboilerplate.data;

import android.app.Application;
import android.support.annotation.NonNull;

import com.monday8am.realmboilerplate.R;
import com.monday8am.realmboilerplate.data.local.RealmDatabaseHelper;
import com.monday8am.realmboilerplate.data.model.NYTimesStory;
import com.monday8am.realmboilerplate.data.remote.NYTimesResponse;
import com.monday8am.realmboilerplate.data.remote.NYTimesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import timber.log.Timber;

/**
 * DataManager class for handling the business rules of the app.
 */

@Singleton
public class DataManager {

    /**
     * Map between section titles and their NYTimes API keys
     */
    private static final Map<String, String> mSections;
    static {
        mSections = new HashMap<>();
        mSections.put("home", "Home");
        mSections.put("world", "World");
        mSections.put("national", "National");
        mSections.put("politics", "Politics");
        mSections.put("nyregion", "NY Region");
        mSections.put("business", "Business");
        mSections.put("opinion", "Opinion");
        mSections.put("technology", "Technology");
        mSections.put("science", "Science");
        mSections.put("health", "Health");
        mSections.put("sports", "Sports");
        mSections.put("arts", "Arts");
        mSections.put("fashion", "Fashion");
        mSections.put("dining", "Dining");
        mSections.put("travel", "Travel");
        mSections.put("magazine", "Magazine");
        mSections.put("realestate", "Real Estate");
    }

    // Minimum 2 minutes between each network request
    private static final long MINIMUM_NETWORK_WAIT_SEC = 120;

    private final NYTimesService mNyTimesService;
    private final RealmDatabaseHelper mRealmHelper;
    private String mSelectedSection;
    private final String mApiKey;
    private Map<String, Long> mLastNetworkRequestMap = new HashMap<>();
    private BehaviorSubject<Boolean> mNetworkInUse = BehaviorSubject.create(false);

    @Inject
    public DataManager(Application context, RealmDatabaseHelper realmHelper,
                       NYTimesService nyTimesService) {
        mRealmHelper = realmHelper;
        mNyTimesService = nyTimesService;
        mApiKey = context.getString(R.string.nyc_top_stories_api_key);
    }

    /**
     * Returns the news feed for the currently selected category.
     */
    public Observable<RealmResults<NYTimesStory>> getSelectedNewsFeed() {
        return mRealmHelper.loadNewsFeed (mSelectedSection);
    }

    /**
     * Forces a reload of the newsfeed
     */
    public void reloadNewsFeed() {

        // Start loading data from the network if needed
        // It will put all data into Realm
        if (timeSinceLastNetworkRequest(mSelectedSection) > MINIMUM_NETWORK_WAIT_SEC) {
            mNetworkInUse.onNext(true);
            mNyTimesService.topStories(mSelectedSection, mApiKey)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<NYTimesResponse<List<NYTimesStory>>>() {
                        @Override
                        public void call(NYTimesResponse<List<NYTimesStory>> response) {
                            Timber.d("Success - Data received: %s", mSelectedSection);
                            mRealmHelper.mergeRemoteStoryList (mSelectedSection, response.results);
                            mNetworkInUse.onNext(false);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mNetworkInUse.onNext(false);
                            Timber.d("Failure: Data not loaded: %s - %s", mSelectedSection,
                                    throwable.toString());
                        }
                    });

            mLastNetworkRequestMap.put(mSelectedSection, System.currentTimeMillis());
        }
    }

    public void setSelectedSection(@NonNull String key) {
        mSelectedSection = key;
        getSelectedNewsFeed ();
    }

    /**
     * Returns the current state of network usage.
     */
    public Observable<Boolean> isNetworkUsed() {
        return mNetworkInUse.distinctUntilChanged();
    }

    /**
     * Marks a story as being read.
     */
    public void markAsRead(@NonNull String storyId, boolean read) {
        mRealmHelper.updateStoryReadState(storyId, read);
    }

    /**
     * Returns all sections available.
     *
     * @return A map of <key, title> pairs for all available mSections.
     */
    public Map<String, String> getSections() {
        return mSections;
    }

    /**
     * Get selected section.
     */
    @NonNull public String getCurrentSectionKey() {
        return mSelectedSection;
    }


    private long timeSinceLastNetworkRequest(@NonNull String sectionKey) {
        Long lastRequest = mLastNetworkRequestMap.get(sectionKey);
        if (lastRequest != null) {
            return TimeUnit.SECONDS.convert(System.currentTimeMillis() - lastRequest,
                    TimeUnit.MILLISECONDS);
        } else {
            return Long.MAX_VALUE;
        }
    }
}
