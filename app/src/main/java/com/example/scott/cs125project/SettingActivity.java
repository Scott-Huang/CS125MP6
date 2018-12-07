package com.example.scott.cs125project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {
    private int speedInt, sizeInt, langu;
    private final String TAG = "setting activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences sharedPref =
                getSharedPreferences("mySettingSettings", MODE_PRIVATE);
        speedInt = sharedPref.getInt("speed", 3);
        sizeInt = sharedPref.getInt("textSize", 3);
        langu = sharedPref.getInt("language", 0);
        if (sharedPref.contains("speed")) {
            Log.d(TAG, "data assigned");
        }

        final SeekBar language = findViewById(R.id.languageSeekBar);
        final SeekBar speed = findViewById(R.id.speedSeekBar);
        final SeekBar size = findViewById(R.id.sizeSeekBar);

        speed.setProgress(speedInt);
        size.setProgress(sizeInt);
        language.setProgress(langu);
        Log.d(TAG, "setting is: " + langu + " " + speedInt + " " + sizeInt);

        Button back = findViewById(R.id.backBtn1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getIntent().hasExtra("setting")) {
                    Intent start = new Intent(getApplicationContext(), StartActivity.class);
                    start.putExtra("language", language.getProgress());
                    start.putExtra("textSize", size.getProgress() * 5 + 10);
                    start.putExtra("speed", (4 - size.getProgress()) * 15 + 15);
                    startActivity(start);
                } else {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    main.putExtra("language", language.getProgress());
                    main.putExtra("conditions", getIntent().getExtras()
                            .getBooleanArray("conditions"));
                    main.putExtra("textSize", size.getProgress() * 5 + 10);
                    main.putExtra("speed", (4 - size.getProgress()) * 15 + 15);
                    speedInt = speed.getProgress();
                    sizeInt = size.getProgress();
                    langu = language.getProgress();
                    startActivity(main);
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref =
                getSharedPreferences("mySettingSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("speed", speedInt);
        editor.putInt("textSize", sizeInt);
        editor.putInt("language", langu);
        Log.d(TAG, "data stored");
        editor.apply();
    }
}
