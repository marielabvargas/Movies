package com.example.android.movies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movies.utilities.Movies;
import com.squareup.picasso.Picasso;

/**
 * Created by tween5 on 20/4/17.
 */

public class DetailActivity extends AppCompatActivity {
    private Movies mDetailForMovie;

    private TextView mOriginalTitleMovie;
    private ImageView mPosterPathMovie;
    private  TextView mOverviewMovie;
    private  TextView mVoteAverageMovie;
    private TextView mReleaseDateMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOriginalTitleMovie = (TextView) findViewById(R.id.tv_original_title);
        mPosterPathMovie =(ImageView) findViewById(R.id.iv_poster_path);
        mOverviewMovie = (TextView) findViewById(R.id.tv_overview);
        mVoteAverageMovie = (TextView) findViewById(R.id.tv_vote_average);
        mReleaseDateMovie = (TextView) findViewById(R.id.tv_release_date);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("detailForMovie")) {
                mDetailForMovie = (Movies)intentThatStartedThisActivity.getSerializableExtra("detailForMovie");

                mOriginalTitleMovie.setText(mDetailForMovie.getOriginalTitleMovie());
                Picasso.with(mPosterPathMovie.getContext()).load(mDetailForMovie.getPosterPathMovie()).into(mPosterPathMovie);
                mOverviewMovie.setText(mDetailForMovie.getOverviewMovie());
                mVoteAverageMovie.setText(mDetailForMovie.getVoteAverageMovie());

                String yearMovie = mDetailForMovie.getReleaseDateMovie().substring(0,4);
                mReleaseDateMovie.setText(yearMovie);
            }
        }
    }
}
