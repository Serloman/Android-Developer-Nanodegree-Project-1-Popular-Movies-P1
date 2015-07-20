package com.serloman.popularmovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serloman.popularmovies.movieList.BasicMovieListFragment;
import com.serloman.themoviedb_api.TheMovieDb_Api;

/**
 * Created by Serloman on 20/07/2015.
 */
public class PopularMoviesFragment extends BasicMovieListFragment{

    public static PopularMoviesFragment newInstance(){
        return newInstance(2);
    }

    public static PopularMoviesFragment newInstance(int spanCount){
        PopularMoviesFragment fragment = new PopularMoviesFragment();

        Bundle args = new Bundle();
        args.putInt(BasicMovieListFragment.ARG_SPAN_COUNT, spanCount);
        fragment.setArguments(args);

        return fragment;
    }

    public PopularMoviesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);

        TheMovieDb_Api api = new TheMovieDb_Api(getString(R.string.the_movie_db_api_key));
        api.discoverMoviesAsync(TheMovieDb_Api.Short_By.POPULARITY_DESC, this);

        return rootView;
    }
}
