/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 5              Fall 2020      *
 *                                                             *
 *   Class Name: CalorieInformation.java                       *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 10 October 2020                               *
 *                                                             *
 *      Purpose: This class is used store the details of the   *
 *               burger add ons to calculate the calorie       *
 *               count.                                        *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

public class CalorieInformation
{
    public static final int baconCalorie = 150;         // Used to store the bacon calorie
    public static final int specialSauceCalorie = 80;   // Used to store the special sauce calorie

    private int finalCalorieCount;    // Used to store the final calorie count of the burger
    private CheeseType cheeseType;    // Used to store the cheese type and its calories
    private PattyType pattyType;      // Used to store the patty type and its calories

    private int seekBarSaucePercentage;

    // No argument constructor
    CalorieInformation()
    {
        this.cheeseType = CheeseType.NONE;
        this.pattyType = PattyType.BEEF;
        this.finalCalorieCount = 0;
        this.seekBarSaucePercentage = 0;
    }

    // Getter method for Bacon calorie
    public static int getBaconCalorie()
    {
        return baconCalorie;
    }

    // Getter method for special sauce
    public static int getSpecialSauceCalorie()
    {
        return specialSauceCalorie;
    }

    // Getter method for final calorie count
    public int getFinalCalorieCount()
    {
        return finalCalorieCount;
    }

    // Getter method for cheese type
    public CheeseType getCheeseType()
    {
        return cheeseType;
    }

    //Getter method for patty type
    public PattyType getPattyType()
    {
        return pattyType;
    }

    //Getter method for seekbar percentage
    public int getSeekBarSaucePercentage()
    {
        return this.seekBarSaucePercentage;
    }

    //Setter method for cheese type
    public void setCheeseType(CheeseType cheeseType)
    {
        this.cheeseType = cheeseType;
    }

    //Setter method for patty type
    public void setPattyType(PattyType pattyType)
    {
        this.pattyType = pattyType;
    }

    //Setter method for final calorie count
    public void setFinalCalorieCount(int finalCalorieCount)
    {
        this.finalCalorieCount = finalCalorieCount;
    }

    //Setter method for seekbar percentage
    public void setSeekBarSaucePercentage(int seekBarSaucePercentage)
    {
        this.seekBarSaucePercentage = seekBarSaucePercentage;
    }
}
