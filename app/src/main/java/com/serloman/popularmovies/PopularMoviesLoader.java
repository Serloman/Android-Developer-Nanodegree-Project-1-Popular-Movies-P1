package com.serloman.popularmovies;

import android.content.Context;

import com.serloman.themoviedb_api.TheMovieDb_Api;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 21/07/2015.
 */
public class PopularMoviesLoader extends MoviesLoader {
    public PopularMoviesLoader(Context context) {
        this(context, 1);
    }

    public PopularMoviesLoader(Context context, int page) {
        super(context, page);
    }

    @Override
    public List<Movie> loadInBackground() {
        DefaultTheMovieDbApi api = new DefaultTheMovieDbApi(getContext());

        return api.popularMovies(getPage());
    }
}
