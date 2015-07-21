package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface MovieListCallback {
    void onMovieListDataReceived(List<Movie> movies);
    void onError(Exception ex);
}
