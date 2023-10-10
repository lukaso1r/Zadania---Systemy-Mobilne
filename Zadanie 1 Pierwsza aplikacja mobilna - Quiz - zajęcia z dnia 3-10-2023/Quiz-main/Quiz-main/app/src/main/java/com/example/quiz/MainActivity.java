package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView question_text_view, question, points;
    Button false_button, true_button, next_button;
    int  questionIndex = 0, wynik = 0;


    boolean czyUdzielonoOdpowiedzi = false;
    private Pytanie[] pytania = new Pytanie[]{
            new Pytanie(R.string.q_Krakow, false),
            new Pytanie(R.string.q_Warszawa, false),
            new Pytanie(R.string.q_Bialystok, true),
            new Pytanie(R.string.q_Sasiedzi, true),
            new Pytanie(R.string.q_Rysy, true)
    };
    int HowManyQ = pytania.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question_text_view = findViewById(R.id.IDquestion_text_view);
        question = findViewById(R.id.IDquestions);
        points = findViewById(R.id.IDpoints);
        false_button = findViewById(R.id.IDfalse_button);
        true_button = findViewById(R.id.IDtrue_button);
        next_button = findViewById(R.id.IDnext_button);

        false_button.setOnClickListener(this);
        true_button.setOnClickListener(this);
        next_button.setOnClickListener(this);

        question.setText(getString(R.string.pytaniaIle) + HowManyQ);
        loadQuestion();
    }

    void loadQuestion(){
        question_text_view.setText(pytania[questionIndex].getIDpytanie());
    }

    @Override
    public void onClick(View view) {
        points.setText(getString(R.string.pktSuma)  + Integer.toString(wynik));
        Button clickedButton = (Button) view;
        if(clickedButton.getId() == R.id.IDnext_button){
            questionIndex++;
            if(questionIndex<pytania.length){
                czyUdzielonoOdpowiedzi = false;
                loadQuestion();
            }else{
                question_text_view.setText(getString(R.string.koniecQuizu));
                questionIndex = -1;
                wynik = 0;
            }
        } else if (clickedButton.getId() == R.id.IDtrue_button && czyUdzielonoOdpowiedzi==false) {

            if(pytania[questionIndex].getOdpowiedz()){
                wynik++;
                question_text_view.setText(getString(R.string.poprawnaOdp));
                points.setText(getString(R.string.pktSuma) + Integer.toString(wynik));
            }else if (czyUdzielonoOdpowiedzi==false){
                question_text_view.setText(getString(R.string.niepoprawnaOdp));
            }
            czyUdzielonoOdpowiedzi = true;
        }else{
            if(!pytania[questionIndex].getOdpowiedz() && czyUdzielonoOdpowiedzi==false){
                wynik++;
                question_text_view.setText(getString(R.string.poprawnaOdp));
                points.setText(getString(R.string.pktSuma)  + Integer.toString(wynik));
            }else if (czyUdzielonoOdpowiedzi==false){
                question_text_view.setText(getString(R.string.niepoprawnaOdp));
            }
            czyUdzielonoOdpowiedzi = true;
        }
    }
}