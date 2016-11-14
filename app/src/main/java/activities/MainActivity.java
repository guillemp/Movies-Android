package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.guillemp.movies.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.MovieAdapter;
import cz.msebera.android.httpclient.Header;
import models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, 0, movies);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(movieAdapter);

        getRecentMovies();
    }

    private void getRecentMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://guillemp.com:8000/api/v1/movies/", new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.d("MOVIES", "Start");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("MOVIES", "Success");
                Movie.moviesFromResponse(movies, response);
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("MOVIES", "Failure");
            }
        });
    }
}
