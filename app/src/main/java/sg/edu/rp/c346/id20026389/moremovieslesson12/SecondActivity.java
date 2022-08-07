package sg.edu.rp.c346.id20026389.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button btnShowMovies;
    Button back;
    ListView lvMovie;
    ArrayList<Movie>alMovieList;
    CustomAdapter caMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShowMovies = findViewById(R.id.btnShow);
        back = findViewById(R.id.buttonBack);
        lvMovie = findViewById(R.id.listViewShowMovies);
        alMovieList = new ArrayList<>();
        caMovie = new CustomAdapter(this, R.layout.row, alMovieList);
        DBHelper dbh = new DBHelper(SecondActivity.this);
        alMovieList.addAll(dbh.getAllMovies());
        lvMovie.setAdapter(caMovie);
        alMovieList.clear();
        alMovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();


//        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //               Intent i = new Intent(SecondActivity.this,
        //                       ThirdActivity.class);
        //               startActivity(i);
        //           }
        //       });

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent (SecondActivity.this, ThirdActivity.class);
                i.putExtra("movie", alMovieList.get(position));
                startActivity(i);
            }
        });

//        btnShowMovies.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < alMovieList.size(); i++) {
//                    if (alMovieList.get(i).getRating().equalsIgnoreCase("PG13")) {
//                        alMovieList.clear();
//                        alMovieList.add(i);
//                    }
//                }
//
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(SecondActivity.this,
                //MainActivity.class);
                //startActivity(i);
                finish();
            }
        });

    }
}