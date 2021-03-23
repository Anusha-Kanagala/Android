/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 5              Fall 2020      *
 *                                                             *
 *   Class Name: PattyType.java                                *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 10 October 2020                               *
 *                                                             *
 *      Purpose: This class is of Enum type to store the type  *
 *               of the patty and the respective calories      *
 *               for the chosen patty type.                    *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

public enum PattyType
{

    BEEF(50,0),    // Calorie for beef patty and the index of the radio buttons
    TURKEY(180,1),  // Calorie for turkey patty and the index of the radio buttons
    VEGGIE(175, 2), // Calorie for veggie patty and the index of the radio buttons
    NONE(0,3);      // Calorie for no patty chosen and the index of the radio buttons

    int calories;     // Used to store the calorie values
    int order;        // Used to store the order of the patty chosen in the layout

    // Constructor with two arguments
    PattyType(int calories, int order)
    {
        this.calories = calories;
        this.order = order;
    }

    // Getter method for calories
    public static int getCalories(PattyType type)
    {
        for (PattyType ty : PattyType.values())
        {
            if(ty.name().equals(type.name()))
            {
                return ty.calories;     // Returns tha calories assigned with the chosen patty type
            }
        }
        return 0;
    }

    /**************************************************************************************************
    *                                                                                                 *
    * getPattyTypeByIndex method is to return the type of the patty chosen by the user in the layout  *
    *                                                                                                 *
    ***************************************************************************************************/
    public static PattyType getPattyTypeByIndex(int index)
    {
        for (PattyType ty : PattyType.values())
        {
            if(ty.order == index)
            {
                return ty;
            }
        }
        return null;
    }
}
