package com.serloman.themoviedb_api.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public class MovieApi implements Movie{
    private String title;
    private boolean adult;
    private String backdrop_path;
    private long budget;
    private List<GenreApi> genres;
    private int id;

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isAdult() {
        return adult;
    }

    @Override
    public String getBackdrop_path() {
        return backdrop_path;
    }

    @Override
    public long getBudget() {
        return budget;
    }

    @Override
    public List<Genre> getListGenres() {
        List<Genre> movieGenres = new ArrayList<>();

        for(GenreApi genre : genres)
            movieGenres.add(genre);

        return movieGenres;
    }

    @Override
    public int getId() {
        return id;
    }
}
