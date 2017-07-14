package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;

import java.util.List;

/**
 * Created by excadmin on 2017/07/14.
 */

public interface MovieCategoryUseCase {

    interface MovieCategoryUseCaseCallback {
        void onMovieCategoryLoaded(String[] list);
        void onError();
    }

    void execute(MovieCategoryUseCase.MovieCategoryUseCaseCallback callback);

    void setCallback(MovieCategoryUseCase.MovieCategoryUseCaseCallback callback);
    void removeCallback();

}
