package com.serloman.popularmovies.movieList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serloman.popularmovies.R;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class BasicMovieListFragment extends Fragment {

    public static final String ARG_SPAN_COUNT = "ARG_SPAN_COUNT";

    public BasicMovieListFragment(){ }

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_grid_fragment, container, false);

        initRecyclerGridView(rootView);

        return rootView;
    }

    private void initRecyclerGridView(View rootView){
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.movieGridRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getSpanCount()));
        mRecyclerView.setHasFixedSize(true);
    }

    private int getSpanCount(){
        return this.getArguments().getInt(ARG_SPAN_COUNT, 2);
    }

    protected void populateGrid(List<Movie> movies){
        mMoviesAdapter = new MoviesAdapter(movies);
        mRecyclerView.setAdapter(mMoviesAdapter);
    }
}
