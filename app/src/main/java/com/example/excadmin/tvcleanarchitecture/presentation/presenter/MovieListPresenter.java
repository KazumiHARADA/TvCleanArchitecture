package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.usecase.MovieListUseCase;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public class MovieListPresenter extends Presenter implements MovieListUseCase.MovieListUseCaseCallback{

    private MovieListUseCase mMovieListUseCase;
    private ShowMovieListView mShowMovieListView;

    public MovieListPresenter(MovieListUseCase movieListUseCase){
        mMovieListUseCase = movieListUseCase;
    }

    public void setShowUserListView(ShowMovieListView view){
        mShowMovieListView = view;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {
        mMovieListUseCase.setCallback(this);
    }

    @Override
    public void pause() {
        mMovieListUseCase.removeCallback();
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
        mShowMovieListView.hideLoading();
        mShowMovieListView.hideNoResultCase();
        mShowMovieListView.showResult(list);
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
        void showResult(List<Movie> list);
    }
}
