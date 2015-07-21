package com.serloman.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serloman.themoviedb_api.calls.MovieCallback;
import com.serloman.themoviedb_api.models.FullMovie;
import com.serloman.themoviedb_api.models.Genre;
import com.serloman.themoviedb_api.models.Movie;

/**
 * Created by Serloman on 20/07/2015.
 */
public class MovieDetailsFragment extends Fragment implements MovieCallback, LoaderManager.LoaderCallbacks<FullMovie>{

    public final static String ARG_MOVIE_DATA = "ARG_MOVIE_DATA";

    public static MovieDetailsFragment newInstance(Movie movie){
        MovieDetailsFragment fragment = new MovieDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE_DATA, (Parcelable) movie);
        fragment.setArguments(args);

        return fragment;
    }

    private FullMovie mMovie;

    public MovieDetailsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_details_fragment, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        getLoaderManager().initLoader(0, null, this);
    }

    private Movie getBasicMovieData(){
        return this.getArguments().getParcelable(ARG_MOVIE_DATA);
    }

    @Override
    public void onMovieDataReceived(FullMovie movie) {
/**/
        Toast.makeText(getActivity(), "I'm " + movie.getTitle(), Toast.LENGTH_SHORT).show();
        for(Genre genre : movie.getListGenres())
            Log.d("MovieDetailsFragment", genre.toString());
/**/
    }

    @Override
    public void onError(Exception ex) {
        // Nothing to do here...
    }

    @Override
    public Loader<FullMovie> onCreateLoader(int id, Bundle args) {
        return new TakeListLoader(getActivity(), getBasicMovieData().getId());
    }

    @Override
    public void onLoadFinished(Loader<FullMovie> loader, FullMovie data) {
        this.mMovie = data;

        onMovieDataReceived(data);
    }

    @Override
    public void onLoaderReset(Loader<FullMovie> loader) {

    }

    private static class TakeListLoader extends AsyncTaskLoader<FullMovie> {

        private int mIdMovie;

        public TakeListLoader(Context context, int idMovie) {
            super(context);

            this.mIdMovie = idMovie;

            onContentChanged();
        }

        @Override
        public FullMovie loadInBackground() {
            DefaultTheMovieDbApi api = new DefaultTheMovieDbApi(getContext());
            return api.getMovieData(mIdMovie);
        }

        @Override
        protected void onStartLoading() {
            if (takeContentChanged())
                forceLoad();
        }

        @Override
        protected void onStopLoading() {
            cancelLoad();
        }
    }
}
