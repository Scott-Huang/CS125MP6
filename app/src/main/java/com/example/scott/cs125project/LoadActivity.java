package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* we may need it since there may be some bugs in the future.
                if (getIntent().hasExtra("save") || getIntent().hasExtra("load")) {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                } else {
                    Intent start = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(start);
                }
                */
                finish();
                System.exit(0);
            }
        });
        ImageButton loadBtn1 = findViewById(R.id.loadBtn0);
        ImageButton loadBtn2 = findViewById(R.id.loadBtn2);
        ImageButton loadBtn3 = findViewById(R.id.loadBtn3);
        ImageButton loadBtn4 = findViewById(R.id.loadBtn4);
        ImageButton loadBtn5 = findViewById(R.id.loadBtn5);

        loadBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load1 = new Intent(getApplicationContext(), MainActivity.class);
                load1.putExtra("plot", 1);
                startActivity(load1);
            }
        });
    }
}
