package com.example.scott.cs125project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LoadActivity extends AppCompatActivity {
    private static int plot1, plot2, plot3;
    private static int option1, option2, option3;
    private static String name1, name2, name3;
    private static boolean[] conditions1,conditions2,conditions3;
    private final String TAG = "loading activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        SharedPreferences sharedPref = getSharedPreferences("myLoadSettings", MODE_PRIVATE);
        plot1 = sharedPref.getInt("plot1", 0);
        plot2 = sharedPref.getInt("plot2", 0);
        plot3 = sharedPref.getInt("plot3", 0);
        option1 = sharedPref.getInt("option1", 0);
        option2 = sharedPref.getInt("option2", 0);
        option3 = sharedPref.getInt("option3", 0);
        name1 = sharedPref.getString("name1", "Unknown");
        name2 = sharedPref.getString("name2", "Unknown");
        name3 = sharedPref.getString("name3", "Unknown");
        conditions1 = Helper.retrieveConditions(sharedPref, "conditions1");
        conditions2 = Helper.retrieveConditions(sharedPref, "conditions2");
        conditions3 = Helper.retrieveConditions(sharedPref, "conditions3");

        final Intent load = new Intent(getApplicationContext(), MainActivity.class);

        final Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save") || getIntent().hasExtra("load")) {
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

        final Helper temp = new Helper(getApplicationContext());
        temp.setImage(plot1, plot2, plot3, loadBtn1, loadBtn2, loadBtn3);

        loadBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("save")) { // save
                    plot1 = getIntent().getExtras().getInt("plot");
                    option1 = getIntent().getExtras().getInt("option");
                    name1 = getIntent().getExtras().getString("name");
                    conditions1 = getIntent().getExtras().getBooleanArray("conditions");
                    temp.setImage(loadBtn1, plot1);
                } else { // load
                    if (plot1 != 0) {
                        load.putExtra("plot", plot1);
                        load.putExtra("option", option1);
                        load.putExtra("name", name1);
                        load.putExtra("conditions", conditions1);
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
                    name2 = getIntent().getExtras().getString("name");
                    conditions2 = getIntent().getExtras().getBooleanArray("conditions");
                    temp.setImage(loadBtn2, plot2);
                } else { // load
                    if (plot2 != 0) {
                        load.putExtra("plot", plot2);
                        load.putExtra("option", option2);
                        load.putExtra("name", name2);
                        load.putExtra("conditions", conditions2);
                        startActivity(load);
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
                    name3 = getIntent().getExtras().getString("name");
                    conditions3 = getIntent().getExtras().getBooleanArray("conditions");
                    temp.setImage(loadBtn3, plot3);
                } else { // load
                    if (plot3 != 0) {
                        load.putExtra("plot", plot3);
                        load.putExtra("option", option3);
                        load.putExtra("name", name3);
                        load.putExtra("conditions", conditions3);
                        startActivity(load);
                    }
                }
            }
        });
        Log.d(TAG, "plot is: " + plot1 + " " + plot2 + " " + plot3);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref = getSharedPreferences("myLoadSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("plot1", plot1);
        editor.putInt("plot2", plot2);
        editor.putInt("plot3", plot3);
        editor.putInt("option1", option1);
        editor.putInt("option2", option2);
        editor.putInt("option3", option3);
        editor.putString("name1", name1);
        editor.putString("name1", name2);
        editor.putString("name1", name3);
        Log.d(TAG, "data stored");
        Helper.commitConditions(editor, conditions1, "conditions1");
        Helper.commitConditions(editor, conditions2, "conditions2");
        Helper.commitConditions(editor, conditions3, "conditions3");
        editor.apply();
    }
}
