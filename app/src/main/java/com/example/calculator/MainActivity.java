package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText et_height, et_weight, et_inc;
    Button btn_calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_calculate = findViewById(R.id.btn_calculate);
        result = findViewById(R.id.result);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        et_inc = findViewById(R.id.et_inc);

        // When click on the Calculate BMI Button then it will be fire.
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            calculate();
            }

        });

        // When click Enter on the keybord then it will be fire.
        et_inc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    calculate();
                    return true;
                }
                return false;
            }
        });

    }

    //Get Text from user and convert it to Integer
    public int textToInteger(EditText text){
        return Integer.parseInt(text.getText().toString());
    }

//    BMi number to Classification Name
    public void calculateCondition(float bmi){
        //Formate flot number like 22.36366 to 22.26
      String res = String.format("%.2f",bmi);

        //Calculate Classification using BMI
        if (bmi < 18.5) {
            result.setText("Underweight (" + res+")");
        }else if(bmi < 24.9){
            result.setText("Normal (" + res+")");
        }else if(bmi < 29.9){
            result.setText("Overweight (" + res+")");
        }
        else if(bmi < 34.9){
            result.setText("Obese (" + res+")");
        }else if(bmi < 39.9){
            result.setText("Severely Obese (" + res+")");
        }else if(bmi > 40){
            result.setText("Morbidly Obese (" + res+")");
        }
        else {
            result.setText("Something wrong");
        }
    }

    public void calculate(){
        if (!et_height.getText().toString().isEmpty() && !et_weight.getText().toString().isEmpty()) {

            int h = textToInteger(et_height);
            int w = textToInteger(et_weight);
            int inc = textToInteger(et_inc);

            double mitter = inc * 0.02540000 + (h * 0.3048000);
            float finalResult = (float) (w / Math.pow(mitter, 2));
            calculateCondition((float) finalResult);

        } else {
            Toast.makeText(getApplicationContext(), "Please fill this form first", Toast.LENGTH_SHORT).show();
        }
    }

}