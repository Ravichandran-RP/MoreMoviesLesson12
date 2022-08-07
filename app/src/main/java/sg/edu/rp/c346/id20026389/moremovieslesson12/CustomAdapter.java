package sg.edu.rp.c346.id20026389.moremovieslesson12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList= objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivRatings = rowView.findViewById(R.id.imageViewRatings);

        Movie currentMovie = movieList.get(position);
        tvName.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(currentMovie.getYear() + "");
        if (currentMovie.getRating().equalsIgnoreCase("G")) {
            ivRatings.setImageResource(R.drawable.rating_g);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("PG")) {
            ivRatings.setImageResource(R.drawable.rating_pg);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("NC16")) {
            ivRatings.setImageResource(R.drawable.rating_nc16);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("PG13")) {
            ivRatings.setImageResource(R.drawable.rating_pg13);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("M18")) {
            ivRatings.setImageResource(R.drawable.rating_m18);
        }
        else if (currentMovie.getRating().equalsIgnoreCase("R21")) {
            ivRatings.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
