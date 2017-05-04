package com.example.android.movies.utilities;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tween5 on 14/4/17.
 */
public final class MoviesJsonUtils {

    public static List<Movies> getMoviesFromJson(Context context, String moviesJsonStr)
            throws JSONException {

        final String OWM_RESULTS = "results";

        final String OWM_ORIGINAL_TITLE = "original_title";
        final String OWM_POSTER_PATH = "poster_path";
        final String OWM_OVERVIEW = "overview";
        final String OWM_VOTE_AVERAGE = "vote_average";
        final String OWM_RELEASE_DATE = "release_date";

        List<Movies> moviesList = new ArrayList<>();

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        try {
                JSONArray moviesArray = moviesJson.getJSONArray(OWM_RESULTS);
                for (int i = 0; i < moviesArray.length(); i++) {
                    String original_title;
                    String poster_path;
                    String overview;
                    String vote_average;
                    String release_date;

                    JSONObject eachMovie = moviesArray.getJSONObject(i);

                    original_title = eachMovie.getString(OWM_ORIGINAL_TITLE);
                    poster_path = NetworkUtils.buildUrlImage(eachMovie.getString(OWM_POSTER_PATH)).toString();
                    overview = eachMovie.getString(OWM_OVERVIEW);
                    vote_average = eachMovie.getString(OWM_VOTE_AVERAGE);
                    release_date = eachMovie.getString(OWM_RELEASE_DATE);

                    Movies thisMovie = new Movies(original_title, poster_path, overview, vote_average, release_date);

                    moviesList.add(thisMovie);
                }
            }catch (JSONException e){
                Log.e("NetworksUtils", "Problem parsing the movies JSON results", e);
            }
        return moviesList;
        }
}