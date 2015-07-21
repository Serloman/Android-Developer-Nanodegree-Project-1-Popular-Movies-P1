package com.serloman.popularmovies;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.serloman.popularmovies.movieList.BasicMovieListFragment;
import com.serloman.themoviedb_api.TheMovieDb_Api;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class RatedMoviesFragment extends BasicMovieListFragment{

    public static RatedMoviesFragment newInstance(){
        return newInstance(2);
    }

    public static RatedMoviesFragment newInstance(int spanCount){
        RatedMoviesFragment fragment = new RatedMoviesFragment();

        Bundle args = new Bundle();
        args.putInt(BasicMovieListFragment.ARG_SPAN_COUNT, spanCount);
        fragment.setArguments(args);

        return fragment;
    }

    public RatedMoviesFragment() { }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MoviesLoader(getActivity(), TheMovieDb_Api.Short_By.VOTE_AVERAGE_DESC);
    }
}
