package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.usecase.MovieCategoryUseCase;
import com.example.excadmin.tvcleanarchitecture.domain.usecase.MovieListUseCase;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public class MovieListPresenter extends Presenter implements MovieListUseCase.MovieListUseCaseCallback,MovieCategoryUseCase.MovieCategoryUseCaseCallback{

    private MovieListUseCase mMovieListUseCase;
    private MovieCategoryUseCase mMovieCategoryUseCase;
    private ShowMovieListView mShowMovieListView;

    private List<Movie> mMovieList = null;

    public MovieListPresenter(MovieListUseCase movieListUseCase,MovieCategoryUseCase movieCategoryUseCase){
        mMovieListUseCase = movieListUseCase;
        mMovieCategoryUseCase = movieCategoryUseCase;
    }

    public void setShowMovieListView(ShowMovieListView view){
        mShowMovieListView = view;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {
        mMovieListUseCase.setCallback(this);
        mMovieCategoryUseCase.setCallback(this);
    }

    @Override
    public void pause() {
        mMovieListUseCase.removeCallback();
        mMovieCategoryUseCase.removeCallback();
    }

    @Override
    public void destroy() {

    }

    public void getMovieList() {
        mShowMovieListView.showLoading();
        mMovieListUseCase.execute(this);
    }

    @Override
    public void onMovieListLoaded(List<Movie> list) {
        mMovieList = list;
        mMovieCategoryUseCase.execute(this);
    }

    @Override
    public void onMovieCategoryLoaded(String[] list) {
        mShowMovieListView.hideLoading();
        mShowMovieListView.hideNoResultCase();
        mShowMovieListView.showResult(mMovieList,list);
    }

    @Override
    public void onError() {
        mShowMovieListView.hideLoading();
        mShowMovieListView.showNoResultCase();
    }

    public interface ShowMovieListView {
        void showLoading();
        void hideLoading();
        void showNoResultCase();
        void hideNoResultCase();
        void showResult(List<Movie> list,String[] category);
    }
}
