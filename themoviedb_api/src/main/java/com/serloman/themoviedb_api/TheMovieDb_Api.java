package com.serloman.themoviedb_api;


import com.serloman.themoviedb_api.calls.DiscoverMoviesAsyncTask;
import com.serloman.themoviedb_api.calls.GetMovieAsyncTask;
import com.serloman.themoviedb_api.calls.MovieCallback;
import com.serloman.themoviedb_api.calls.MovieListCallback;
import com.serloman.themoviedb_api.calls.MovieMediaAsyncTask;
import com.serloman.themoviedb_api.calls.MovieMediaCallback;
import com.serloman.themoviedb_api.models.DiscoverMovieApi;
import com.serloman.themoviedb_api.models.FullMovie;
import com.serloman.themoviedb_api.models.ImageMovie;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieListApi;
import com.serloman.themoviedb_api.models.MovieMedia;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Serloman on 19/07/2015.
 */
public class TheMovieDb_Api {

    public enum Short_By {
        POPULARITY_ASC("popularity.asc"),
        POPULARITY_DESC("popularity.desc"),
        VOTE_AVERAGE_ASC("vote_average.asc"),
        VOTE_AVERAGE_DESC("vote_average.desc");

        private final String mName;

        Short_By(String name) {
            this.mName = name;
        }


        @Override
        public String toString() {
            return mName;
        }
    }

    private static final String API_ENDPOINT = "http://api.themoviedb.org/3";

    private String mApiKey;
    private RestAdapter mRestAdapter;
    private TheMovieDb_Service mService;

    public TheMovieDb_Api(String apiKey){
        mRestAdapter = new RestAdapter.Builder().setEndpoint(API_ENDPOINT).build();
        mService = mRestAdapter.create(TheMovieDb_Service.class);
        mApiKey = apiKey;
    }

    public FullMovie getMovieData(int idMovie){
        return getMovieData(String.valueOf(idMovie));
    }

    public FullMovie getMovieData(String idMovie){
        return mService.movieData(idMovie, mApiKey);
    }

    public void getMovieDataAsync(int idMovie, MovieCallback movieCallback){
        getMovieDataAsync(String.valueOf(idMovie), movieCallback);
    }

    public void getMovieDataAsync(String idMovie, MovieCallback movieCallback){
        GetMovieAsyncTask task = new GetMovieAsyncTask(mService, mApiKey, movieCallback);
        task.execute(idMovie);
    }

    public List<Movie> discoverMovies(Short_By short_by){
        return discoverMovies(1, short_by);
    }

    public List<Movie> discoverMovies(int page, Short_By short_by){
        MovieListApi moviesApi = mService.discoverMovies(String.valueOf(page), short_by.toString(), mApiKey);

        return moviesApi.getMovies();
    }

    public void discoverMoviesAsync(Short_By short_by, MovieListCallback moviesCallback){
        discoverMoviesAsync(1, short_by, moviesCallback);
    }

    public void discoverMoviesAsync(int page, Short_By short_by, MovieListCallback moviesCallback){
        DiscoverMoviesAsyncTask task = new DiscoverMoviesAsyncTask(mService, mApiKey, moviesCallback);
        task.execute(String.valueOf(page), short_by.toString());
    }

    public MovieMedia getMovieMedia(int idMovie){
        return getMovieMedia(String.valueOf(idMovie));
    }

    public MovieMedia getMovieMedia(String idMovie){
        return mService.mediaMovie(idMovie, mApiKey);
    }

    public void getMovieMediaAsync(int idMovie, MovieMediaCallback movieMediaCallback){
        getMovieMediaAsync(String.valueOf(idMovie), movieMediaCallback);
    }

    public void getMovieMediaAsync(String idMovie, MovieMediaCallback movieMediaCallback){
        MovieMediaAsyncTask task = new MovieMediaAsyncTask(mService, mApiKey, movieMediaCallback);
        task.execute(idMovie);
    }
}
