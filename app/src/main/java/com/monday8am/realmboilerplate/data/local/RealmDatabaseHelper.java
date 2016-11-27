package com.monday8am.realmboilerplate.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.monday8am.realmboilerplate.data.model.NYTimesStory;

import java.io.Closeable;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Realm DB helper is a container for all Realm-only operations.
 * It includes a mechanism to close the UI thread realm copy if
 * all activities are closed. More info at: https://github.com/Zhuinden/realm-book-example
 */

public class RealmDatabaseHelper implements Closeable {

    private Realm mRealm;

    private int mActivityCounter = 0;

    private RealmConfiguration mRealmConfiguration;

    @UiThread
    public RealmDatabaseHelper(RealmConfiguration realmConfiguration) {
        mRealmConfiguration = realmConfiguration;
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Loads the news feed as well as all future updates.
     */
    @UiThread
    public Observable<RealmResults<NYTimesStory>> loadNewsFeed(@NonNull String sectionKey) {
        RealmResults<NYTimesStory> test = mRealm.where(NYTimesStory.class).findAll();
        return mRealm.where(NYTimesStory.class).findAll() //equalTo(NYTimesStory.API_SECTION, sectionKey)
                //.findAllSortedAsync(NYTimesStory.PUBLISHED_DATE, Sort.DESCENDING)
                .asObservable();
    }

    /**
     * Updates a story.
     *
     * @param storyId story to update
     * @param read {@code true} if the story has been read, {@code false} otherwise.
     */
    @UiThread
    public void updateStoryReadState(final String storyId, final boolean read) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NYTimesStory persistedStory = realm.where(NYTimesStory.class)
                        .equalTo(NYTimesStory.URL, storyId)
                        .findFirst();
                if (persistedStory != null) {
                    persistedStory.setRead(read);
                } else {
                    Timber.e("Trying to update a story that no longer exists: %1$s", storyId);
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable throwable) {
                Timber.e(throwable, "Failed to save data.");
            }
        });
    }

    /**
     * Returns story details
     */
    @UiThread
    public Observable<NYTimesStory> loadStory(final String storyId) {
        return mRealm.where(NYTimesStory.class)
                .equalTo(NYTimesStory.URL, storyId)
                .findFirstAsync()
                .<NYTimesStory>asObservable()
                .filter(new Func1<NYTimesStory, Boolean>() {
                    @Override
                    public Boolean call(NYTimesStory story) {
                        return story.isLoaded();
                    }
                });
    }

    @UiThread
    public void mergeRemoteStoryList(final String sectionKey, final List<NYTimesStory> stories) {
        if (stories.isEmpty()) return;

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (NYTimesStory story : stories) {

                    // Find existing story in Realm (if any)
                    // If it exists, we need to merge the local state
                    // with the remote, because the local state
                    // contains more info than is available on the server.
                    NYTimesStory persistedStory = realm.where(NYTimesStory.class)
                            .equalTo(NYTimesStory.URL, story.getUrl())
                            .findFirst();

                    // Only create or update the local story if needed
                    if (MergePolicer.mergeNYTimeStory(persistedStory, story)) {
                        story.setApiSection(sectionKey);
                        realm.copyToRealmOrUpdate(story);
                    }
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable throwable) {
                Timber.e(throwable, "Could not save data");
            }
        });
    }

    /**
     * Closes all underlying resources used by the Repository.
     */
    @UiThread
    public void close() {
        mRealm.close();
    }

    /**
     * Keep only one Realm instance open, depending on
     * the activity is open or closed.
     */
    @UiThread
    public void incrementCount() {
        if (mActivityCounter == 0) {
            if (mRealm != null && !mRealm.isClosed()) {
                Timber.w("Unexpected open Realm found.");
                mRealm.close();
            }

            Timber.d("Incrementing Activity Count [0]: opening Realm.");
            mRealm = Realm.getDefaultInstance();
        }

        mActivityCounter++;
        Timber.d("Increment: Count [ %s ]", mActivityCounter);
    }

    @UiThread
    public void decrementCount() {
        mActivityCounter--;
        Timber.d("Decrement: Count [ %s ]", mActivityCounter);
        if (mActivityCounter <= 0) {
            Timber.d("Decrementing Activity Count: closing Realm.");
            mActivityCounter = 0;
            mRealm.close();

            if (Realm.compactRealm(mRealmConfiguration))
                Timber.d("Realm compacted successfully.");

            mRealm = null;
        }
    }
}
