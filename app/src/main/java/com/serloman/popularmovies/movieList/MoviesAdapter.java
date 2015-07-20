package com.serloman.popularmovies.movieList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serloman.popularmovies.R;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private List<Movie> mDataSet;

    public MoviesAdapter(List<Movie> dataSet){
        this.mDataSet = dataSet;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int i) {
        View movieSingleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_single_view, parent, false);

        return new MovieHolder(movieSingleView);
    }

    @Override
    public void onBindViewHolder(MovieHolder movieHolder, int i) {
        Movie movie = mDataSet.get(i);

        movieHolder.updateTitle(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class MovieHolder extends RecyclerView.ViewHolder{

        private TextView mTitleView;
        private ImageView mPosterView;

        public MovieHolder(View movieSingleView) {
            super(movieSingleView);

            mTitleView = (TextView) movieSingleView.findViewById(R.id.movieSingleViewTitle);
            mPosterView = (ImageView) movieSingleView.findViewById(R.id.movieSingleViewPoster);
        }

        public void updateTitle(String title){
            mTitleView.setText(title);
        }

        public ImageView getPosterView(){
            return mPosterView;
        }
    }
}
