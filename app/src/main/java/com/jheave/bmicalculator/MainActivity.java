package com.jheave.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView weightCardView;
    CardView ageCardView;
    TextView weightCounterText, ageCounterText, height_title_text;
    SeekBar seekbar_height;
    FloatingActionButton weightBtnInc, ageBtnInc;
    FloatingActionButton weightBtnDec, ageBtnDec;

    int weightCounter = 55;
    int ageCounter = 20;
    int currentProgress;

    String heightCounter="170";
    String countWeight, countAge;
    Button calculateBtn;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightCardView = findViewById(R.id.weight_cardView);
        ageCardView = findViewById(R.id.age_cardView);
        weightCounterText = findViewById(R.id.weight_counter_text);
        ageCounterText = findViewById(R.id.age_counter_text);
        weightBtnInc = findViewById(R.id.weight_btn_inc);
        weightBtnDec = findViewById(R.id.weight_btn_dec);
        ageBtnInc = findViewById(R.id.age_btn_inc);
        ageBtnDec = findViewById(R.id.age_btn_dec);
        seekbar_height = findViewById(R.id.seekbar_height);
        height_title_text = findViewById(R.id.current_height);
        calculateBtn = findViewById(R.id.calculate_btn);
        counterInit();
        decimalFormat = new DecimalFormat(".#");
        weightCardView.setOnClickListener(this);
        ageCardView.setOnClickListener(this);
        weightBtnInc.setOnClickListener(this);
        weightBtnDec.setOnClickListener(this);
        ageBtnInc.setOnClickListener(this);
        ageBtnDec.setOnClickListener(this);

        seekbar_height.setMax(300);
        seekbar_height.setProgress(170);
        seekbar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentProgress=progress;
                heightCounter=String.valueOf(currentProgress);
                height_title_text.setText(heightCounter);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        calculateBtn.setOnClickListener(v -> calculateBmi());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.weight_cardView:
            case R.id.age_cardView:
                break;
            case R.id.weight_btn_inc:
                if(weightCounter < 700)
                weightCounter++;
                countWeight = Integer.toString(weightCounter);
                weightCounterText.setText(countWeight);
                break;
            case R.id.weight_btn_dec:
                if(weightCounter > 0)
                weightCounter--;
                countWeight = Integer.toString(weightCounter);
                weightCounterText.setText(countWeight);
                break;
            case R.id.age_btn_inc:
                if(ageCounter < 150)
                    ageCounter++;
                countAge = Integer.toString(ageCounter);
                ageCounterText.setText(countAge);
                break;
            case R.id.age_btn_dec:
                if(ageCounter > 1)
                    ageCounter--;
                countAge = Integer.toString(ageCounter);
                ageCounterText.setText(countAge);
                break;
        }
    }

    private void counterInit() {
        countWeight = Integer.toString(weightCounter);
        weightCounterText.setText(countWeight);
        countAge = Integer.toString(ageCounter);
        ageCounterText.setText(countAge);

    }

    public void calculateBmi(){
        double height = Double.parseDouble(heightCounter);
        double heightInMeters = height/100;
        double heightInMetersSquared = Math.pow(heightInMeters,2);
        double bmi = weightCounter / heightInMetersSquared;

        String bmiValue = decimalFormat.format(bmi);
        Intent intent = new Intent(this,BmiActivity.class);
        intent.putExtra("bmiVal",bmiValue);
        startActivity(intent);
    }
}
