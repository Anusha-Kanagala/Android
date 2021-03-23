/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 5              Fall 2020      *
 *                                                             *
 *   Class Name: CheeseType.java                               *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 10 October 2020                               *
 *                                                             *
 *      Purpose: This class is of Enum type to store the type  *
 *               of the cheese and the respective calories     *
 *               for the chosen cheese type.                   *
 *                                                             *
 ***************************************************************/

package edu.niu.cs.z1901136;

public enum CheeseType {
    NONE(0, 0),
    CHEDDAR(110, 1),
    MOZARELLA(180, 2);

    int calories;       // Used to store the calorie values
    int order;          // Used to store the order of the cheese chosen in the layout

    // Constructor with two arguments
    CheeseType(int calories, int order)
    {
        this.calories = calories;
        this.order = order;
    }

    // Getter method for calories
    public static int getCalories(CheeseType type)
    {
        for (CheeseType ty : CheeseType.values())
        {
            if (ty.name().equals(type.name()))
            {
                return ty.calories;      // Returns tha calories assigned with the chosen cheese type
            }
        }
        return 0;
    }

    /*******************************************************************************************************
    *                                                                                                      *
    * getCheeseTypeByIndex method is to return the type of the cheese chosen by the user in the layout     *
    *                                                                                                      *
    ********************************************************************************************************/
    public static CheeseType getCheeseTypeByIndex(int index)
    {
        for (CheeseType ty : CheeseType.values())
        {
            if (ty.order == index)
            {
                return ty;
            }
        }
        return null;
    }
}
