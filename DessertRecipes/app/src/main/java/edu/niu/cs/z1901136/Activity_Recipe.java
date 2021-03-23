/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 7              Fall 2020      *
 *                                                             *
 *   Class Name: Activity_Recipe.java                          *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 06 November 2020                              *
 *                                                             *
 *      Purpose: This class is used display the selected item  *
 *               from the spinner in the main activity and     *
 *               describe the directions of the recipe process *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Recipe extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Button goBack = findViewById(R.id.button_goBack);
        //Intent intent = getIntent();    // create the get Intent object

        Bundle bundle = getIntent().getExtras();  // getting bundle from main activity
        if (bundle!= null)  // Checks if there is data available and passed to bundle
        {
            // getting the string value of the key passed in the main activity
            String recipeString = bundle.getString("recipeString");

            // getting the string value of the key passed in the main activity
            String recipe_Description = bundle.getString("Recipe_key");
            TextView recipeSelected_main = findViewById(R.id.textView_recipeName);
            TextView recipeDescription = findViewById(R.id.textView_recipeDescription);
            recipeSelected_main.setText(recipeString);      // Set the text of recipe selected in the main activity on textview
            recipeDescription.setText(recipe_Description);  // set the text of the recipe description on Textview
        }

        // Implementing OnClickListener interface
        goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            /****************************************************************************************************************
            *                                                                                                               *
            * onClick method is override to go back to the main activity page when clicked on button in the Activity_Recipe *
            *                                                                                                               *
            ****************************************************************************************************************/
            public void onClick(View v)
            {
                // Create object for Intent to move to the main activity when clicked on button
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);    // Starting activity
            }
        });
    }
}