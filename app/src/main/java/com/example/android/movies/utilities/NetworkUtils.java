package com.example.android.movies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by tween5 on 31/3/17.
 */

public class NetworkUtils {

    final static String BASE_URL = "http://api.themoviedb.org";

    final static String POPULAR_BASE_URL = "http://api.themoviedb.org/3/movie";

    final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";

    /*
    the key is on README file
     */
    private static final String key = "";
    final static String PARAM_KEY = "api_key";

    final static String PARAM_SIZE = "w185";

    public static URL buildUrlPopularMovies(String param) {
        Uri builtUri = Uri.parse(POPULAR_BASE_URL).buildUpon()
                .appendPath(param)
                .appendQueryParameter(PARAM_KEY, key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildUrlImage(String stringImage) {

        stringImage = stringImage.substring(1);
        Uri builtUri = Uri.parse(IMAGE_BASE_URL).buildUpon().appendPath(PARAM_SIZE).appendPath(stringImage).build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
