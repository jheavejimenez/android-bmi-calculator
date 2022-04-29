package com.jheave.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {
    TextView bmiValue, bmiCategory,bmiTips;
    String category;
    String bmiValOutput;
    Button calculateAgainBtn;
    String[] bmiTipsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        bmiValue = findViewById(R.id.bmi_value);
        bmiCategory = findViewById(R.id.bmi_category);
        bmiTipsArray = getResources().getStringArray(R.array.tips_array);
        calculateAgainBtn = findViewById(R.id.calculate_again_btn);
        bmiValOutput = getIntent().getStringExtra("bmiVal");
        bmiValue.setText(bmiValOutput);
        findCategory();
        calculateAgainBtn.setOnClickListener(v -> onBackPressed());

    }

    private void findCategory() {
        double result = Double.parseDouble(bmiValOutput);
        if(result < 15){
            category = "Very Severely Underweight";
            bmiCategory.setText(category);
        }
        else if(result >= 15 && result <= 16){
            category = "Severely Underweight";
            bmiCategory.setText(category);
        }
        else if(result >= 16 && result <= 18.5){
            category = "Underweight";
            bmiCategory.setText(category);
        }
        else if(result >= 18.5 && result <= 25){
            category = "Normal";
            bmiCategory.setText(category);
        }
        else if(result >= 25 && result <= 30){
            category = "Overweight";
            bmiCategory.setText(category);
        }
        else if(result >=30 && result <= 35){
            category = "Moderately Obese";
            bmiCategory.setText(category);
        }
        else if(result >= 35 && result <= 50){
            bmiCategory.setText(category);
            category = "Severely Obese";
        }
        else
            category = "Very Severely Obese";
        bmiCategory.setText(category);

    }
}
