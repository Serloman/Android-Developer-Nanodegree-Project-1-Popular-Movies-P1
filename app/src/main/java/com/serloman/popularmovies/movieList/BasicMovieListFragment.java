package com.serloman.popularmovies.movieList;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serloman.popularmovies.R;
import com.serloman.themoviedb_api.calls.MovieListCallback;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public abstract class BasicMovieListFragment extends Fragment implements MovieListCallback, MoviesAdapter.MovieSelectedListener {

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
        mRecyclerView.addItemDecoration(new MovieDecoration(20));
        mRecyclerView.setHasFixedSize(true);
    }

    private int getSpanCount(){
        return this.getArguments().getInt(ARG_SPAN_COUNT, 2);
    }

    @Override
    public void onDataReceived(List<Movie> movies) {
        mMoviesAdapter = new MoviesAdapter(getActivity(), movies, this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        getView().findViewById(R.id.movieGridProgressBar).setVisibility(View.GONE);
    }

    @Override
    public void onError(Exception ex) {
        Toast.makeText(getActivity(), getActivity().getString(R.string.error_getting_movie), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Toast.makeText(getActivity(), "I'm " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }


}
