package com.serloman.themoviedb_api.calls;

import android.os.AsyncTask;

import com.serloman.themoviedb_api.TheMovieDb_Service;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieApi;
import com.serloman.themoviedb_api.models.MovieListApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public class DiscoverMoviesAsyncTask extends AsyncTask<String, Integer, List<Movie>> {

    public static final int POS_ARG_PAGE = 0;
    public static final int POS_ARG_SHORT_BY = 1;

    private TheMovieDb_Service mService;
    private String mApiKey;
    private MovieListCallback mMovieCallback;

    public DiscoverMoviesAsyncTask(TheMovieDb_Service service, String apiKey, MovieListCallback movieCallback){
        this.mService = service;
        this.mApiKey = apiKey;
        this.mMovieCallback = movieCallback;
    }

    protected List<Movie> doInBackground(String... args) {
        String shortBy = args[POS_ARG_SHORT_BY];

        MovieListApi moviesApi = mService.discoverMovies(shortBy, mApiKey);
        List<MovieApi> movies = moviesApi.getMovies();

        List<Movie> defaultMovies = new ArrayList<>();
        for(MovieApi movie : movies)
            defaultMovies.add(movie);

        return defaultMovies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if(movies!=null)
            mMovieCallback.onDataReceived(movies);
        else
            mMovieCallback.onError(null);
    }
}