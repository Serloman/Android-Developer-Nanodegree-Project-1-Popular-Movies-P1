package com.serloman.popularmovies.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serloman.popularmovies.R;
import com.serloman.popularmovies.models.ParcelableImageMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serloman on 22/07/2015.
 */
public class GalleryFragment extends Fragment {

    public static final String ARG_POSITION = "ARG_POSITION";
    public static final String ARG_IMAGES_MOVIE = "ARG_IMAGES_MOVIE";

    public static GalleryFragment newInstance(int position, List<ParcelableImageMovie> images){
        GalleryFragment fragment = new GalleryFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putParcelableArrayList(ARG_IMAGES_MOVIE, (ArrayList<? extends Parcelable>) images);
        fragment.setArguments(args);

        return fragment;
    }

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private ViewPager.OnPageChangeListener pageChangeListener;

    public GalleryFragment(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery_fragment, container, false);

        initViewPager(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            pageChangeListener = (ViewPager.OnPageChangeListener)activity;
        }catch (ClassCastException ex){
            throw new ClassCastException("Activity must implement ViewPager.OnPageChangeListener");
        }
    }

    private void initViewPager(View rootView){
        mViewPager = (ViewPager) rootView.findViewById(R.id.galleryViewPager);
        mViewPager.setPageMargin(10);

        mAdapter = new GalleryFragmentPagerAdapter(getChildFragmentManager(), getImages());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(getFirstPosition());

        mViewPager.addOnPageChangeListener(pageChangeListener);
    }

    private List<ParcelableImageMovie> getImages(){
        return this.getArguments().getParcelableArrayList(ARG_IMAGES_MOVIE);
    };

    private int getFirstPosition(){
        return this.getArguments().getInt(ARG_POSITION);
    }

    private static class GalleryFragmentPagerAdapter extends FragmentPagerAdapter{

        private List<ParcelableImageMovie> mImages;

        public GalleryFragmentPagerAdapter(FragmentManager fm, List<ParcelableImageMovie> images) {
            super(fm);

            this.mImages = images;
        }

        @Override
        public Fragment getItem(int position) {
            return GalleryItemFragment.newInstance(mImages.get(position));
        }

        @Override
        public int getCount() {
            return mImages.size();
        }
    }
}
