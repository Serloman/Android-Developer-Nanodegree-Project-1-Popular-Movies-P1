package com.serloman.themoviedb_api;

import com.serloman.themoviedb_api.models.MovieMediaApi;
import com.serloman.themoviedb_api.models.MovieApi;
import com.serloman.themoviedb_api.models.MovieListApi;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Serloman on 19/07/2015.
 */
public interface TheMovieDb_Service {

    @GET("/movie/{movie}")
    MovieApi movieData(@Path("movie") String movie, @Query("api_key") String api_key);

    @GET("/discover/movie")
    MovieListApi discoverMovies(@Query("short_by") String sort_by, @Query("api_key") String api_key);

    @GET("/movie/{movie}/images")
    MovieMediaApi mediaMovie(@Path("movie") String movie, @Query("api_key") String api_key);
}
