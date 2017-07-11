package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.repository.MovieRepository;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public class MovieListUseCaseImpl extends UseCase<Void> implements MovieListUseCase,MovieRepository.MovieRepositoryCallback {

    private static MovieListUseCaseImpl sUseCase;
    private final MovieRepository mMovieRepository;
    private PostExecutionThread mPostExecutionThread;
    private MovieListUseCaseCallback mCallback;

    public static MovieListUseCaseImpl getUseCase(MovieRepository movieRepository,PostExecutionThread postExecutionThread) {
        if (sUseCase == null) {
            sUseCase = new MovieListUseCaseImpl(movieRepository,postExecutionThread);
        }
        return sUseCase;
    }

    public MovieListUseCaseImpl(MovieRepository movieRepository, PostExecutionThread postExecutionThread){
        mMovieRepository = movieRepository;
        mPostExecutionThread = postExecutionThread;
    }

    @Override
    public void execute(MovieListUseCaseCallback callback) {
        mCallback = callback;
        this.start(null);
    }

    @Override
    public void setCallback(MovieListUseCaseCallback callback) {
        mCallback = callback;
    }

    @Override
    public void removeCallback() {
        mCallback = null;
    }

    @Override
    public void onMovieListLoaded(final List<Movie> list) {
        mPostExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.onMovieListLoaded(list);
                }
            }
        });
    }

    @Override
    public void onMovieLoaded(Movie movie) {

    }

    @Override
    public void onError() {
        mPostExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.onError();
                }
            }
        });
    }

    @Override
    protected void call(Void params) {
        mMovieRepository.getMovieList(this);
    }
}
