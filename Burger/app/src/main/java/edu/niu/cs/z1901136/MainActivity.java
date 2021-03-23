/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 5              Fall 2020      *
 *                                                             *
 *   Class Name: MainActivity.java                             *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 10 October 2020                               *
 *                                                             *
 *      Purpose: This class is used to display the calorie     *
 *               count information based on choosing the       *
 *               available burger options using checkboxes     *
 *               and radio buttons.                            *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnCheckedChangeListener, OnSeekBarChangeListener
{
    CalorieInformation calorieInformation = new CalorieInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox cb = findViewById(R.id.checkBox_bacon);
        SeekBar sbprogress = findViewById(R.id.seekBar);
        RadioGroup rgPatty = findViewById(R.id.radiogroup_patty);
        RadioGroup rgCheese = findViewById(R.id.radioGroup_cheese);
        cb.setOnClickListener(this);                  // links OnClickListener to the check box
        rgPatty.setOnCheckedChangeListener(this);     // links OnCheckedChangeListener to the radio group
        rgCheese.setOnCheckedChangeListener(this);    // links OnCheckedChangeListener to the radio group
        sbprogress.setOnSeekBarChangeListener(this);  // links OnSeekBarChangeListener to the seekbar progress
        int cals = calorieInformation.getPattyType().calories + calorieInformation.getCheeseType().calories;
        calorieInformation.setFinalCalorieCount(cals);
        TextView calorieCount = findViewById(R.id.textView_calCount);
        calorieCount.setText(String.valueOf(calorieInformation.getFinalCalorieCount()));
    }

    /****************************************************************************************
    *                                                                                       *
    * onClick method is override to display the calorie count of the bacon when chosen      *
    *                                                                                       *
    *****************************************************************************************/
    public void onClick(View view)
    {
        TextView calorieCount = findViewById(R.id.textView_calCount);
        CheckBox cb = findViewById(R.id.checkBox_bacon);
        int cals = calorieInformation.getFinalCalorieCount();

        if(cb.isChecked())
        {
            cals = calorieInformation.getFinalCalorieCount() + CalorieInformation.getBaconCalorie();
            calorieCount.setText(String.valueOf(cals));   // Displays calorie count of the bacon in the textview when checked
            calorieInformation.setFinalCalorieCount(cals);
        }
        else if(!cb.isChecked())
        {
            if (cals > 0)
            {
                cals = calorieInformation.getFinalCalorieCount() - CalorieInformation.getBaconCalorie();
            }
            calorieCount.setText(String.valueOf(cals));  // Displays calorie count of the bacon in the textview when not checked
            calorieInformation.setFinalCalorieCount(cals);
        }
    }

    /**********************************************************************************************************
    *                                                                                                         *
    * onCheckedChanged method is override to manage the patty type chosen and display calorie count of patty  *
    *                                                                                                         *
    ***********************************************************************************************************/
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        RadioGroup rgPatty = findViewById(R.id.radiogroup_patty);
        RadioGroup rgCheese = findViewById(R.id.radioGroup_cheese);

    if(group == rgPatty)
    {
        TextView calorieCount = findViewById(R.id.textView_calCount);
        for (int i = 0; i < rgPatty.getChildCount(); i++)
        {
            RadioButton rb = (RadioButton) rgPatty.getChildAt(i);
            if (rb.isChecked())
            {
                int cals = calorieInformation.getFinalCalorieCount();
                if (calorieInformation.getFinalCalorieCount() > 0)
                {
                    PattyType.getCalories(calorieInformation.getPattyType());
                    cals = cals - PattyType.getCalories(calorieInformation.getPattyType());
                }
                calorieInformation.setPattyType(PattyType.getPattyTypeByIndex(i));
                cals = cals + PattyType.getCalories(calorieInformation.getPattyType());
                calorieCount.setText(String.valueOf(cals));    // Displays calorie count of the patty in the textview
                calorieInformation.setFinalCalorieCount(cals);
            }
    }

    }
    else if(group == rgCheese)
    {
        TextView calorieCount = findViewById(R.id.textView_calCount);
        for (int i = 0; i < rgCheese.getChildCount(); i++)
        {
            RadioButton rb = (RadioButton) rgCheese.getChildAt(i);
            if (rb.isChecked())
            {
                int cals = calorieInformation.getFinalCalorieCount();
                if (calorieInformation.getFinalCalorieCount() > 0)
                {
                   CheeseType.getCalories(calorieInformation.getCheeseType());
                    cals = cals - CheeseType.getCalories(calorieInformation.getCheeseType());
                }
                calorieInformation.setCheeseType(CheeseType.getCheeseTypeByIndex(i));
                cals = cals + CheeseType.getCalories(calorieInformation.getCheeseType());
                calorieCount.setText(String.valueOf(cals));  // Displays calorie count of the patty in the textview
                calorieInformation.setFinalCalorieCount(cals);
            }
        }
    }
    }

  /*********************************************************************************************************
  *                                                                                                        *
  * onProgressChanged method is override to calculate the progress of the seekbar for the special sauce    *
  *                                                                                                        *
  **********************************************************************************************************/
  @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
  {
      int cals = calorieInformation.getFinalCalorieCount();
      if (calorieInformation.getSeekBarSaucePercentage() > 0)
      {
          Double val = Math.ceil(calorieInformation.getSeekBarSaucePercentage() * 0.85);
          cals = cals - val.intValue();
      }
      Double val = Math.ceil(progress * 0.85);
      cals = cals + val.intValue();
      calorieInformation.setFinalCalorieCount(cals);
      TextView calorieCount = findViewById(R.id.textView_calCount);
      calorieInformation.setSeekBarSaucePercentage(progress);
      calorieCount.setText(String.valueOf(cals));
    }

    // Override onStartTrackingTouch method
    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    // Override onStopTrackingTouch method
    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}





