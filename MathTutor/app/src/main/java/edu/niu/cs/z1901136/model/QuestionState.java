/***************************************************************************
 *                                                                         *
 * CSCI 522           522 Honors and Graduate Project           Fall 2020  *
 *                                                                         *
 *   Class Name: QuestionState.java                                        *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 04 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to store the operands, operator and    *
 *               answer of the questions generated during the quiz         *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136.model;

public class QuestionState
{
    int leftOperand;           // Used to store the left operand
    int rightOperand;          // Used to store the right operand
    String operator;           // Used to store the operator
    int answer;                // Used to store the answer
    int id;                    // Used to store the ID

    // Constructor with arguments
    public QuestionState(int left, int right, String operator)
    {
        this.leftOperand = left;
        this.rightOperand = right;
        this.operator = operator;
    }

    // Getter method for left operand
    public int getLeftOperand()
    {
        return leftOperand;
    }

    // Getter method for right operand
    public int getRightOperand()
    {
        return rightOperand;
    }

    // Getter method for operator
    public String getOperator()
    {
        return operator;
    }

    // Getter method for answer
    public int getAnswer()
    {
        return answer;
    }

    // Getter method for ID
    public int getId()
    {
        return id;
    }

    // Setter method for answer
    public void setAnswer(int answer)
    {
        this.answer = answer;
    }

    // Setter method for ID
    public void setId(int id)
    {
        this.id = id;
    }
}
