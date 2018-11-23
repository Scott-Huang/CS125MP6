package com.example.scott.cs125project;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* english is 0, chinese is 1.*/
    private int language = 0;
    private static int plot = 0;
    private static int option = 0;
    private int textSize = 24;
    private int count = 0;
    private String names = "Unknown";
    private boolean selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] scripts_en = getResources().getStringArray(R.array.scripts_en);
        final String[] scripts_cn;
        final String[] characters = getResources().getStringArray(R.array.characters);
        final String[] choice1 = getResources().getStringArray(R.array.option0);
        final String[] choice2 = getResources().getStringArray(R.array.option1);

        final List conditions = new ArrayList<Boolean>();

        final Intent title = new Intent(getApplicationContext(), StartActivity.class);
        final Intent save = new Intent(getApplicationContext(), LoadActivity.class);
        final Intent load = new Intent(getApplicationContext(), LoadActivity.class);
        final Intent setting = new Intent(getApplicationContext(), SettingActivity.class);

        final Button saveBtn = findViewById(R.id.saveBtn1);
        final Button loadBtn = findViewById(R.id.loadBtn0);
        final Button settingBtn = findViewById(R.id.setBtn);
        final Button titleBtn = findViewById(R.id.startBtn1);
        final Button choiceBtn1 = findViewById(R.id.choiceBtn1);
        final Button choiceBtn2 = findViewById(R.id.choiceBtn2);

        final ConstraintLayout layout = findViewById(R.id.layout);

        final TextView textTextView = findViewById(R.id.textTextView);
        final TextView nameTextView = findViewById(R.id.nameTextView);
        final TextView name = findViewById(R.id.nameTextViewDisplay);

        final EditText nameEditText = findViewById(R.id.nameEditText);

        if (getIntent().hasExtra("language")) {
            language = getIntent().getExtras().getInt("language");
        }
        if (getIntent().hasExtra("plot")) {
            plot = getIntent().getExtras().getInt("plot");
        }
        if (getIntent().hasExtra("option")) {
            option = getIntent().getExtras().getInt("option");
        }

        textTextView.setTextSize(textSize);

        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(title);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.putExtra("save", plot);
                startActivity(save);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.putExtra("load", true);
                startActivity(load);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.putExtra("setting", language);
                setting.putExtra("plot", plot);
                startActivity(setting);
            }
        });
        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditions.add(true);
                selected = true;
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditions.add(false);
                selected = true;
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                plot++;
                if (plot == 2) {
                    Helper.setVisibility(true, name,nameEditText);
                } else if (plot == 3) {
                    names = nameEditText.getText().toString();
                    if (names.equals("") && count < 5) {
                        textTextView.setText(getResources().getString(R.string.require_name) + Helper.createExc(count));
                        nameTextView.setText(names);
                        plot = 2;
                        count++;
                    } else if (names.equals("")) {
                        textTextView.setText(scripts_en[plot - 1] + getResources().getString(R.string.reflection0));
                        Helper.setVisibility(false, name, nameEditText);
                        names = "Bland";
                        nameTextView.setText(names);
                        count = 0;
                    } else {
                        Helper.setVisibility(false, name, nameEditText);
                        nameTextView.setText(names);
                    }
                } else if (plot == 9) {
                    //plot = 0;
                    //startActivity(title);
                } else if (plot == 14) {
                    if (!selected && conditions.size() != 1) {
                        plot -= 1;
                        textTextView.setText(getResources().getString(R.string.require_selection));
                        Helper.clearNameTag(nameTextView);
                    } else {
                        selected = false;
                        if (!(boolean) conditions.get(0)) {
                            plot = 17;
                            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                            plot++;
                        }
                    }
                } else if (plot == 20) {
                    if (!selected && conditions.size() != 2) {
                        plot -= 1;
                        textTextView.setText(getResources().getString(R.string.require_selection));
                        Helper.clearNameTag(nameTextView);
                    } else {
                        selected = false;
                        if (!(boolean) conditions.get(1)) {
                            plot = 22;
                            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                            plot++;
                        }
                    }
                } else if (plot == 21 && (boolean) conditions.get(1)) {
                    //plot = 0;
                    //startActivity(title);
                }
                Helper.setBtn(choice1, choiceBtn1, plot, option);
                Helper.setBtn(choice2, choiceBtn2, plot, option);
                if (plot == 17 && (boolean) conditions.get(0)) {
                    plot =13;
                }
            }
        });
    }
}