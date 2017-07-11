package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public interface MovieListUseCase {

    interface MovieListUseCaseCallback {
        void onMovieListLoaded(List<Movie> list);
        void onError();
    }

    void execute(MovieListUseCaseCallback callback);

    void setCallback(MovieListUseCaseCallback callback);
    void removeCallback();
}
