/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 7              Fall 2020      *
 *                                                             *
 *   Class Name: MainActivity.java                             *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 06 November 2020                              *
 *                                                             *
 *      Purpose: This class is used to select a dessert from   *
 *               the provided list displayed along with        *
 *               respective images and once clicked on recipe  *
 *               button takes you next activity.               *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner recipeList = findViewById(R.id.Spinner_recipeList);
        final ImageView recipeImage = findViewById(R.id.imageView_list);
        Button recipeButton = findViewById(R.id.button_goToRecipe);

        // Implementing setOnClickListener interface
        recipeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            /*************************************************************************************************
             *                                                                                               *
             * onClick method is override to move to another activity for description of recipe selected     *
             *                                                                                               *
             *************************************************************************************************/
            public void onClick(View v)
            {
                String recipeSelected = recipeList.getSelectedItem().toString();

                // Create the Intent object of this class Context() to other activity class
                Intent i = new Intent(MainActivity.this,Activity_Recipe.class);

                i.putExtra("recipeString",recipeSelected); // putExtra method put the value in key, value pair
                Bundle bundle = new Bundle();     // creating instance for Bundle to pass data from one main to recipe activity

                // Checks the if the recipe selected from the list equals the string provided in the condition and pushes the string to bundle
                if(recipeSelected.equals("Vanilla cake"))
                {
                    bundle.putString("Recipe_key", getResources().getString(R.string.VanillaCake_Recipe));
                }
                else if(recipeSelected.equals("Red velvet cake"))
                {
                    bundle.putString("Recipe_key", getResources().getString(R.string.redvelvet_Recipe));
                }
                else if(recipeSelected.equals("Chocolate ombre cake"))
                {
                    bundle.putString("Recipe_key", getResources().getString(R.string.chocolateOmbre_Recipe));
                }
                else if(recipeSelected.equals("Carrot cake"))
                {
                    bundle.putString("Recipe_key", getResources().getString(R.string.carrotCake_Recipe));
                }
                else if(recipeSelected.equals("Brownie"))
                {
                    bundle.putString("Recipe_key", getResources().getString(R.string.brownies_Recipe));
                }
                i.putExtras(bundle); // Passing Bundle object to Intent
               startActivity(i);   // Starting the activity
            }
        });

        //Implementing OnItemSelectedListener interface
        recipeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            /*******************************************************************************************
            *                                                                                          *
            * onItemSelected method is override to get the value of the selected item from the spinner *
            * to display the respective image in the activity                                          *
            *                                                                                          *
            ********************************************************************************************/
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(recipeList.getSelectedItem().equals("Vanilla cake"))
                {
                    recipeImage.setImageResource(R.drawable.vanilla_cake);
                }
                else if(recipeList.getSelectedItem().equals("Red velvet cake"))
                {
                    recipeImage.setImageResource(R.drawable.red_velvet);
                }
                else if(recipeList.getSelectedItem().equals("Chocolate ombre cake"))
                {
                    recipeImage.setImageResource(R.drawable.ombre_cake);
                }
                else if(recipeList.getSelectedItem().equals("Carrot cake"))
                {
                    recipeImage.setImageResource(R.drawable.carrot);
                }
                else if(recipeList.getSelectedItem().equals("Brownie"))
                {
                    recipeImage.setImageResource(R.drawable.brownies);
                }
            }
            // Override onNothingSelected method
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
}