package com.serloman.themoviedb_api.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class FullMovieApi extends DiscoverMovieApi implements FullMovie{

    private long budget;
    private List<GenreApi> genres;

    @Override
    public long getBudget() {
        return budget;
    }

    @Override
    public List<Genre> getListGenres() {
        List<Genre> movieGenres = new ArrayList<>();

        if(genres!=null)
            for(GenreApi genre : genres)
                movieGenres.add(genre);

        return movieGenres;
    }
}
