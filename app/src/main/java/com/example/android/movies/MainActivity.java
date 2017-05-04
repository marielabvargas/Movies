package com.example.android.movies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movies.utilities.Movies;
import com.example.android.movies.utilities.MoviesJsonUtils;
import com.example.android.movies.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

import static com.example.android.movies.R.menu.sort;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler{


    private MovieAdapter mMovieAdapter;
    private RecyclerView mRecyclerView;

    private Toast mToast;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    public String optionSelected = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMoviesData();
    }

    private void loadMoviesData() {
        showMoviesDataView();
        new FetchMoviesTask().execute();
    }

    @Override
    public void onClick(Movies movieClicked){
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("detailForMovie", movieClicked);
        startActivity(intentToStartDetailActivity);
    }
/*
    @Override
    public void onClick(String oneMovie) {
        Context context = this;
        //Class destinationClass = DetailActivity.class;
        //Intent intentToStartDetailActivity = new Intent(context, destinationClass);

        //intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, weatherForDay);
        //startActivity(intentToStartDetailActivity);
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item # clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
*/
    private void showMoviesDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, List<Movies>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movies> doInBackground(String... params) {
            URL moviesRequestUrl = NetworkUtils.buildUrlPopularMovies(optionSelected);

            try {
                String jsonMoviesResponse = NetworkUtils
                        .getResponseFromHttpUrl(moviesRequestUrl);

                List<Movies> moviesData = MoviesJsonUtils.getMoviesFromJson(MainActivity.this, jsonMoviesResponse);

                return moviesData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movies> moviesData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (moviesData != null) {
                showMoviesDataView();
                mMovieAdapter.setMoviesData(moviesData);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            if (optionSelected == "popular"){
                optionSelected = "top_rated";
                item.setTitle("Popular");
            }else{
                optionSelected = "popular";
                item.setTitle("Top rated");
            }
            loadMoviesData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}