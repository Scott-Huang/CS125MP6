package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {
    private boolean fromMain = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final SeekBar language = findViewById(R.id.languageSeekBar);
        final SeekBar speed = findViewById(R.id.speedSeekBar);
        final SeekBar size = findViewById(R.id.sizeSeekBar);
        if (getIntent().hasExtra("language")) {
            language.setProgress(getIntent().getExtras().getInt("language"));
        } else if (getIntent().hasExtra("setting")) {
            language.setProgress(getIntent().getExtras().getInt("setting"));
            speed.setProgress(4 - (getIntent().getExtras().getInt("speed") - 15) / 15);
            size.setProgress((getIntent().getExtras().getInt("textSize") - 10) / 5);
            fromMain = true;
        }
        Button back = findViewById(R.id.backBtn1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fromMain) {
                    Intent start = new Intent(getApplicationContext(), StartActivity.class);
                    start.putExtra("language", language.getProgress());
                    startActivity(start);
                } else {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    main.putExtra("language", language.getProgress());
                    main.putExtra("conditions", getIntent().getExtras().getBooleanArray("conditions"));
                    main.putExtra("textSize", size.getProgress() * 5 + 10);
                    main.putExtra("speed", (4 - size.getProgress()) * 15 + 15);
                    startActivity(main);
                }
            }
        });

    }
}
