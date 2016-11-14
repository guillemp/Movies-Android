package models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int movieId;
    private String title;
    private String originalTitle;
    private String tagline;
    private String overview;
    private String releaseDate;
    private String originalLanguage;
    private String runtime;
    private String imdbId;
    private float imdbRating;
    private int imdbVotes;
    private String imdbLink;
    private String tmdbLink;
    private String poster;
    private String backdrop;

    public Movie() {
    }

    public Movie(int movieId, String title, String originalTitle, String tagline, String overview, String releaseDate, String originalLanguage, String runtime, String imdbId, float imdbRating, int imdbVotes, String imdbLink, String tmdbLink, String poster, String backdrop) {
        this.movieId = movieId;
        this.title = title;
        this.originalTitle = originalTitle;
        this.tagline = tagline;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.runtime = runtime;
        this.imdbId = imdbId;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbLink = imdbLink;
        this.tmdbLink = tmdbLink;
        this.poster = poster;
        this.backdrop = backdrop;
    }

    public static void moviesFromResponse(List<Movie> movies, JSONObject response) {
        try {
            JSONArray moviesArray = response.getJSONArray("movies");
            for (int i=0; i<moviesArray.length(); i++) {
                JSONObject movieObject = moviesArray.getJSONObject(i);
                Movie movie = new Movie(
                        movieObject.getInt("id"),
                        movieObject.getString("title"),
                        movieObject.getString("original_title"),
                        movieObject.getString("tagline"),
                        movieObject.getString("overview"),
                        movieObject.getString("release_date"),
                        movieObject.getString("original_language"),
                        movieObject.getString("runtime"),
                        movieObject.getString("imdb_id"),
                        movieObject.getInt("imdb_rating"),
                        movieObject.getInt("imdb_votes"),
                        movieObject.getString("imdb_link"),
                        movieObject.getString("tmdb_link"),
                        movieObject.getString("poster"),
                        movieObject.getString("backdrop")
                );
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getImdbId() {
        return imdbId;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public String getTmdbLink() {
        return tmdbLink;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }
}
