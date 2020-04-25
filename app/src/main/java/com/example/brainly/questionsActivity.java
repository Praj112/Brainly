package com.example.brainly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class questionsActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resulttext;
    TextView sumtext;
    TextView scoreView;
    TextView timer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button again;
    int locationofcorrectanswers;
    int score  = 0;
    int numberofquestions = 0;

    public void playagain(View view){
        score = 0;
        numberofquestions = 0;
        timer.setText("30s");
        scoreView.setText("0/0");
        resulttext.setText("");
        again.setVisibility(View.INVISIBLE);
        generatequestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                again.setVisibility(View.VISIBLE);
                timer.setText("0s");
                resulttext.setText("Your Score:" + Integer.toString(score) + "/" + Integer.toString(numberofquestions));
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mplayer.start();
            }
        }.start();
    }

    public void generatequestions(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumtext.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationofcorrectanswers = rand.nextInt(4);
        answers.clear();
        int incorrectanswers;

        for (int i=0;i<4;i++)
        {
            if(i == locationofcorrectanswers)
            {
                answers.add( a + b );
            }
            else
            {
                incorrectanswers = rand.nextInt(41);
                while(incorrectanswers == a + b )
                {
                    incorrectanswers = rand.nextInt(41);
                }
                answers.add(incorrectanswers);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void chooseanswer (View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswers))){
            score++;
            resulttext.setText("Correct!");
        }
        else {
            resulttext.setText("Wrong!");
        }
        numberofquestions++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        generatequestions();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        sumtext = findViewById(R.id.sumtext);
        button0= findViewById(R.id.button0);
        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        resulttext = findViewById(R.id.resulttext);
        scoreView = findViewById(R.id.scoreView);
        timer = findViewById(R.id.timer);
        again = findViewById(R.id.again);
        playagain(findViewById(R.id.again));
    }
}
