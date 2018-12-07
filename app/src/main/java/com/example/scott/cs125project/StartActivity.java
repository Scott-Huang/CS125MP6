package com.example.scott.cs125project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    /** english is 0, chinese is 1. */
    private int langu;
    private final String TAG = "start activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences sharedPref =
                getSharedPreferences("mySettings", MODE_PRIVATE);
        langu = sharedPref.getInt("language", 0);

        TextView language = findViewById(R.id.languageTextView);
        final Intent main = new Intent(getApplicationContext(), MainActivity.class);
        if (getIntent().hasExtra("language")) {
            langu = getIntent().getExtras().getInt("language", 0);
        }
        if (langu == 0) {
            language.setText("English");
        } else {
            language.setText("中文");
        }
        Button continueBtn = findViewById(R.id.continueBtn);
        if (getSharedPreferences("mySettings", MODE_PRIVATE).getInt("plot", 0) != 0) {
            Helper.setVisibility(true, continueBtn);
        }
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(main);
            }
        });
        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("language")) {
                    main.putExtra("language", langu);
                    main.putExtra("speed", getIntent().getExtras().getInt("speed"));
                    main.putExtra("textSize", getIntent()
                            .getExtras().getInt("textSize"));
                }
                main.putExtra("plot", 0);
                main.putExtra("option", 0);
                main.putExtra("conditions", new boolean[0]);
                main.putExtra("name", "Unknown");
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

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences =
                getSharedPreferences("mySettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("language", langu);
        editor.apply();
    }
}
