package com.monday8am.realmboilerplate.data;

import android.support.annotation.NonNull;

import com.monday8am.realmboilerplate.data.model.NYTimesStory;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmResults;
import rx.Observable;

/**
 * DataManager class for handling the business rules of the app.
 */

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

    private String mSelectedSection;

    /*
    private static DataManager instance = null;
    private final RealmDatabaseHelper realmHelper;
    private String mSelectedSection;

    // This could be replaced by Dependency Injection for easier testing
    public static synchronized DataManager getInstance() {
        if (instance == null) {
            RealmDatabaseHelper realmHelper = new RealmDatabaseHelper();
            instance = new DataManager(realmHelper);
        }
        return instance;
    }
    */

    /**
     * Returns the news feed for the currently selected category.
     */
    public Observable<RealmResults<NYTimesStory>> getSelectedNewsFeed() {
        return null; //repository.loadNewsFeed(mSelectedSection, false);
    }

    /**
     * Forces a reload of the newsfeed
     */
    public void reloadNewsFeed() {
        //repository.loadNewsFeed(mSelectedSection, true);
    }

    /**
     * Returns the current state of network usage.
     */
    public Observable<Boolean> isNetworkUsed() {
        return null; //return repository.networkInUse().distinctUntilChanged();
    }

    /**
     * Marks a story as being read.
     */
    public void markAsRead(@NonNull String storyId, boolean read) {
        //repository.updateStoryReadState(storyId, read);
    }

    /**
     * Returns all mSections available.
     *
     * @return A map of <key, title> pairs for all available mSections.
     */
    public Map<String, String> getSections() {
        return mSections;
    }

    public void selectSection(@NonNull String key) {
        mSelectedSection = key;
        //repository.loadNewsFeed(mSelectedSection, false);
    }

    @NonNull public String getCurrentSectionKey() {
        return mSelectedSection;
    }
}
