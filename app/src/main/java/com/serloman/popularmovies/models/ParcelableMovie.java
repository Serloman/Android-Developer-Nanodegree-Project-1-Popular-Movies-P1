package com.serloman.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.serloman.themoviedb_api.models.Genre;
import com.serloman.themoviedb_api.models.ImageMovie;
import com.serloman.themoviedb_api.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serloman on 20/07/2015.
 */
public class ParcelableMovie implements Parcelable, Movie {

    private int mId;
    private String mTitle;
    private int mIsAdult;
    private long mBudget;
    private String mBackDropUrl;
    private String mPosterUrl;
    private List<ParcelableGenre> mGenres;

    public ParcelableMovie(Movie movie, ImageMovie.Sizes size){
        this.mId = movie.getId();
        this.mTitle = movie.getTitle();
        this.mIsAdult = 0;
        if(movie.isAdult())
            this.mIsAdult = 1;
        this.mBudget = movie.getBudget();
        this.mBackDropUrl = movie.getBackdropUrl(size);
        this.mPosterUrl = movie.getPosterUrl(size);
        mGenres = new ArrayList<>();
        for(Genre genre : movie.getListGenres())
            mGenres.add(new ParcelableGenre(genre));
    }

    public ParcelableMovie(Parcel in){
        readFromParcel(in);
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public boolean isAdult() {
        return mIsAdult==1;
    }

    @Override
    public long getBudget() {
        return mBudget;
    }

    @Override
    public List<Genre> getListGenres() {
        List<Genre> genres = new ArrayList<>();

        for(ParcelableGenre genre :  mGenres)
            genres.add(genre);

        return genres;
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getBackdropUrl(ImageMovie.Sizes size) {
        return mBackDropUrl;
    }

    @Override
    public String getPosterUrl(ImageMovie.Sizes size) {
        return mPosterUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeInt(mIsAdult);
        dest.writeLong(mBudget);
        dest.writeString(mBackDropUrl);
        dest.writeString(mPosterUrl);
        dest.writeList(mGenres);

    }

    private void readFromParcel(Parcel in){
        mId = in.readInt();
        mTitle = in.readString();
        mIsAdult = in.readInt();
        mBudget = in.readLong();
        mBackDropUrl = in.readString();
        mPosterUrl = in.readString();
        mGenres = new ArrayList<>();
        in.readList(mGenres, ParcelableGenre.class.getClassLoader());
    }

    public static final Parcelable.Creator<ParcelableMovie> CREATOR = new Parcelable.Creator<ParcelableMovie>(){

        @Override
        public ParcelableMovie createFromParcel(Parcel source) {
            return new ParcelableMovie(source);
        }

        @Override
        public ParcelableMovie[] newArray(int size) {
            return new ParcelableMovie[size];
        }
    };
}
