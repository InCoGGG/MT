package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String score = intent.getStringExtra("score");
        String time = intent.getStringExtra("time");

        TextView sc = findViewById(R.id.over_score);
        TextView tm = findViewById(R.id.over_time);

        sc.setText(score);
        tm.setText(time);

        Button exit = findViewById(R.id.over_menu);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
