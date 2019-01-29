package com.example.lol.math101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;






public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button easy, hard;
    private String levelDiff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easy = findViewById(R.id.easyButton);
        hard = findViewById(R.id.mediumButton);
        easy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent goToLevel = new Intent(this, DiffLevels.class);

        switch(v.getId()){
            case R.id.easyButton:
                levelDiff = "Easy";
                break;
            case R.id.mediumButton:
                levelDiff = "Medium";
                break;
            case  R.id.hardButton:
                levelDiff = "Hard";
                break;
        }
        goToLevel.putExtra("level", levelDiff);
        startActivity(goToLevel);

    }





}