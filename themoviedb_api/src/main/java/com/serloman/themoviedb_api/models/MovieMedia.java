package com.serloman.themoviedb_api.models;

import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface MovieMedia {
    List<ImageMovieApi> getBackdrops();
    List<ImageMovieApi> getPosters();
}
