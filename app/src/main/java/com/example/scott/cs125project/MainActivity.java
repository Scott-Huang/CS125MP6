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

        final String[] scripts = getResources().getStringArray(R.array.scripts);
        final String[] characters = getResources().getStringArray(R.array.characters);
        final String[] choice1 = getResources().getStringArray(R.array.option0);
        final String[] choice2 = getResources().getStringArray(R.array.option1);
        final Intent title = new Intent(getApplicationContext(), StartActivity.class);
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

        final List conditions = new ArrayList<Boolean>();

        textTextView.setTextSize(textSize);
        if (plot < scripts.length) {
            textTextView.setText(scripts[plot]);
        }

        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(title);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent save = new Intent(getApplicationContext(), LoadActivity.class);
                save.putExtra("save", plot);
                startActivity(save);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(getApplicationContext(), LoadActivity.class);
                load.putExtra("load", true);
                startActivity(load);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(getApplicationContext(), SettingActivity.class);
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
                choiceBtn1.setVisibility(View.INVISIBLE);
                choiceBtn2.setVisibility(View.INVISIBLE);
                option++;
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditions.add(false);
                selected = true;
                choiceBtn1.setVisibility(View.INVISIBLE);
                choiceBtn2.setVisibility(View.INVISIBLE);
                option++;
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plot++;
                if (plot < scripts.length) {
                    textTextView.setText(scripts[plot]);
                    /* delay the display time
                    try {
                        Thread.sleep(1000); //1000 milliseconds is one second.
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    */
                }
                if (plot < characters.length) {
                    if (characters[plot].equals("")) {
                        nameTextView.setText(names);
                    } else {
                        nameTextView.setText(characters[plot]);
                    }
                }
                if (plot == 1) {
                    nameEditText.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                } else if (plot == 2) {
                    names = nameEditText.getText().toString();
                    if (names.equals("") && count < 8) {
                        textTextView.setText(getResources().getString(R.string.require_name) + Helper.createExc(count));
                        nameTextView.setText(names);
                        plot = 0;
                        count++;
                    } else if (names.equals("")) {
                        textTextView.setText(scripts[plot] + getResources().getString(R.string.reflection0));
                        name.setVisibility(View.INVISIBLE);
                        nameEditText.setVisibility(View.INVISIBLE);
                        names = "Bland";
                        count = 0;
                    } else {
                        name.setVisibility(View.INVISIBLE);
                        nameEditText.setVisibility(View.INVISIBLE);
                        nameTextView.setText(names);
                    }
                } else if (plot == 8) {
                    //plot = 0;
                    //startActivity(title);
                    try {
                        Thread.sleep(1000); //1000 milliseconds is one second.
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                } else if (plot == 12) {
                    choiceBtn1.setText(choice1[option]);
                    choiceBtn1.setVisibility(View.VISIBLE);
                    choiceBtn2.setText(choice2[option]);
                    choiceBtn2.setVisibility(View.VISIBLE);
                } else if (plot == 13) {
                    if (!selected) {
                        plot -= 1;
                        textTextView.setText(getResources().getString(R.string.require_selection));
                        nameTextView.setText("");
                    } else {
                        selected = false;
                    }
                }
            }
        });
    }
}
