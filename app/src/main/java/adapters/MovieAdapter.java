package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guillemp.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, int resource, List<Movie> movies) {
        super(context, resource, movies);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movies.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_movie, null);

        // layout views
        ImageView image = (ImageView) view.findViewById(R.id.poster);
        Picasso.with(context).load(movie.getPoster()).into(image);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(movie.getTitle());

        return view;
    }
}
