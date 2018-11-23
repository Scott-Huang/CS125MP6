package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    /** english is 0, chinese is 1. */
    private int langu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TextView language = findViewById(R.id.languageTextView);
        language.setTextSize(20);
        if (getIntent().hasExtra("language")) {
            langu = getIntent().getExtras().getInt("language");
        }
        if (langu == 0) {
            language.setText("English");
        } else {
            language.setText("中文");
        }
        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                main.putExtra("language", langu);
                main.putExtra("plot", 0);
                main.putExtra("option", 0);
                startActivity(main);
            }
        });
        Button loadBtn = findViewById(R.id.loadBtn0);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(getApplicationContext(), LoadActivity.class);
                startActivity(load);
            }
        });
        Button settingBtn = findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(getApplicationContext(), SettingActivity.class);
                setting.putExtra("language", langu);
                startActivity(setting);
            }
        });
        Button creditBtn = findViewById(R.id.creditBtn);
        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
