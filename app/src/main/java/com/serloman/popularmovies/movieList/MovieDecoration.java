package com.serloman.popularmovies.movieList;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Serloman on 20/07/2015.
 */
public class MovieDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public MovieDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = space;
        outRect.right = space;

        int position = parent.getChildPosition(view);

        if (position % 2 == 0)
            outRect.left = space;

        // Add top margin only for the first item to avoid double space between items
        if (position < 2)
            outRect.top = space;
    }
}