package com.jheave.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView weightCardView;
    CardView ageCardView;
    TextView weightCounterText, ageCounterText, height_title_text;
    FloatingActionButton weightBtnInc, ageBtnInc;
    FloatingActionButton weightBtnDec, ageBtnDec;

    int weightCounter = 50;
    int ageCounter = 25;
    String countWeight, countAge;
    NumberPicker feetPicker, inchPicker;
    int feetValue = 5 , inchValue = 4;
    Button calculateBtn;
    String heightValue;
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
        feetPicker = findViewById(R.id.feet_picker);
        inchPicker = findViewById(R.id.inch_picker);
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

        feetPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            feetValue = newVal;
            heightValueIs();

        });

        inchPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            inchValue = newVal;
            heightValueIs();
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
        feetPicker.setMinValue(1);
        feetPicker.setMaxValue(8);
        inchPicker.setMinValue(0);
        inchPicker.setMaxValue(11);
        feetPicker.setValue(5);
        inchPicker.setValue(4);
        heightValueIs();
    }
    public void heightValueIs()
    {
        if(inchValue == 0){
            heightValue = feetValue + " feet ";
            height_title_text.setText(heightValue);
        }
        else
        heightValue = feetValue + " feet " + inchValue +" inches";
        height_title_text.setText(heightValue);
    }
    public void calculateBmi(){
        double heightInInches = feetValue * 12 + inchValue;
        double heightInMetres = heightInInches / 39.37;
        double heightInMetreSq = heightInMetres * heightInMetres;
        double bmi = weightCounter / heightInMetreSq;
        String bmiValue = decimalFormat.format(bmi);
        Intent intent = new Intent(this,BmiActivity.class);
        intent.putExtra("bmiVal",bmiValue);
        startActivity(intent);
    }
}
