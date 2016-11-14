package activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.guillemp.movies.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.MovieAdapter;
import cz.msebera.android.httpclient.Header;
import listeners.InfiniteScrollListener;
import models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private MovieAdapter movieAdapter;
    private boolean noMoreItems = false;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, 0, movies);

        dialog = new ProgressDialog(MainActivity.this, R.style.MyTheme);
        dialog.setCancelable(false);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(movieAdapter);
        listView.setOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void getMoreItems(int page, int size) {
                if (!noMoreItems) {
                    dialog.show();
                    getMovies(this, page, size);
                }
            }
        });

        //getMovies(1, 20);
    }

    private void getMovies(final InfiniteScrollListener scrollListener, final int page, final int size) {
        AsyncHttpClient client = new AsyncHttpClient();
        final String url = String.format("http://guillemp.com:8000/api/v1/movies/?page=%d&size=%d", page, size);
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.d("MOVIES", "Start: " + url);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("MOVIES", "Success");

                try {
                    int total_items = response.getInt("count");
                    if (total_items < size) {
                        noMoreItems = true;
                    }

                    Movie.moviesFromResponse(movies, response);
                    movieAdapter.notifyDataSetChanged();
                    scrollListener.setIsLoading(false);
                    dialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("MOVIES", "Failure");
                dialog.dismiss();
            }
        });
    }
}
