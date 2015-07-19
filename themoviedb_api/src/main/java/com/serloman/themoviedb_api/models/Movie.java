package com.serloman.themoviedb_api.models;


import java.util.List;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface Movie {
    String getTitle();
    boolean isAdult();
    String getBackdrop_path();
    long getBudget();
    List<Genre> getListGenres();
    int getId();
}
