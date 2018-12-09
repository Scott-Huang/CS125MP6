package com.example.scott.cs125project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        final int plot = sharedPref.getInt("plot", 0);

        final ConstraintLayout startLayout = findViewById(R.id.startLayout);

        final TextView language = findViewById(R.id.languageTextView);
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
        if (plot != 0) {
            continueBtn.setVisibility(View.VISIBLE);
        } else {
            continueBtn.setVisibility(View.INVISIBLE);
        }
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("real", true);
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
                if (plot < 58) {
                    main.putExtra("plot", 0);
                    main.putExtra("option", 0);
                    main.putExtra("conditions", new boolean[0]);
                    main.putExtra("name", "Unknown");
                } else {
                    main.putExtra("plot", 59);
                    main.putExtra("option", 5);
                    main.putExtra("conditions", new boolean[]
                            {true, false, false, false});
                    main.putExtra("name", "Bland");
                }
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
                Intent credit = new Intent(getApplicationContext(), CreditActivity.class);
                startActivity(credit);
            }
        });

        if (plot > 58) {
            Log.d(TAG, "start background changed");
            startLayout.setBackground(getResources().getDrawable(R.drawable.start_background2));
            Helper.setAllColor(Color.RED, language, continueBtn,
                    startBtn, loadBtn, settingBtn, creditBtn);
        }
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
