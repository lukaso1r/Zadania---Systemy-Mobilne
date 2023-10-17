package com.example.quiz_cykl_zycia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private boolean correctAnswer;
    TextView textVeiwShowAnswerWIDOK;
    Button buttonShowAnswerBUTTON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        textVeiwShowAnswerWIDOK = findViewById(R.id.TextVeiwShowAnswer);
        buttonShowAnswerBUTTON = findViewById(R.id.buttonShowAnswer);

        buttonShowAnswerBUTTON.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(buttonShowAnswerBUTTON.getId() == R.id.buttonShowAnswer) {
            int answer = correctAnswer ? R.string.button_true : R.string.button_false;
            textVeiwShowAnswerWIDOK.setText(answer);
        }
    }
}