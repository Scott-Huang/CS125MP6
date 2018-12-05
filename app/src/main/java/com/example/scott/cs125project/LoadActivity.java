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
    private static String name1, name2, name3, name4, name5;
    private static List<Boolean> conditions1,conditions2,conditions3,conditions4,conditions5;
    private static int textSize;
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
                    //load.putExtra("plot", getIntent().getExtras().getInt("plot"));
                    //load.putExtra("option", getIntent().getExtras().getInt("option"));
                    //load.putExtra("name", getIntent().getExtras().getString("name"));
                    load.putExtra("conditions", getIntent().getExtras().getBooleanArray("conditions"));
                    startActivity(load);
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

        final Helper temp = new Helper(getApplicationContext());

        loadBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot1 = getIntent().getExtras().getInt("plot");
                    option1 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions1, getIntent().getExtras().getBooleanArray("conditions"));
                    temp.setImage(loadBtn1, plot1);
                } else { // load
                    if (plot1 != 0) {
                        load.putExtra("plot", plot1);
                        load.putExtra("option", option1);
                        load.putExtra("conditions", Helper.transfer(conditions1));
                        startActivity(load);
                    }
                }
            }
        });
        loadBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot2 = getIntent().getExtras().getInt("plot");
                    option2 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions2, getIntent().getExtras().getBooleanArray("conditions"));
                    temp.setImage(loadBtn2, plot2);
                } else { // load
                    if (plot2 != 0) {

                    }
                }
            }
        });
        loadBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot3 = getIntent().getExtras().getInt("plot");
                    option3 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions3, getIntent().getExtras().getBooleanArray("conditions"));
                    temp.setImage(loadBtn3, plot3);
                } else { // load
                    if (plot3 != 0) {

                    }
                }
            }
        });
        loadBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot4 = getIntent().getExtras().getInt("plot");
                    option4 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions4, getIntent().getExtras().getBooleanArray("conditions"));
                    temp.setImage(loadBtn4, plot4);
                } else { // load
                    if (plot4 != 0) {

                    }
                }
            }
        });
        loadBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot5 = getIntent().getExtras().getInt("plot");
                    option5 = getIntent().getExtras().getInt("option");
                    Helper.setCondition(conditions5, getIntent().getExtras().getBooleanArray("conditions"));
                    temp.setImage(loadBtn5, plot5);
                } else { // load
                    if (plot5 != 0) {

                    }
                }
            }
        });
    }
}
