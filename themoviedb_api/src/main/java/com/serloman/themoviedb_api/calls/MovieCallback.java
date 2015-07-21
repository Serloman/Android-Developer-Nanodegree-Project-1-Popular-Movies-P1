package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.FullMovie;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface MovieCallback {
    void onMovieDataReceived(FullMovie movie);
    void onError(Exception ex);
}
