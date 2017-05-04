package com.example.android.movies.utilities;

import java.io.Serializable;

/**
 * Created by tween5 on 20/4/17.
 */

public class Movies implements Serializable {
    String originalTitleMovie;
    String overviewMovie;
    String voteAverageMovie;
    String releaseDateMovie;
    String posterPathMovie;

    public Movies(String title, String posterPath, String overview, String voteAverage, String releaseDate)
    {
        this.originalTitleMovie = title;
        this.overviewMovie = overview;
        this.voteAverageMovie = voteAverage;
        this.releaseDateMovie = releaseDate;
        this.posterPathMovie = posterPath;
    }

    public String getOriginalTitleMovie() {
        return originalTitleMovie;
    }

    public String getOverviewMovie() {
        return overviewMovie;
    }

    public String getVoteAverageMovie(){
        return voteAverageMovie;
    }

    public String getReleaseDateMovie(){
        return releaseDateMovie;
    }

    public String getPosterPathMovie(){
        return  posterPathMovie;
    }
}
