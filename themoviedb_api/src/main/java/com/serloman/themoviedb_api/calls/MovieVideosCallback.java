package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.VideoMovie;

import java.util.List;

/**
 * Created by Serloman on 22/07/2015.
 */
public interface MovieVideosCallback {
    void onMovieVideosDataReceived(List<VideoMovie> videos);
    void onError(Exception ex);
}
