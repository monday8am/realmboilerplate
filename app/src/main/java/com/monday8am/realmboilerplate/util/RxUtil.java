package com.monday8am.realmboilerplate.util;

import rx.Subscription;

/**
 * Created by anton on 20/11/2016.
 */

public final class RxUtil {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
