package com.serloman.themoviedb_api.models;

import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public class MovieListApi {
    private List<MovieApi> results;

    public List<MovieApi> getMovies(){
        return results;
    }
}
