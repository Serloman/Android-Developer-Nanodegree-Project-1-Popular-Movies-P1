package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.ImageMovie;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieMedia;

import java.util.List;

/**
 * Created by Serloman on 22/07/2015.
 */
public interface MovieMediaCallback {
    void onMovieMediaDataReceived(MovieMedia movieMedia);
    void onError(Exception ex);
}
