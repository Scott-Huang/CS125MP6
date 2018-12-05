package com.example.scott.cs125project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragankrstic.autotypetextview.AutoTypeTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* english is 0, chinese is 1.*/
    private int language = 0;
    private static int plot;
    private static int option;
    private int textSize;
    private int speed;
    private int count = 0;
    private String names = "Unknown";
    private final String TAG = "CS125Project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Maybe need it later.
        //Helper helper = new Helper(this);

        final String[] scripts_en = getResources().getStringArray(R.array.scripts_en);
        final String[] scripts_cn = getResources().getStringArray(R.array.scripts_cn);
        final String[] characters = getResources().getStringArray(R.array.characters);
        final String[] choice1_en = getResources().getStringArray(R.array.option0_en);
        final String[] choice2_en = getResources().getStringArray(R.array.option1_en);
        final String[] choice1_cn = getResources().getStringArray(R.array.option0_cn);
        final String[] choice2_cn = getResources().getStringArray(R.array.option1_cn);

        final List<Boolean> conditions = new ArrayList<>();

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

        final AutoTypeTextView textTextView = findViewById(R.id.textTextView);
        final TextView nameTextView = findViewById(R.id.nameTextView);
        final TextView name = findViewById(R.id.nameTextViewDisplay);

        final EditText nameEditText = findViewById(R.id.nameEditText);

        final ImageView imageView;

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        plot = sharedPref.getInt("plot", 0);
        option = sharedPref.getInt("option", 0);
        textSize = sharedPref.getInt("textSize", 25);
        speed = sharedPref.getInt("speed", 30);
        names = sharedPref.getString("name", "Unknown");
        language = sharedPref.getInt("language", 0);

        if (getIntent().hasExtra("language")) {
            language = getIntent().getExtras().getInt("language");
        }
        if (getIntent().hasExtra("plot")) {
            plot = getIntent().getExtras().getInt("plot");
        }
        if (getIntent().hasExtra("option")) {
            option = getIntent().getExtras().getInt("option");
        }
        if (getIntent().hasExtra("conditions")) {
            Helper.setCondition(conditions, getIntent().getExtras().getBooleanArray("conditions"));
        }
        if (getIntent().hasExtra("name")) {
            names = getIntent().getExtras().getString("name");
            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot - 1);
            Helper.setBtn(choice1_en, choiceBtn1, choice2_en, choiceBtn2, layout, plot, option);
        }

        textTextView.setTextSize(textSize);
        textTextView.setTypingSpeed(speed);

        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(title);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.putExtra("save", true);
                save.putExtra("plot", plot);
                save.putExtra("option", option);
                save.putExtra("name", names);
                save.putExtra("conditions", Helper.transfer(conditions));
                startActivity(save);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.putExtra("conditions", Helper.transfer(conditions));
                startActivity(load);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.putExtra("conditions", Helper.transfer(conditions));
                setting.putExtra("language", language);
                setting.putExtra("speed", speed);
                setting.putExtra("textSize", textSize);
                startActivity(setting);
            }
        });
        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditions.add(true);
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
                layout.setClickable(true);
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditions.add(false);
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
                layout.setClickable(true);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textTextView.isRunning()) {
                    return;
                }
                if (Helper.checkEnd(conditions, plot)) {
                    Log.d(TAG, "end");
                    //plot = 0;
                    //startActivity(title);
                }
                plot++;
                if (plot == 2) {
                    Helper.setVisibility(true, name,nameEditText);
                } else if (plot == 3) {
                    names = nameEditText.getText().toString();
                    if (names.equals("") && count < 5) {
                        Helper.setVisibility(true, name,nameEditText);
                        textTextView.setTextAutoTyping(getResources().getString(R.string.require_name) + Helper.createExc(count));
                        nameTextView.setText(names);
                        plot = 2;
                        count++;
                    } else if (names.equals("")) {
                        textTextView.setTextAutoTyping(scripts_en[plot - 1] + getResources().getString(R.string.reflection0));
                        Helper.setVisibility(false, name, nameEditText);
                        names = "Bland";
                        nameTextView.setText(names);
                        count = 0;
                    } else {
                        Helper.setVisibility(false, name, nameEditText);
                        nameTextView.setText(names);
                    }
                }
                switch (plot) {
                    case 14:
                        if (conditions.size() > 0 && !conditions.get(0)) {
                            plot = 17;
                            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                            plot++;
                        }
                        break;
                    case 18:
                        if (conditions.size() > 0 && conditions.get(0)) {
                            plot = 13;
                            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                            plot++;
                        }
                        break;
                    case 20:
                        if (conditions.size() > 1 && !conditions.get(1)) {
                            plot = 22;
                            Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot);
                            plot++;
                        }
                        break;
                }
                Helper.setUp(scripts_en, textTextView, characters, names, nameTextView, plot - 1);
                Helper.setBtn(choice1_en, choiceBtn1, choice2_en, choiceBtn2, layout, plot, option);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("plot", plot);
        editor.putInt("option", option);
        editor.putInt("language", language);
        editor.putInt("speed", speed);
        editor.putInt("textSize", textSize);
        editor.putString("name", names);
        editor.apply();
    }
}