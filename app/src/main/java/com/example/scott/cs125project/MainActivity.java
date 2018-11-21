package com.example.scott.cs125project;

import android.content.Intent;
import android.nfc.Tag;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /* english is 0, chinese is 1.*/
    private int language = 0;
    private static int plot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] script = getResources().getStringArray(R.array.scripts);

        if (getIntent().hasExtra("language")) {
            language = getIntent().getExtras().getInt("language");
        }
        if (getIntent().hasExtra("plot")) {
            plot = getIntent().getExtras().getInt("plot");
        }

        Button saveBtn = findViewById(R.id.saveBtn1);
        Button loadBtn = findViewById(R.id.loadBtn0);
        Button settingBtn = findViewById(R.id.setBtn);
        Button titleBtn = findViewById(R.id.startBtn1);
        final TextView textTextView = findViewById(R.id.textTextView);

        textTextView.setTextSize(22);
        if (plot < script.length) {
            textTextView.setText(script[plot]);
        }

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
                save.putExtra("save", plot);
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
                setting.putExtra("plot", plot);
                startActivity(setting);
            }
        });
        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plot++;
                if (plot < script.length) {
                    textTextView.setText(script[plot]);
                }
            }
        });
    }
}
