package com.example.quiz_cykl_zycia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        public static final String KEY_CURRENT_INDEX = "currentIndex";
        public static final String KEY_CURRENT_WYNIK = "wynik";
        public static final String KEY_CURRENT_WYNIK_TEXT = "WYNIK_TEXT";
        TextView question_text_view, question, points;
        Button false_button, true_button, next_button, hintbutton;
        int  questionIndex = 0;
        int wynik = 0;


        private static final String TAGonCreate = "Wystąpilo onCreate";
        private static final String TAGonStart = "Wystąpilo onStart";
        private static final String TAGonResume = "Wystąpilo onResume";
        private static final String TAGonPause = "Wystąpilo onPause";
        private static final String TAGonStop = "Wystąpilo onStop";
        private static final String TAGonDestroy = "Wystąpilo onDestroy";
        private static final String QUIZ_TAG = "Wystąpilo onSaveInstanceState";
        boolean czyUdzielonoOdpowiedzi = false;


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAGonStart, "Wystąpiło onStart!----------------------------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAGonResume, "Wystąpiło onResume!----------------------------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAGonPause, "Wystąpiło onPause!----------------------------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAGonStop, "Wystąpiło onStop!----------------------------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAGonDestroy, "Wystąpiło onDestory!----------------------------------");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wystąpiło wywołanie metody: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, questionIndex);
        outState.putInt(KEY_CURRENT_WYNIK, wynik);


    }

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
            Log.d(TAGonCreate, "Wystąpiło onCreate!----------------------------------");
            setContentView(R.layout.activity_main);

            if(savedInstanceState!=null){
                questionIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
                wynik = savedInstanceState.getInt(KEY_CURRENT_WYNIK);
            }

            question_text_view = findViewById(R.id.IDquestion_text_view);
            question = findViewById(R.id.IDquestions);
            points = findViewById(R.id.IDpoints);
            false_button = findViewById(R.id.IDfalse_button);
            true_button = findViewById(R.id.IDtrue_button);
            next_button = findViewById(R.id.IDnext_button);
            hintbutton = findViewById(R.id.IDhintbutton);

            false_button.setOnClickListener(this);
            true_button.setOnClickListener(this);
            next_button.setOnClickListener(this);
            hintbutton.setOnClickListener(this);

            question.setText(getString(R.string.pytaniaIle) + HowManyQ);
            loadQuestion();
            loadPoints();

        }

        void loadQuestion(){
            question_text_view.setText(pytania[questionIndex].getIDpytanie());
        }

        void loadPoints(){
            points.setText(getString(R.string.pktSuma)  + Integer.toString(wynik));
        }

        @Override
        public void onClick(View view) {
            loadPoints();
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
                    loadPoints();
                }else if (czyUdzielonoOdpowiedzi==false){
                    question_text_view.setText(getString(R.string.niepoprawnaOdp));
                }
                czyUdzielonoOdpowiedzi = true;
            }else{
                if(!pytania[questionIndex].getOdpowiedz() && czyUdzielonoOdpowiedzi==false){
                    wynik++;
                    question_text_view.setText(getString(R.string.poprawnaOdp));
                    loadPoints();
                }else if (czyUdzielonoOdpowiedzi==false){
                    question_text_view.setText(getString(R.string.niepoprawnaOdp));
                }
                czyUdzielonoOdpowiedzi = true;
            }
        }
    }