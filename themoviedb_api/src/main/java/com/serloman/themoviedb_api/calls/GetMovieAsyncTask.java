package com.serloman.themoviedb_api.calls;

import android.os.AsyncTask;

import com.serloman.themoviedb_api.TheMovieDb_Service;
import com.serloman.themoviedb_api.models.Movie;

/**
 * Created by Serloman on 19/07/2015.
 */
public class GetMovieAsyncTask extends AsyncTask<String, Integer, Movie> {

    private TheMovieDb_Service mService;
    private String mApiKey;
    private MovieCallback mMovieCallback;

    public GetMovieAsyncTask(TheMovieDb_Service service, String apiKey, MovieCallback movieCallback){
        this.mService = service;
        this.mApiKey = apiKey;
        this.mMovieCallback = movieCallback;
    }

    protected Movie doInBackground(String... movieIds) {
        String movieId = movieIds[0];
        Movie movie = mService.movieData(movieId, mApiKey);

        return movie;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        if(movie!=null)
            mMovieCallback.onDataReceived(movie);
        else
            mMovieCallback.onError(null);
    }
}
