package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.usecase.MovieListUseCase;
import com.example.excadmin.tvcleanarchitecture.domain.usecase.MovieUseCase;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public class MovieDetailPresenter extends Presenter implements MovieUseCase.MovieUseCaseCallback,MovieListUseCase.MovieListUseCaseCallback{

    private MovieUseCase mMovieUseCase;
    private MovieListUseCase mMovieListUseCase;
    private MovieDetailPresenter.ShowMovieView mShowMovieView;

    public MovieDetailPresenter(MovieUseCase movieUseCase,MovieListUseCase movieListUseCase){
        mMovieUseCase = movieUseCase;
        mMovieListUseCase = movieListUseCase;
    }

    public void setShowMovieView(MovieDetailPresenter.ShowMovieView view){
        mShowMovieView = view;
    }

    @Override
    public void onMovieLoaded(Movie movie) {
        mShowMovieView.hideLoading();
        mShowMovieView.hideNoResultCase();
        mShowMovieView.showResult(movie);
    }

    @Override
    public void onMovieListLoaded(List<Movie> list) {
        mShowMovieView.hideLoading();
        mShowMovieView.hideNoResultCase();
        mShowMovieView.showResultList(list);
    }

    @Override
    public void onError() {
        mShowMovieView.hideLoading();
        mShowMovieView.showNoResultCase();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {
        mMovieUseCase.setCallback(this);
        mMovieListUseCase.setCallback(this);
    }

    @Override
    public void pause() {
        mMovieUseCase.removeCallback();
        mMovieListUseCase.removeCallback();
    }

    @Override
    public void destroy() {

    }

    public void getMovie(long id) {
        mShowMovieView.showLoading();
        mMovieUseCase.execute(id,this);
    }

    public void getMovieList() {
        mShowMovieView.showLoading();
        mMovieListUseCase.execute(this);
    }

    public interface ShowMovieView {
        void showLoading();
        void hideLoading();
        void showNoResultCase();
        void hideNoResultCase();
        void showResult(Movie movie);
        void showResultList(List<Movie> movie);
    }
}
