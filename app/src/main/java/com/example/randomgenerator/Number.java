package com.example.randomgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Number extends AppCompatActivity {

    Button generate;
    ImageButton btnBack;
    TextView start, end, randomNumber, txtClearHistory;
    Random r;
    ListView listView;
    ArrayList<Integer> arrayList;
    int min, max, output, random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        Button btnGenerate = findViewById(R.id.btnGenerate);
        ImageButton btnBack = findViewById(R.id.btnBack);
        EditText start = findViewById(R.id.start);
        EditText end = findViewById(R.id.end);
        TextView randomNumber = findViewById(R.id.randomNumber);
        TextView txtClearHistory = findViewById(R.id.txtClearHistory);
        ListView listView = findViewById(R.id.listView);

        ArrayList arrayList = new ArrayList<>();

       /* regular list view layout
        ArrayAdapter adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);*/

        //customized list view layout
        ArrayAdapter adapter = new ArrayAdapter<Integer>(this,R.layout.listview_layout,R.id.listItem, arrayList);
        listView.setAdapter(adapter);

        //generate the random number
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minNum = start.getText().toString();//get value from EditText
                String maxNum = end.getText().toString();//get value from EditText


                if (TextUtils.isEmpty(start.getText()) || TextUtils.isEmpty(end.getText())) {  //check if the min and max values is empty
                    if (TextUtils.isEmpty(start.getText())) {  //check if the start value is empty

                        start.setError("Enter a min value!");

                    }
                    else if (TextUtils.isEmpty(end.getText())) {  //check if the end value is empty

                        end.setError("Enter a max value!");

                    }
                }
                else {
                    //convert values to int
                    int min = Integer.parseInt(minNum);
                    int max = Integer.parseInt(maxNum);


                        if (max < min) {
                            //check if the max number is bigger than the min number
                            AlertDialog.Builder builder = new AlertDialog.Builder(Number.this);
                            builder.setTitle("Error");
                            builder.setMessage("The max number cannot be smaller than the min number!");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show(); //makes the popup msg visible
                        }
                        else if (min == max) { //check if the max number is = min number

                            AlertDialog.Builder builder = new AlertDialog.Builder(Number.this);
                            builder.setTitle("Error");
                            builder.setMessage("The min number cannot be equal to the max number!");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show(); //makes the popup msg visible
                        }
                        else {
                            final int random = new Random().nextInt((max - min) + 1) + min; //returns random int based on range set
                            randomNumber.setText("" + random); //display random number in TextView

                            //add data into list and update listview
                            String historyVal = (String) randomNumber.getText().toString(); //stores random generated number into variable
                            arrayList.add(historyVal); //add data into array
                            adapter.notifyDataSetChanged(); //update listview
                        }

                }
            }
        });

        //clear listView
        txtClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList.clear();//clear the array list
                adapter.notifyDataSetChanged(); //update listview

            }
        });

        //go back to homepage
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent LetterPage = new Intent(Number.this,HomePage.class);
                startActivity(LetterPage); //opens letter ui
                finish();

            }
        });
    }
}

