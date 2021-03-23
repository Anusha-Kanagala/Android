/***************************************************************************
 *                                                                         *
 * CSCI 522           522 Honors and Graduate Project           Fall 2020  *
 *                                                                         *
 *   Class Name: ChoiceEnum.java                                           *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 04 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is of Enum type to manage the four answer      *
 *               choice buttons and its position in the second activity    *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136.model;

import edu.niu.cs.z1901136.R;

public enum ChoiceEnum
{
    CHOICE_ONE(R.id.button_choice1, 0),     // Used to manage button for choice 1
    CHOICE_TWO((R.id.button_choice2),1),    // Used to manage button for choice 2
    CHOICE_THREE((R.id.button_choice3),2),  // Used to manage button for choice 3
    CHOICE_FOUR((R.id.button_choice4), 3);  // Used to manage button for choice 4

    int id;            // Used to store the resource ID of respective button
    int pos;           // Used to store the position of the buttons

    // Constructor with arguments
    ChoiceEnum(int id, int pos)
    {
        this.id = id;
        this.pos = pos;
    }

    // Getter method for ID
    public int getId()
    {
        return id;
    }

    // Getter method for position
    public int getPos()
    {
        return pos;
    }

    // Setter method for ID
    public void setId(int id)
    {
        this.id = id;
    }

    // Setter method for position
    public void setPos(int pos)
    {
        this.pos = pos;
    }
}
