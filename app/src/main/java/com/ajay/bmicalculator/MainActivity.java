package com.ajay.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText edWeg, edHei;
        TextView txtRes, txtInter;
        Button btnRes, btnReset;



        edWeg = (EditText) findViewById(R.id.edweg);
        edHei = (EditText) findViewById(R.id.edhei);

        txtInter = (TextView) findViewById(R.id.txtinter);
        txtRes = (TextView) findViewById(R.id.txtres);

        btnRes = (Button) findViewById(R.id.btnres);
        btnReset = (Button) findViewById(R.id.btnreset);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strweg = edWeg.getText().toString();
                String strhei = edHei.getText().toString();

                if (strweg.equals("")) {
                    edWeg.setError("please enter your weight");
                    edWeg.requestFocus();
                    return;
                }
                if (strhei.equals("")) {
                    edHei.setError("please enter your height");
                    edHei.requestFocus();
                    return;
                }
                float weight = Float.parseFloat(strweg);
                float height = Float.parseFloat(strhei) / 100;

                float bmiValue = BMICalculate(weight, height);

                txtInter.setText(interpreteBMI(bmiValue));
                txtRes.setText("BMI= " + bmiValue);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edHei.setText("");
                edWeg.setText("");
                txtInter.setText("");
                txtRes.setText("");

            }
        });


    }


    public float BMICalculate(float weight, float height) {
        return weight / (height * height);
    }

    public String interpreteBMI(float bmiValue) {
        if (bmiValue < 16) {
            return "Servely Underweight";
        } else if (bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue < 25) {
            return "Normal";
        } else if (bmiValue < 30) {
            return "OverWeight";
        } else
            return "obese";

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
       return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeActivity1:
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
                Toast.makeText(this, "About is selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.changeActivity2:
                startActivity(new Intent(MainActivity.this,MainActivity3.class));
                Toast.makeText(this, "BMI Chart is selected", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.changeActivity3:
                startActivity(new Intent(MainActivity.this,MainActivity4.class));
                Toast.makeText(this, "About Developer is selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


    public void onBackPressed(){

        myAlert(MainActivity.this);

    }



    public void myAlert(Context mContext){
        new AlertDialog.Builder(mContext)
                .setIcon(R.drawable.ic_alert1)
                .setTitle("Exit?")
                .setMessage("Do you want to exit my app?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();



    }

 

}