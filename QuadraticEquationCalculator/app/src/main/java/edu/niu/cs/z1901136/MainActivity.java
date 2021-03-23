/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 6              Fall 2020      *
 *                                                             *
 *   Class Name: MainActivity.java                             *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 16 October 2020                               *
 *                                                             *
 *      Purpose: This class is used to calculate the roots of  *
 *               the quadratic equation and display  the       *
 *               respective values on the screen               *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;
import java.lang.Math;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    double discriminant, x1, x2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calculate = findViewById(R.id.button_x);
        calculate.setOnClickListener(this);            // links OnClickListener to the calculate button
        Button clear_all = findViewById(R.id.button_clear);
        clear_all.setOnClickListener(this);            // links OnClickListener to the clear button
    }

    /*****************************************************************************************
    *                                                                                        *
    * onClick method is override to find the roots of the quadratic equation values entered  *
    *                                                                                        *
    ******************************************************************************************/
    public void onClick(View view)
    {
        DecimalFormat df = new DecimalFormat("##.00");   // declaring the pattern to apply for the decimal
        EditText variable_a = findViewById(R.id.editText_a);
        EditText variable_b = findViewById(R.id.editText_b);
        EditText variable_c = findViewById(R.id.editText_c);
        EditText resultTV_x1 = findViewById(R.id.editText_x1);
        EditText resultTV_x2 = findViewById(R.id.editText_x2);

            if (!variable_a.getText().toString().isEmpty() && !variable_b.getText().toString().isEmpty()
                    && !variable_c.getText().toString().isEmpty() && view.getId() == R.id.button_x)
            {
                double value_a = Double.parseDouble(variable_a.getText().toString()); // convert String to double
                double value_b = Double.parseDouble(variable_b.getText().toString()); // convert String to double
                double value_c = Double.parseDouble(variable_c.getText().toString()); // convert String to double
                discriminant = Math.pow(value_b, 2) - (4 * value_a * value_c);  // Used to evaluate discriminant value

                if(value_a == 0.00)  // Checks if the string is null for value 'a' and displays message on screen
                {
                    Toast.makeText(MainActivity.this,"Please enter a non zero value for a" ,Toast.LENGTH_LONG).show();
                }

              else if (discriminant == 0)  // checks if the discriminant is zero
                {
                    x1 = (-value_b) / (2 * value_a);
                    resultTV_x1.setText(df.format(x1));
                    resultTV_x2.setText(df.format(x1));
                }
                else if (discriminant > 0) // checks if the discriminant is greater than zero
                {
                    x1 = (-(value_b) + Math.sqrt(discriminant)) / (2 * value_a);
                    x2 = (-(value_b) - Math.sqrt(discriminant)) / (2 * value_a);
                    resultTV_x1.setText(df.format(x1));
                    resultTV_x2.setText(df.format(x2));
                }
                else if (discriminant < 0)  // checks if the discriminant is less than zero and roots are imaginary
                {
                    resultTV_x1.setText("Imaginary");
                    resultTV_x2.setText("Imaginary");
                }
            }
            else if (view.getId() == R.id.button_x)
            {
                if(variable_a.getText().toString().isEmpty())  // checks if the textbox for value 'a' is empty
                    printToastMessage("a");
                else if(variable_b.getText().toString().isEmpty())  // checks if the textbox for value 'b' is empty
                    printToastMessage("b");
                else
                    printToastMessage("c");
            }

        if(view.getId() == R.id.button_clear)  //checks if the CLEAR button is clicked
        {
            variable_a.setText(" ");
            variable_b.setText(" ");
            variable_c.setText(" ");
            resultTV_x1.setText(" ");
            resultTV_x2.setText(" ");
        }
    }

    /****************************************************************************************************
    *                                                                                                   *
    * printToastMessage method is to display a message on the screen if no input given to the textbox   *
    *                                                                                                   *
    *****************************************************************************************************/
    private void printToastMessage(String variable)
    {
        // toast message on the screen if the textbox values are left empty
        Toast.makeText(MainActivity.this,"Please enter a value for " + variable,Toast.LENGTH_LONG).show();
    }

}