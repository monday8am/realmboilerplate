package com.monday8am.realmboilerplate.util;

//import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by anton on 20/11/2016.
 */

public class RxEventBus {

    private final PublishSubject<Object> mBusSubject;

    //@Inject
    public RxEventBus() {
        mBusSubject = PublishSubject.create();
    }

    /**
     * Posts an object (usually an Event) to the bus
     */
    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    /**
     * Observable that will emmit everything posted to the event bus.
     */
    public Observable<Object> observable() {
        return mBusSubject;
    }

    /**
     * Observable that only emits events of a specific class.
     * Use this if you only want to subscribe to one type of events.
     */
    public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
        return mBusSubject.ofType(eventClass);
    }
}
