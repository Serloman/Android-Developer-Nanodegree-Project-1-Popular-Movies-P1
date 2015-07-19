package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.Movie;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface MovieCallback {
    void onDataReceived(Movie movie);
    void onError(Exception ex);
}
