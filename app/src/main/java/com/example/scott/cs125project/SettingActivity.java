package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final SeekBar language = findViewById(R.id.languageSeekBar);
        if (getIntent().hasExtra("language")) {
            language.setProgress(getIntent().getExtras().getInt("language"));
        }
        Button back = findViewById(R.id.backBtn1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(), StartActivity.class);
                start.putExtra("language", language.getProgress());
                startActivity(start);
            }
        });

    }
}
