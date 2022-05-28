package com.example.quizapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//    main  declaration variables
    private Button startQ;
//    quiz name
    private  String QuizName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        quiz name
        QuizName = "PHP";
//        start controls
        startQ = findViewById(R.id.start);

//      click button action
        startQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent  = new Intent(MainActivity.this, quizbody.class);
               intent.putExtra("Quizname", QuizName) ;
               startActivity(intent);
            }
        });



    }
}