/***************************************************************************
 *                                                                         *
 * CSCI 522           522 Honors and Graduate Project           Fall 2020  *
 *                                                                         *
 *   Class Name: MainActivity.java                                         *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 04 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used attempt a math quiz for kids with      *
 *               randomly generated questions for addition, subtraction   *
 *               multiplication and division.                              *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startQuiz = findViewById(R.id.button_start);

        /*******************************************************************************************
        *                                                                                          *
        * setOnClickListener method is implemented to override onClick method when a button to     *
        * start quiz is clicked and starts second activity                                         *
        *                                                                                          *
        ********************************************************************************************/
        startQuiz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Create the Intent object of this class (MainActivity) Context() to SecondActivity class
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);        // Starting Second activity
            }
        });
    }
}