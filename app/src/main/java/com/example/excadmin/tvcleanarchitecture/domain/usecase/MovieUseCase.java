package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public interface MovieUseCase {

    interface MovieUseCaseCallback {
        void onMovieLoaded(Movie movie);
        void onError();
    }

    void execute(String movieId,MovieUseCase.MovieUseCaseCallback callback);

    void setCallback(MovieUseCase.MovieUseCaseCallback callback);
    void removeCallback();

}
