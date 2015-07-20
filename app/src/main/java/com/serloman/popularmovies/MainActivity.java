package com.serloman.popularmovies;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

    public final static int TYPE_POPULARITY = 0;
    public final static int TYPE_RATED = 1;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        selectFragment(TYPE_POPULARITY);
    }

    private void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbarMain);

        this.setSupportActionBar(mToolbar);
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

        switch (id){
            case R.id.action_sort_by_popularity:
                selectFragment(TYPE_POPULARITY);
                return true;
            case R.id.action_sort_by_most_rated:
                selectFragment(TYPE_RATED);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectFragment(int type){

        Fragment fragment;

        switch (type){

            case TYPE_POPULARITY:
                fragment = PopularMoviesFragment.newInstance();
                break;
            case TYPE_RATED:
                fragment = RatedMoviesFragment.newInstance();
                break;

            default:
                fragment = PopularMoviesFragment.newInstance();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        updateSubtitle(type);
    }

    private void updateSubtitle(int type){
        String title;

        switch (type){
            case TYPE_POPULARITY:
                title = getString(R.string.label_popularity);
                break;
            case TYPE_RATED:
                title = getString(R.string.label_most_rated);
                break;
            default:
                title = "undefined";
                break;
        }

        mToolbar.setSubtitle(title);
    }
}
