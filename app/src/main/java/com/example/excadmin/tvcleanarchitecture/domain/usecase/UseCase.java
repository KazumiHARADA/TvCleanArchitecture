package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by excadmin on 2017/07/11.
 */
public abstract class UseCase<T> {
    // Everything inside this method will be executed asynchronously.
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public void start(final T params) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                call(params);
            }
        });
    }

    abstract protected void call(T params);
}
