package com.serloman.popularmovies.movieDetails;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serloman.popularmovies.DefaultTheMovieDbApi;
import com.serloman.popularmovies.R;
import com.serloman.themoviedb_api.calls.MovieCallback;
import com.serloman.themoviedb_api.calls.MovieImagesCallback;
import com.serloman.themoviedb_api.models.FullMovie;
import com.serloman.themoviedb_api.models.ImageMovie;
import com.serloman.themoviedb_api.models.Movie;
import com.serloman.themoviedb_api.models.MovieImages;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class MovieDetailsFragment extends Fragment implements MovieCallback, LoaderManager.LoaderCallbacks<FullMovie>, MovieImagesCallback {

    public final static String ARG_MOVIE_DATA = "ARG_MOVIE_DATA";

    public static MovieDetailsFragment newInstance(Movie movie){
        MovieDetailsFragment fragment = new MovieDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE_DATA, (Parcelable) movie);
        fragment.setArguments(args);

        return fragment;
    }

    private FullMovie mMovie;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

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

        initBasicData();
    }

    private void initBasicData(){
        Movie movie = getBasicMovieData();

        initToolbar(movie);
        initBackDrop(movie);
        initMovieData(movie);
        initGallery(movie);
    }

    private Movie getBasicMovieData(){
        return this.getArguments().getParcelable(ARG_MOVIE_DATA);
    }

    @Override
    public void onMovieDataReceived(FullMovie movie) {
//        initExtras();
    }

    private void initBackDrop(Movie movie){
        ImageView backDrop = (ImageView) getView().findViewById(R.id.movieDetailsBackdrop);
        Picasso.with(getActivity().getApplicationContext()).load(movie.getBackdropUrl(ImageMovie.Sizes.w500)).into(backDrop);
    }

    private void initToolbar(Movie movie){
        AppCompatActivity activity = (AppCompatActivity) this.getActivity();
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.movieDetailsToolbar);

        activity.setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) getView().findViewById(R.id.movieDetailsCollapsingToolbar);
        collapsingToolbarLayout.setTitle(movie.getTitle());
    }

    private void initMovieData(Movie movie){
        ImageView poster = (ImageView) getView().findViewById(R.id.movieDetailsPoster);
        Picasso.with(getActivity().getApplicationContext()).load(movie.getPosterUrl(ImageMovie.Sizes.w185)).into(poster);

        TextView overview = (TextView) getView().findViewById(R.id.movieDetailsOverview);
        overview.setText(movie.getOverview());
    }

    private void initGallery(Movie movie){
        mViewPager = (ViewPager) getView().findViewById(R.id.movieDetailsGallery);
        mViewPager.setPageMargin(20);
        mViewPager.setClipToPadding(false);

        int paddingSiblings = 120;
        mViewPager.setPadding(paddingSiblings, 0, paddingSiblings, 0);

        DefaultTheMovieDbApi api = new DefaultTheMovieDbApi(getActivity());
        api.getMovieImagesAsync(movie.getId(), this);
    }

    @Override
    public void onMovieMediaDataReceived(MovieImages movieMedia) {
        List<ImageMovie> gallery = movieMedia.getBackdrops();
        mAdapter = new GalleryPagerAdapter(getActivity(), gallery);

        mViewPager.setAdapter(mAdapter);

        if(gallery.size()>1)
            mViewPager.setCurrentItem(1);
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

    private static class GalleryPagerAdapter extends PagerAdapter{

        Context mContext;
        List<ImageMovie> mImages;

        public GalleryPagerAdapter(Context context, List<ImageMovie> images){
            super();

            this.mContext = context;
            this.mImages = images;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.gallery_item_view, container, false);

            ImageMovie image = mImages.get(position);

            ImageView galleryImage = (ImageView) rootView.findViewById(R.id.galleryImageMovie);
            Picasso.with(mContext).load(image.getUrl(ImageMovie.Sizes.w500)).into(galleryImage);

//            setOnItemClickListener(rootView, position, image);

            container.addView(rootView);

            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private void setOnItemClickListener(View layout, final int position, final ImageMovie imageMovie){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, imageMovie.getUrl(ImageMovie.Sizes.original), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
