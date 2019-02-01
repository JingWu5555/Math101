package com.example.lol.math101;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;




import java.util.ArrayList;
import java.util.Random;

public class DiffLevels extends AppCompatActivity implements View.OnClickListener{
    private int numberRange;
    private String []signs;
    private int solution;
    private Random randNumber;
    private TextView problem;
    private int counter;
    private int percent;
    private ArrayList<Button> buttons;
    AlertDialog popUp;
    private ProgressBar pointSystem;
    private Button answer, answer2, answer3, answer4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        problem = findViewById(R.id.problem);
        pointSystem = findViewById(R.id.points);
        answer = findViewById(R.id.mchoice1);
        answer2 = findViewById(R.id.mchoice2);
        answer3 = findViewById(R.id.mchoice3);
        answer4 = findViewById(R.id.mchoice4);
        buttons = new ArrayList<>();
        TextView endMessage = new TextView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Ended");
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
                System.exit(0);
            }
        });
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                generateProblem();
            }
        });
        popUp = builder.create();




        initial();


    }

    private void initial(){
       Intent level = getIntent();
        switch(level.getStringExtra("level")){
            case "Easy":
                numberRange = 10;
                break;
            case "Medium":
                numberRange = 20;
                break;
            case "Hard":
                numberRange = 30;
                break;
            default:
                numberRange = 10;
                break;
        }

        signs = new String[]{"+", "-"};
        randNumber = new Random();
        int []buttonID = new int[]{R.id.mchoice1, R.id.mchoice2, R.id.mchoice3, R.id.mchoice4};
        for(int i = 0; i < 4; i++){
            buttons.add(((Button)findViewById(buttonID[i])));
        }


        answer.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        generateProblem();
        generateAnswers();

    }

    private String randomOperation(String x, int number, int number2){
        int tempNumber;
        if(number < number){
            tempNumber = number;
            number = number2;
            number2 = tempNumber;
        }
        if(x.equalsIgnoreCase("-"))
            solution = number - number2;
        else
            solution = number + number2;
        return String.valueOf(number) + " " + x + " " + String.valueOf(number2);
    }
    private void generateProblem(){
        int number = randNumber.nextInt(numberRange);
        int number2 = randNumber.nextInt(numberRange);
        String operation = signs[randNumber.nextInt(2)];
        String temp = randomOperation(operation, number, number2);
        problem.setText(temp);
        counter++;
    }

    private void generateAnswers(){
        int randomNumber = randNumber.nextInt(5);
        for(int i = 0; i < 4; i++){
            int temp = randNumber.nextInt(21);
            buttons.get(i).setText("" + temp);
        }
        buttons.get(randomNumber).setText("" + solution);
    }
    private void checkAnswers(int userAnswer){
        if(userAnswer == solution){
            pointSystem.incrementProgressBy(1);
        }

    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            checkAnswers(Integer.parseInt(((Button)v).getText().toString()));
            generateProblem();
            generateAnswers();

        }
    }
}
