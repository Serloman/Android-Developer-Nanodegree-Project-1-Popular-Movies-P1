package com.serloman.popularmovies.movieList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public abstract class BasicMovieListFragment extends Fragment implements MovieListCallback, LoaderManager.LoaderCallbacks<List<Movie>>, MoviesAdapter.MovieSelectedListener {

    public interface OpenMovieListener{
        void openMovie(Movie movie);
    }

    public static final String ARG_SPAN_COUNT = "ARG_SPAN_COUNT";

    public BasicMovieListFragment(){ }

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private OpenMovieListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_grid_fragment, container, false);

        initRecyclerGridView(rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mListener = (OpenMovieListener)activity;
        }catch (ClassCastException ex){
            throw new ClassCastException(activity.toString() + " must implement BasicMovieListFragment.OpenMovieListener interface");
        }
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
    public void onMovieListDataReceived(List<Movie> movies) {
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
        mListener.openMovie(movie);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        onMovieListDataReceived(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }
}
