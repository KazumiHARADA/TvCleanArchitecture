package com.example.excadmin.tvcleanarchitecture.domain.usecase;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.model.Movie;
import com.example.excadmin.tvcleanarchitecture.domain.repository.MovieRepository;

import java.util.List;

/**
 * Created by excadmin on 2017/07/14.
 */

public class MovieCategoryUseCaseImpl extends UseCase<Void> implements MovieCategoryUseCase,MovieRepository.MovieRepositoryCallback {

    private static MovieCategoryUseCaseImpl sUseCase;
    private final MovieRepository mMovieRepository;
    private PostExecutionThread mPostExecutionThread;
    private MovieCategoryUseCaseCallback mCallback;

    public static MovieCategoryUseCaseImpl getUseCase(MovieRepository movieRepository,PostExecutionThread postExecutionThread) {
        if (sUseCase == null) {
            sUseCase = new MovieCategoryUseCaseImpl(movieRepository,postExecutionThread);
        }
        return sUseCase;
    }

    public MovieCategoryUseCaseImpl(MovieRepository movieRepository, PostExecutionThread postExecutionThread){
        mMovieRepository = movieRepository;
        mPostExecutionThread = postExecutionThread;
    }

    @Override
    public void execute(MovieCategoryUseCaseCallback callback) {
        mCallback = callback;
        this.start(null);
    }

    @Override
    public void setCallback(MovieCategoryUseCaseCallback callback) {
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
    public void onMovieLoaded(Movie movie) {

    }

    @Override
    public void onCategoryListLoaded(final String[] list) {
        mPostExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.onMovieCategoryLoaded(list);
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
    protected void call(Void params) {
        mMovieRepository.getCategoryList(this);
    }
}
