package com.serloman.themoviedb_api.models;

import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public class MovieMediaApi implements MovieMedia{
    int id;
    private List<ImageMovieApi> backdrops;
    private List<ImageMovieApi> posters;

    @Override
    public List<ImageMovieApi> getBackdrops(){
        return backdrops;
    }

    @Override
    public List<ImageMovieApi> getPosters(){
        return backdrops;
    }
}
