package sg.edu.rp.c346.id20026389.moremovieslesson12;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etTitle,etGenre,etYear;
    TextView etID;
    Button btnUpdate, btnDelete, btnCancel;
    Spinner spnRating;
    String rating;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID=findViewById(R.id.etMovieID);
        etTitle=findViewById(R.id.etMovieTitle);
        etGenre=findViewById(R.id.etGenre);
        etYear=findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        spnRating=findViewById(R.id.spinnerRating);
        etID.setEnabled(false);

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

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("movie");

        etID.setText(data.get_id()+"");
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(data.getYear()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(rating);
                dbh.updateMovie(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder=new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie "+data.getTitle());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        dbh.deleteMovie(data.get_id());
                    }
                });
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog= myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder=new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DO NOT DISCARD",null);
                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });


            }
        });

    }}