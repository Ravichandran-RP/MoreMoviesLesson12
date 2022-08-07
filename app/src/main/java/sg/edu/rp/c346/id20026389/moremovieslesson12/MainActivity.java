package sg.edu.rp.c346.id20026389.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle,tvGenre,tvYear;
    EditText etTitle,etGenre,etYear;
    Button btnInsert,btnShow;
    Spinner spnRating;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=findViewById(R.id.tvMovieTitle);
        tvGenre=findViewById(R.id.tvGenre);
        tvYear=findViewById(R.id.tvYear);
        etTitle=findViewById(R.id.etMovieTitle);
        etGenre=findViewById(R.id.etGenre);
        etYear=findViewById(R.id.etYear);
        btnInsert=findViewById(R.id.btnUpdate);
        btnShow=findViewById(R.id.btnDelete);
        spnRating=findViewById(R.id.spinnerRating);


        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        rating="G";
                        break;
                    case 1:
                        rating="PG";
                        break;
                    case 2:
                        rating="PG13";
                        break;
                    case 3:
                        rating="NC16";
                        break;
                    case 4:
                        rating="M18";
                        break;
                    case 5:
                        rating="R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbh = new DBHelper(MainActivity.this);
                if (etTitle.getText().toString().trim().length()==0 ||
                        etGenre.getText().toString().trim().length()==0 ||
                        etYear.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this, "Please fill up all the fields",
                            Toast.LENGTH_LONG).show();
                }
                else if(Integer.parseInt(etYear.getText().toString().trim())>2022 || Integer.parseInt(etYear.getText().toString().trim())<=0){
                    Toast.makeText(MainActivity.this, "Please enter a valid year",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    String movieTitle = etTitle.getText().toString().trim();
                    String genre = etGenre.getText().toString().trim();
                    int year = Integer.parseInt(etYear.getText().toString().trim());

                    long inserted_id = dbh.insertMovie(movieTitle,genre,year,rating);
                    if (inserted_id != -1) {
//                    al.clear();
//                    al.addAll(dbh.getAllNotes());
//                    aa.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Insert failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });


    }

}