package com.example.excadmin.tvcleanarchitecture.domain.repository;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public interface MovieRepository {
    void getMovieList(MovieRepositoryCallback movieRepositoryCallback);
    void getMovie(long id, MovieRepositoryCallback movieRepositoryCallback);
    void getCategoryList(MovieRepositoryCallback movieRepositoryCallback);
    void putMovie(Movie movie);
    interface MovieRepositoryCallback {
        void onMovieListLoaded(List<Movie> list);
        void onMovieLoaded(Movie movie);
        void onCategoryListLoaded(String[] list);
        void onError();
    }


}
