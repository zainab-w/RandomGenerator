package com.example.randomgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {


    Button Letter, Number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Number = (Button) findViewById(R.id.btnNumber);
        Letter = (Button) findViewById(R.id.btnLetter);


        Number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LetterPage = new Intent(HomePage.this,Number.class);
                startActivity(LetterPage); //opens letter ui
                finish();
            }
        });


    }
}


