package com.example.quizapp;

import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button btn_submit;

    int score=0;
    int totalQuestion=QuestionAns.question.length;
    int currentQuestionIndex=0;
    String selectedAns="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        btn_submit=findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);


        totalQuestionTextView.setText("Total Questions: "+totalQuestion);

        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishedQuiz();
            return;
        }
        questionTextView.setText(QuestionAns.question[currentQuestionIndex]);
        ansA.setText(QuestionAns.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAns.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAns.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAns.choices[currentQuestionIndex][3]);

        selectedAns="";
    }
    private void finishedQuiz(){
        String passStatus;
        if(score >= totalQuestion*0.6){
            passStatus="Passed the Quiz";
        }else{
            passStatus="Failed the Quiz";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is "+score+" Out of "+totalQuestion)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;

        if(clickedButton.getId() == R.id.btn_submit){
            if(!selectedAns.isEmpty()){
                if(selectedAns.equals(QuestionAns.correctAns[currentQuestionIndex])){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }else{

            }
        }else{
            selectedAns=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }

    }
}