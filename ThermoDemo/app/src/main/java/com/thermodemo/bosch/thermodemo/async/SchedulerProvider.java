package com.thermodemo.bosch.thermodemo.async;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {
    private static SchedulerProvider instance;

    public static SchedulerProvider getIsntance() {
        if (instance == null) {
            instance = new SchedulerProvider();
        }

        return instance;
    }

    public Scheduler background() {
        return Schedulers.io();
    }

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
