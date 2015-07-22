package com.serloman.themoviedb_api.calls;

import android.os.AsyncTask;

import com.serloman.themoviedb_api.TheMovieDb_Service;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieListApi;
import com.serloman.themoviedb_api.models.MovieMedia;

import java.util.List;

/**
 * Created by Serloman on 22/07/2015.
 */
public class MovieMediaAsyncTask  extends AsyncTask<String, Integer, MovieMedia> {

    private TheMovieDb_Service mService;
    private String mApiKey;
    private MovieMediaCallback mMovieCallback;

    public MovieMediaAsyncTask(TheMovieDb_Service service, String apiKey, MovieMediaCallback movieCallback){
        this.mService = service;
        this.mApiKey = apiKey;
        this.mMovieCallback = movieCallback;
    }

    protected MovieMedia doInBackground(String... args) {
        String idMovie = args[0];

        return mService.mediaMovie(idMovie, mApiKey);
    }

    @Override
    protected void onPostExecute(MovieMedia movieMedia) {
        if(movieMedia!=null)
            mMovieCallback.onMovieMediaDataReceived(movieMedia);
        else
            mMovieCallback.onError(null);
    }
}