package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /* english is 0, chinese is 1.*/
    private int language = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("language")) {
            language = getIntent().getExtras().getInt("language");
        }

        Button saveBtn = findViewById(R.id.saveBtn1);
        Button loadBtn = findViewById(R.id.loadBtn0);
        Button settingBtn = findViewById(R.id.setBtn);
        Button titleBtn = findViewById(R.id.startBtn1);
        TextView textTextView = findViewById(R.id.textTextView);

        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent title = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(title);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent save = new Intent(getApplicationContext(), LoadActivity.class);
                save.putExtra("save", 0);
                startActivity(save);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(getApplicationContext(), LoadActivity.class);
                load.putExtra("load", true);
                startActivity(load);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(getApplicationContext(), SettingActivity.class);
                setting.putExtra("setting", language);
                startActivity(setting);
            }
        });
    }
}
