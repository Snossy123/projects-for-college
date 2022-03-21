package com.example.quizapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    EditText topic_name, num_Of_question, create_question;
    EditText sol1, sol2, sol3, sol4, enter_solution;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        topic_name = findViewById(R.id.v_topic_name);
        num_Of_question = findViewById(R.id.v_num_question);
        create_question = findViewById(R.id.v_create_question);
        sol1 = findViewById(R.id.v_sol1);
        sol2 = findViewById(R.id.v_sol2);
        sol3 = findViewById(R.id.v_sol3);
        sol4 = findViewById(R.id.v_sol4);
        enter_solution = findViewById(R.id.v_enter_solution);

        String Topic_Name = topic_name.getText().toString();
        String Create_Question = create_question.getText().toString();
        String Sol1 = sol1.getText().toString();
        String Sol2 = sol2.getText().toString();
        String Sol3 = sol3.getText().toString();
        String Sol4 = sol4.getText().toString();
        String Enter_Solution = enter_solution.getText().toString();
        double Num_OF_Question = Double.parseDouble(num_Of_question.getText().toString());



        if(Topic_Name.isEmpty() || Create_Question.isEmpty() || Sol1.isEmpty()
         || Sol2.isEmpty() || Sol3.isEmpty() || Sol4.isEmpty() || Enter_Solution.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the missing value! ", Toast.LENGTH_LONG).show();
        }
        else{

        }



    }
}