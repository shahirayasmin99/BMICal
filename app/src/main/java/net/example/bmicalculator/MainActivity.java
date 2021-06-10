package net.example.bmicalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static net.example.bmicalculator.R.id.calculate;
import static net.example.bmicalculator.R.id.floating;

public class MainActivity extends AppCompatActivity{

    EditText height;
    EditText weight;
    TextView result;
    Button calculate, aboutpage;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("BMI Calculator");

        sharedPreferences=this.getSharedPreferences("ha",Context.MODE_PRIVATE);


        aboutpage = (Button) findViewById(R.id.aboutpage);
       height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        calculate = (Button) findViewById(R.id.calculate);

      SharedPreferences sharedPreferences = this.getSharedPreferences("ref", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor=sharedPreferences.edit();
        height.setText(sharedPreferences.getString("h",""));
        weight.setText(sharedPreferences.getString("w",""));


        //SharedPreferences.Editor editor=sharedPreferences.edit();
       // editor.putString("h",heightStr);
       // editor.apply();

        aboutpage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,aboutpage.class);
                Toast.makeText(MainActivity.this,"You are now in About page",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                sharedPreferences.edit().putString("h", heightStr.toString()).commit();
                sharedPreferences.edit().putString("w", weightStr.toString()).commit();
                calculateBMI();
            }
        });
    }
    private void calculateBMI(){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

     //   SharedPreferences.Editor editor=sharedPreferences.edit();

//if (heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr)){
    if(heightStr.isEmpty() || weightStr.isEmpty()) {
        Toast.makeText(MainActivity.this,"Please dont let anything left empty",Toast.LENGTH_SHORT).show();
    }else{

        float heightValue = Float.parseFloat(heightStr)/100;
        float weightValue = Float.parseFloat(weightStr);

        float bmi = weightValue / (heightValue * heightValue);
        String.format("%.2f",bmi);
        displayBMI(bmi);

    }

        //   editor.putString("h",heightStr);
        // editor.putString("w",weightStr);
        //editor.apply();
    }

    private void displayBMI(float bmi){
        String bmiLabel = "";


        if(Float.compare(bmi, 18.4f ) <=0){
            bmiLabel = "Underweight\n Malnutrition Risk ";

        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 24.9f) <=0){
            bmiLabel = "Normal Weight\n Low risk";

        }else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 29.9f) <=0){
            bmiLabel = "Overweight\n Enchanced risk";

        }else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 34.9f) <=0){
            bmiLabel = "Moderately obese\n Medium risk";

        }else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 49.9f) <=0){
            bmiLabel = "Severely Obese\n High risk";

        } else{
            bmiLabel = "Very severely obese\n Very high risk";

        }

        bmiLabel = String.format("Your BMI is %.1f",bmi) + "\n" + bmiLabel ;
        result.setText(bmiLabel);

    }


}