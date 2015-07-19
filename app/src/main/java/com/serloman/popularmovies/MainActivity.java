package com.serloman.popularmovies;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.serloman.themoviedb_api.calls.MovieCallback;
import com.serloman.themoviedb_api.TheMovieDb_Api;
import com.serloman.themoviedb_api.calls.MovieListCallback;
import com.serloman.themoviedb_api.models.Movie;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test(){
        TheMovieDb_Api api = new TheMovieDb_Api(getString(R.string.the_movie_db_api_key));
        api.getMovieDataAsync("550", new MovieCallback() {
            @Override
            public void onDataReceived(Movie movie) {
                Toast.makeText(getApplicationContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error_getting_movie), Toast.LENGTH_SHORT).show();
            }
        });

        api.discoverMoviesAsync(TheMovieDb_Api.Short_By.POPULARITY_ASC, new MovieListCallback() {
            @Override
            public void onDataReceived(List<Movie> movies) {
                for (Movie movie : movies)
                    Log.d("MovieData", movie.getTitle());
            }

            @Override
            public void onError(Exception ex) {

            }
        });
    }


}
