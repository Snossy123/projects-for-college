package com.example.quizapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class quizbody extends AppCompatActivity {

    // call db main
    MainDb maindb = new MainDb(this);

//    get questions from database

    private String tag ;
    private ImageView star;
    private TextView Q1 ;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
// get passed variables
//         = getIntent().getExtras();StringExtra("Quizname");
//        String QName
     tag = "html";
      Q1 = findViewById(R.id.questionText);
      answer = "the hupper mark";
//        star icon
        star = findViewById(R.id.star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(star.getColorFilter() == null)
                {
                    star.setColorFilter(Color.BLACK);
                    maindb.AddToFav(tag,Q1.getText().toString(),answer);

                }
                else
                {
                    star.setColorFilter(null);
                    maindb.delfromFav(Q1.getText().toString());

                }

            }
        });

    }
}