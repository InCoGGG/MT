package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setting = findViewById(R.id.mainMenuSetting);
        Button exit = findViewById(R.id.mainMenuExit);
        Button start = findViewById(R.id.mainMenuStart);
        setting.setOnClickListener(this);
        exit.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mainMenuSetting : {
                Intent intent = new Intent(MainActivity.this, com.example.myapplication.SettingsActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.mainMenuExit : {
                finish();
                break;
            }

            case R.id.mainMenuStart : {
                SharePrefHelper sharePrefHelper = new SharePrefHelper(MainActivity.this);
                String gameSizeValue = sharePrefHelper.getGameSize();
                switch (gameSizeValue) {
                    case "4": {
                        Intent intent = new Intent(MainActivity.this, Game1Activity.class);
                        startActivity(intent);
                        break;
                    }
                    case "5": {
                        Intent intent = new Intent(MainActivity.this, Game2Activity.class);
                        startActivity(intent);
                        break;
                    }
                    case "6": {
                        Intent intent = new Intent(MainActivity.this, Game3Activity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        }
    }
}
