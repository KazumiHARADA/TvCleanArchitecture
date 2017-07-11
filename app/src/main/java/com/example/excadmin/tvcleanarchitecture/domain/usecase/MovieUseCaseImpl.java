package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.repository.MovieRepository;

import java.util.List;

/**
 * Created by excadmin on 2017/07/11.
 */

public class MovieUseCaseImpl extends UseCase<String> implements MovieUseCase,MovieRepository.MovieRepositoryCallback {

    private static MovieUseCaseImpl sUseCase;
    private final MovieRepository mMovieRepository;
    private PostExecutionThread mPostExecutionThread;
    private MovieUseCase.MovieUseCaseCallback mCallback;

    public MovieUseCaseImpl(MovieRepository movieRepository, PostExecutionThread postExecutionThread){
        mMovieRepository = movieRepository;
        mPostExecutionThread = postExecutionThread;
    }

    public static MovieUseCaseImpl getUseCase(MovieRepository movieRepository,PostExecutionThread postExecutionThread) {
        if (sUseCase == null) {
            sUseCase = new MovieUseCaseImpl(movieRepository,postExecutionThread);
        }
        return sUseCase;
    }

    @Override
    public void execute(String movieId, MovieUseCaseCallback callback) {
        mCallback = callback;
        this.start(null);
    }

    @Override
    public void setCallback(MovieUseCaseCallback callback) {
        mCallback = callback;
    }

    @Override
    public void removeCallback() {
        mCallback = null;
    }

    @Override
    public void onMovieListLoaded(List<Movie> list) {

    }

    @Override
    public void onMovieLoaded(final Movie movie) {
        mPostExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.onMovieLoaded(movie);
                }
            }
        });
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
    protected void call(String params) {
        mMovieRepository.getMovie(params);
    }
}
