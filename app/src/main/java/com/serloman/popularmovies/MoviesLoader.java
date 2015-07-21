package com.serloman.popularmovies;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.serloman.themoviedb_api.TheMovieDb_Api;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieListApi;

import java.util.List;

/**
 * Created by Serloman on 21/07/2015.
 */
public class MoviesLoader extends AsyncTaskLoader<List<Movie>> {

    private int mPage;
    private TheMovieDb_Api.Short_By mShortBy;

    public MoviesLoader(Context context, TheMovieDb_Api.Short_By shortBy) {
        this(context, 1, shortBy);
    }

    public MoviesLoader(Context context, int page, TheMovieDb_Api.Short_By shortBy) {
        super(context);

        this.mPage = page;
        this.mShortBy = shortBy;

        onContentChanged();
    }

    @Override
    public List<Movie> loadInBackground() {
        DefaultTheMovieDbApi api = new DefaultTheMovieDbApi(getContext());

        return api.discoverMovies(mPage, mShortBy);
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
