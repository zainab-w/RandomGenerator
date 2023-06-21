package com.example.randomgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent mainPage = new Intent(MainActivity.this,HomePage.class);
                    startActivity(mainPage); //create navigation drawer page and call it MainPage
                    finish();
                }
            }
        };
        thread.start();
    }
}