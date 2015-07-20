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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);

        TheMovieDb_Api api = new TheMovieDb_Api(PopularMoviesApp.getTheMovieDbApiKey(getActivity()));
        api.discoverMoviesAsync(TheMovieDb_Api.Short_By.VOTE_AVERAGE_DESC, this);

        return rootView;
    }
}
