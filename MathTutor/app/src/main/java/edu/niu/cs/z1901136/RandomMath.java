/***************************************************************************
 *                                                                         *
 * CSCI 522           522 Honors and Graduate Project           Fall 2020  *
 *                                                                         *
 *   Class Name: RandomMath.java                                           *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 04 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to generate the random numbers and     *
 *               operator for questions in the quiz                       *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import java.util.Random;

import edu.niu.cs.z1901136.model.QuestionState;

public class RandomMath
{
    private int randomNumberOne;         // Used to store first random number
    private int randomNumberTwo;         // Used to store second random number

    // Getter method for first number
    public int getRandomNumberOne()
    {
        return randomNumberOne;
    }

    // Getter method for second number
    public int getRandomNumberTwo()
    {
        return randomNumberTwo;
    }

    // Constructor with arguments to generate numbers within the bound
    RandomMath(int min, int max)
    {
        Random random = new Random();          // Create instance of Random class
        this.randomNumberOne = random.nextInt(max - min) + min;
        this.randomNumberTwo = random.nextInt(max - min) + min;
    }

    /*******************************************************************************************
    *                                                                                          *
    *  getRandomOperatorIndex method is to generate position index to pick operator randomly   *
    *                                                                                          *
    ********************************************************************************************/
    static String getRandomOperatorIndex(String[] arr)
    {
        Random random = new Random();          // Create instance of Random class
        int i = random.nextInt(arr.length);    // generate numbers maximum of the length of the array passed
        return arr[i];
    }

    /*******************************************************************************************
    *                                                                                          *
    * pickRandomOperator method is to generate operator randomly for each question             *
    *                                                                                          *
    ********************************************************************************************/
    static String pickRandomOperator()
    {
        String[] operator = {"+", "-", "/", "*"};
        String optr = getRandomOperatorIndex(operator);
        return optr;
    }

    /*******************************************************************************************
    *                                                                                          *
    * getAnswer method is to evaluate the answer based on the question state object passed     *
    *                                                                                          *
    ********************************************************************************************/
    public int getAnswer(QuestionState qs)
    {
        int answer = 0;
        if(qs.getOperator().equals("+"))                             // Checks if the operator equals "+"
        {
            answer = qs.getLeftOperand() + qs.getRightOperand();     // Returns answer with addition of operands
        }
        else if(qs.getOperator().equals("-"))                        // Checks if the operator equals "-"
        {
            answer = qs.getLeftOperand() - qs.getRightOperand();     // Returns answer with subtraction of operands
        }
        else if(qs.getOperator().equals("*"))                        // Checks if the operator equal "*"
        {
            answer = qs.getLeftOperand() * qs.getRightOperand();     // Returns answer with multiplication of operands
        }
        else if (qs.getOperator().equals("/"))                       // Checks if the operator equal "/"
        {
            answer = qs.getLeftOperand() / qs.getRightOperand();     // Returns answer with division of operands
        }
        return answer;
    }
}

