package com.example.scott.cs125project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private boolean[] con;
    private String names = "Unknown";
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    private boolean shakeOn;

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private final String TAG = "main activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Helper helper = new Helper(this);

        final String[] scripts_en = getResources().getStringArray(R.array.scripts_en);
        final String[] scripts_cn = getResources().getStringArray(R.array.scripts_cn);
        final String[] characters = getResources().getStringArray(R.array.characters);
        final String[] choice1_en = getResources().getStringArray(R.array.option0_en);
        final String[] choice2_en = getResources().getStringArray(R.array.option1_en);
        final String[] choice1_cn = getResources().getStringArray(R.array.option0_cn);
        final String[] choice2_cn = getResources().getStringArray(R.array.option1_cn);

        final List<Boolean> conditions = new ArrayList<>();
        con = new boolean[0];

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
        final Button clockBtn = findViewById(R.id.clockBtn);

        final ConstraintLayout layout = findViewById(R.id.layout);

        final AutoTypeTextView textTextView = findViewById(R.id.textTextView);
        final TextView nameTextView = findViewById(R.id.nameTextView);
        final TextView name = findViewById(R.id.nameTextViewDisplay);

        final EditText nameEditText = findViewById(R.id.nameEditText);

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        plot = sharedPref.getInt("plot", 0);
        option = sharedPref.getInt("option", 0);
        Helper.retrieveConditions(sharedPref, conditions, "conditions");
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
            Helper.setCondition(conditions, getIntent().getExtras()
                    .getBooleanArray("conditions"));
        }
        if (getIntent().hasExtra("name")) {
            names = getIntent().getExtras().getString("name");
        }
        if (getIntent().hasExtra("speed")) {
            speed = getIntent().getExtras().getInt("speed");
        }
        if (getIntent().hasExtra("textSize")) {
            textSize = getIntent().getExtras().getInt("textSize");
        }

        textTextView.setTextSize(textSize);
        textTextView.setTypingSpeed(speed);

        if (plot != 0) {
            if (language == 0) {
                Helper.setUp(scripts_en, textTextView, characters, names, nameTextView,
                        plot - 1, false);
                Helper.setBtn(choice1_en, choiceBtn1, choice2_en,
                        choiceBtn2, layout, plot - 1, option);
            } else {
                Helper.setUp(scripts_cn, textTextView, characters, names, nameTextView,
                        plot - 1, false);
                Helper.setBtn(choice1_cn, choiceBtn1, choice2_en,
                        choiceBtn2, layout, plot - 1, option);
            }
        }

        helper.setImage(layout, plot);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.
                getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        shakeOn = true;

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
                load.putExtra("load", true);
                startActivity(load);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.putExtra("setting", true);
                startActivity(setting);
            }
        });
        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.addCondition(conditions, true, option);
                Log.d(TAG, "clock is clicked");
                con = Helper.transfer(conditions);
                Helper.setVisibility(false, clockBtn);
                option = 1;
            }
        });
        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.addCondition(conditions, true, option);
                con = Helper.transfer(conditions);
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
                layout.setClickable(true);
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.addCondition(conditions, false, option);
                con = Helper.transfer(conditions);
                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                option++;
                layout.setClickable(true);
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Helper.setVisibility(false, textTextView, nameTextView);
                return false;
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textTextView.getVisibility() == View.INVISIBLE) {
                    Helper.setVisibility(true, textTextView, nameTextView);
                    return;
                }
                if (textTextView.isRunning()) {
                    return;
                }
                end();
                if (shakeOn && Math.abs(mAccel) > 2.2) {
                    Helper.addCondition(conditions, true, 0);
                    option = 1;
                    con = Helper.transfer(conditions);
                    Log.d(TAG, "shake detected");
                    mSensorManager.unregisterListener(mSensorListener);
                    Log.d(TAG, "shake detector cancelled");
                    shakeOn = false;
                } else if (shakeOn) {
                    Log.d(TAG, "shake at" + mAccel);
                }
                switch (plot) {
                    case 1:
                        Helper.setVisibility(true, name,nameEditText);
                        break;
                    case 2:
                        names = nameEditText.getText().toString();
                        if (names.equals("") && count < 5) {
                            Helper.setVisibility(true, name,nameEditText);
                            textTextView.setTextAutoTyping(getResources().getString(R.string.require_name)
                                    + Helper.createExc(count));
                            plot = 1;
                            count++;
                        } else if (names.equals("")) {
                            textTextView.setTextAutoTyping(scripts_en[plot]
                                    + getResources().getString(R.string.reflection0));
                            Helper.setVisibility(false, name, nameEditText);
                            names = "Bland";
                            count = 0;
                        } else {
                            Helper.setVisibility(false, name, nameEditText);
                            count = 0;
                        }
                        break;
                    case 3:
                        clockBtn.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        clockBtn.setVisibility(View.INVISIBLE);
                        if (conditions.size() > 0 && conditions.get(0)) {
                            plot = 9;
                            mSensorManager.unregisterListener(mSensorListener);
                            Log.d(TAG, "shake detector cancelled");
                        } else {
                            Helper.addCondition(conditions, false, 0);
                            con = Helper.transfer(conditions);
                            option++;
                        }
                        break;
                    case 13:
                        if (conditions.size() > 1 && !conditions.get(1)) {
                            plot = 17;
                        }
                        break;
                    case 17:
                        if (conditions.size() > 1 && conditions.get(1)) {
                            plot = 13;
                        }
                        break;
                    case 19:
                        if (conditions.size() > 2 && !conditions.get(2)) {
                            plot = 22;
                        }
                        break;
                    case 20:
                        if (names.trim().toLowerCase().equals("bland")) {
                            plot = 43;
                        }
                        break;
                    case 30:
                        if (conditions.size() != 4) {
                            if (count < 5) {
                                setUp(language);
                                layout.setClickable(true);
                                count++;
                                return;
                            } else {
                                Helper.setVisibility(false, choiceBtn1, choiceBtn2);
                                Helper.addCondition(conditions, false, 3);
                                plot = 35;
                            }
                        } else {
                            Helper.addCondition(conditions, true, 3);
                        }
                        break;
                    case 35:
                        if (!conditions.get(3)) {
                            Helper.addCondition(conditions, true, 3);
                            plot = 13;
                        }
                        break;
                    case 59:
                        if (!getIntent().hasExtra("real")) {
                            startActivity(title);
                        }
                        break;
                }
                helper.setImage(layout, plot);
                setUp(language);
                plot++;
            }

            void setUp(int a) {
                if (a == 0) {
                    Helper.setUp(scripts_en, textTextView, characters, names,
                            nameTextView, plot, true);
                    Helper.setBtn(choice1_en, choiceBtn1, choice2_en,
                            choiceBtn2, layout, plot, option);
                } else {
                    Helper.setUp(scripts_cn, textTextView, characters, names,
                            nameTextView, plot, true);
                    Helper.setBtn(choice1_cn, choiceBtn1, choice2_cn,
                            choiceBtn2, layout, plot, option);
                }
            }
            void end() {
                if (Helper.checkEnd(conditions, plot)) {
                    plot = 0;
                    option = 0;
                    con = new boolean[0];
                    Log.d(TAG, "end");
                    startActivity(title);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.
                getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("plot", plot);
        editor.putInt("option", option);
        editor.putInt("language", language);
        editor.putInt("speed", speed);
        editor.putInt("textSize", textSize);
        editor.putString("name", names);
        Helper.commitConditions(editor, con, "conditions");
        editor.apply();
    }
}