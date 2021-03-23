package edu.niu.z1901136.exercise1;

/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 4              Fall 2020      *
 *                                                             *
 *   Class Name: MainActivity.java                             *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 25 September 2020                             *
 *                                                             *
 *      Purpose: This class is used to display two different   *
 *               messages on each simultaneous click of button *
 *               independently.                                *
 *                                                             *
 ***************************************************************/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void convertCurrency(View view){
        EditText dollarText =  findViewById(R.id.dollarText);
        TextView textView = findViewById(R.id.textView);

        if(!dollarText.getText().toString().equals("")){
            float dollarValue = Float.valueOf(dollarText.getText().toString());
            float euroValue = dollarValue * 0.84F;
            textView.setText(String.format(Locale.getDefault(), "%.2f Euros", euroValue));
        }
        else{
            textView.setText(R.string.no_value_string);
        }
    }
}