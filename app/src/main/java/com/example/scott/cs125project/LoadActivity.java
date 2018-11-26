package com.example.scott.cs125project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {
    private static int plot1, plot2, plot3, plot4, plot5;
    private static int option1, option2, option3, option4, option5;
    private static List<Boolean> conditions1,conditions2,conditions3,conditions4,conditions5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        final Intent load = new Intent(getApplicationContext(), MainActivity.class);

        conditions1 = new ArrayList<>();
        conditions2 = new ArrayList<>();
        conditions3 = new ArrayList<>();
        conditions4 = new ArrayList<>();
        conditions5 = new ArrayList<>();

        final Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save") || getIntent().hasExtra("load")) {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                } else {
                    Intent start = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(start);
                }
            }
        });
        final ImageButton loadBtn1 = findViewById(R.id.loadBtn0);
        final ImageButton loadBtn2 = findViewById(R.id.loadBtn2);
        final ImageButton loadBtn3 = findViewById(R.id.loadBtn3);
        final ImageButton loadBtn4 = findViewById(R.id.loadBtn4);
        final ImageButton loadBtn5 = findViewById(R.id.loadBtn5);

        loadBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot1 = getIntent().getExtras().getInt("save");
                    option1 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions1, getIntent().getExtras().getBooleanArray("conditions"));
                    Helper temp = new Helper(getApplicationContext());
                    temp.setImage(loadBtn1, plot1);
                } else { // load

                }
            }
        });
        loadBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot2 = getIntent().getExtras().getInt("save");
                    option2 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions2, getIntent().getExtras().getBooleanArray("conditions"));
                    Helper temp = new Helper(getApplicationContext());
                    temp.setImage(loadBtn2, plot2);
                } else { // load

                }
            }
        });
        loadBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot3 = getIntent().getExtras().getInt("save");
                    option3 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions3, getIntent().getExtras().getBooleanArray("conditions"));
                    Helper temp = new Helper(getApplicationContext());
                    temp.setImage(loadBtn3, plot3);
                } else { // load

                }
            }
        });
        loadBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot4 = getIntent().getExtras().getInt("save");
                    option4 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions4, getIntent().getExtras().getBooleanArray("conditions"));
                    Helper temp = new Helper(getApplicationContext());
                    temp.setImage(loadBtn4, plot4);
                } else { // load

                }
            }
        });
        loadBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot5 = getIntent().getExtras().getInt("save");
                    option5 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions5, getIntent().getExtras().getBooleanArray("conditions"));
                    Helper temp = new Helper(getApplicationContext());
                    temp.setImage(loadBtn5, plot5);
                } else { // load

                }
            }
        });
    }
}
