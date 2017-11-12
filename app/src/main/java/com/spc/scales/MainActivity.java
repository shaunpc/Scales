package com.spc.scales;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView etScale, etDynamic, etStructure;
    String[] s_scales, s_dynamics, s_structures;
    int num_scales, num_dynamics, num_structures;
    Button  btn;
    Random rand;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etScale = findViewById(R.id.scale);
        etDynamic = findViewById(R.id.dynamic);
        etStructure = findViewById(R.id.structure);
        btn = findViewById(R.id.button);

        //Load strings
        s_scales = getResources().getStringArray(R.array.scales);
        s_dynamics = getResources().getStringArray(R.array.dynamics);
        s_structures = getResources().getStringArray(R.array.structures);
        num_scales = s_scales.length;
        num_dynamics = s_dynamics.length;
        num_structures = s_structures.length;

        Log.d("SCALES", "onCreate: Scales ("+num_scales+"):"+s_scales);
        Log.d("SCALES", "onCreate: Dynamics ("+num_dynamics+"):"+s_dynamics);
        Log.d("SCALES", "onCreate: Scales ("+num_structures+"):"+s_structures);

        rand = new Random();

    }

    void getNewCombo (final View v) {
        etScale.setText(s_scales[rand.nextInt(num_scales)]);
        etScale.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        etDynamic.setText(s_dynamics[rand.nextInt(num_dynamics)]);
        etDynamic.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        etStructure.setText(s_structures[rand.nextInt(num_structures)]);
        etStructure.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        btn.setText("Get Next Combination");

        // If timer is running, then cancel it, and start a new one...
        if (timer != null) {timer.cancel();}
        timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                btn.setText("Next Combo in " + millisUntilFinished / 1000 + " secs");
            }

            public void onFinish() {
                btn.setText("Changing!");
                getNewCombo(v);
            }
        }.start();
    }
}
