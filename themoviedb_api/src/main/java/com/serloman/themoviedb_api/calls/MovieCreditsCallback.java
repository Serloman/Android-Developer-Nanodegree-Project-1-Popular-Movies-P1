package com.serloman.themoviedb_api.calls;

import com.serloman.themoviedb_api.models.CreditsMovie;
import com.serloman.themoviedb_api.models.CreditsMovieApi;

/**
 * Created by Serloman on 22/07/2015.
 */
public interface MovieCreditsCallback {
    void onMovieCreditsDataReceived(CreditsMovie credits);
    void onError(Exception ex);
}
