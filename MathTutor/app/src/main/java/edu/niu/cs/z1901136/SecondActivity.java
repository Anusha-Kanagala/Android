/***************************************************************************
 *                                                                         *
 * CSCI 522           522 Honors and Graduate Project           Fall 2020  *
 *                                                                         *
 *   Class Name: SecondActivity.java                                       *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 04 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to manage the questions of the quiz.   *
 *               Also evaluates the answers to display total score         *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import edu.niu.cs.z1901136.model.ChoiceEnum;
import edu.niu.cs.z1901136.model.QuestionState;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener
{
    int correctCount = 0;                         // Used to store the count of questions answered correctly
    int totalCount = 0;                           // Used to store the count of total questions attempted in the quiz
    QuestionState currentQuestionState = null;    // initialize current question state object
    int nextButtonCount =0;                       // Used to store next button count
    int timeRemaining = 60;                       // Used to store the time availed to take quiz

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        handleApp();
    }

    /********************************************************************************************************
    *                                                                                                       *
    * handleApp method is used to display questions in 60 seconds of time and an option to start quiz again *
    *                                                                                                       *
    *********************************************************************************************************/
    private void handleApp()
    {
        setContentView(R.layout.activity_second);
        TextView tv = findViewById(R.id.textView_question);
        ProgressBar progressTimer = findViewById(R.id.progressBar);
        progressTimer.setProgress(0);                                   // set progress of progress bar to zero
        Button startOver = findViewById(R.id.button_startOver);

        // make start over button invisible when started taking quiz
        startOver.setVisibility(View.GONE);

        /*******************************************************************************************
        *                                                                                          *
        * setOnClickListener method is implemented to override onClick method when a button to     *
        * start quiz again  is clicked                                                             *
        *                                                                                          *
        ********************************************************************************************/
        startOver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Button nextQuestion = findViewById(R.id.button_next);
                nextQuestion.setVisibility(View.VISIBLE);
                Button startOver = findViewById(R.id.button_startOver);
                startOver.setVisibility(View.GONE);
                timeRemaining = 60;         // Set the timer to 60 seconds
                correctCount = 0;           // Set correct answers count to zero
                totalCount = 0;             // Set total count of questions to zero
                handleApp();                // invoke handleapp() method to take quiz again
            }
        });

        // Implement the abstract methods of abstract class CountDownTimer to implement timer in quiz
        CountDownTimer timer = new CountDownTimer(60000,1000)
        {
            ProgressBar timerProgress = findViewById(R.id.progressBar);
                TextView startTimer = findViewById(R.id.textView_startTimer);

            /************************************************************************
            *                                                                       *
            * override onTick method to manage timer for 60 seconds to take quiz    *
            *                                                                       *
            *************************************************************************/
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeRemaining--;
                startTimer.setText(Integer.toString(timeRemaining) + "sec");
                timerProgress.setProgress(60 - timeRemaining);
            }

            /************************************************************************************************
            *                                                                                               *
            * override onFinish method to manage the resources in the layout when time is up to take quiz   *
            * and display the score                                                                         *
            *                                                                                               *
            *************************************************************************************************/
            @Override
            public void onFinish()
            {
                Button choiceOne = findViewById(R.id.button_choice1);
                Button choiceTwo = findViewById(R.id.button_choice2);
                Button choiceThree = findViewById(R.id.button_choice3);
                Button choiceFour = findViewById(R.id.button_choice4);
                Button nextQuestion = findViewById(R.id.button_next);
                Button startOver = findViewById(R.id.button_startOver);

                choiceOne.setEnabled(false);             // Disable the button for choice one
                choiceTwo.setEnabled(false);             // Disable the button for choice two
                choiceThree.setEnabled(false);           // Disable the button for choice three
                choiceFour.setEnabled(false);            // Disable the button for choice four
                nextQuestion.setVisibility(View.GONE);   // make next button invisible when timer is up
                startOver.setVisibility(View.VISIBLE);   // make startOver button visible to take quiz again
                TextView score = findViewById(R.id.textView_score);

                // set text of the textview to display the score of the quiz taken
                score.setText(" Congrats!! You scored " + String.valueOf(correctCount) + " out of " + String.valueOf(totalCount));
            }
        }.start();

       createQuestionState(tv);      // Invoke createQuestionState method to display question in the textview

        // setOnClickListener for choice answer buttons
        Button choiceOne = findViewById(R.id.button_choice1);
        choiceOne.setOnClickListener(this);
        Button choiceTwo = findViewById(R.id.button_choice2);
        choiceTwo.setOnClickListener(this);
        Button choiceThree = findViewById(R.id.button_choice3);
        choiceThree.setOnClickListener(this);
        Button choiceFour = findViewById(R.id.button_choice4);
        choiceFour.setOnClickListener(this);

        Button nextQuestion = findViewById(R.id.button_next);
        nextQuestion.setOnClickListener(new View.OnClickListener()
        {
            /**************************************************************************************
            *                                                                                     *
            * override onClick method to move to the next question when next button is clicked    *
            *                                                                                     *
            ***************************************************************************************/
            @Override
            public void onClick(View v)
            {
                //Random random = new Random();
                TextView tv = findViewById(R.id.textView_question);
                createQuestionState(tv);         // Invoke createQuestionState method to display question in the textview
            }
        });
  }

    /*********************************************************************************************
    *                                                                                            *
    * createQuestionState method is used to generate questions and display in the textview       *
    *                                                                                            *
    **********************************************************************************************/
    private void createQuestionState(TextView tv)
    {
        // Create instance of Random class to generate numbers randomly from 1 to 15
        RandomMath rm = new RandomMath(1, 15);
        QuestionState qs = new QuestionState(rm.getRandomNumberOne(), rm.getRandomNumberTwo(), rm.pickRandomOperator());

        // set text in the textview with the question
        tv.setText(qs.getLeftOperand() + qs.getOperator() + qs.getRightOperand());
        int answer = rm.getAnswer(qs);              // invoke getAnswer method with QuestionState object
        qs.setAnswer(answer);                       // invoke setAnswer method
        setChoiceValues(qs);                        // invoke setChoiceValues method with QuestionState object
        this.currentQuestionState = qs;
    }

    /*********************************************************************************************
    *                                                                                            *
    * setChoiceValues method is used to generate questions and display in the textview       *
    *                                                                                            *
    **********************************************************************************************/
    private void setChoiceValues(QuestionState qs)
    {
        Set<Integer> answerSet = new HashSet<>();    // set demonstration using Hashset
        Random random = new Random();                // create instance of Random class
        int choice = random.nextInt(4);      // generate four random numbers to manage choices
        answerSet.add(qs.getAnswer());              // add answer to answerSet

        // Iterate on each choice button and set answers randomly to any of the buttons
        for (ChoiceEnum choiceEnum :ChoiceEnum.values())
        {
            Button currentChoiceButton = findViewById(choiceEnum.getId());
            if (choiceEnum.getPos() == choice)
            {
                currentChoiceButton.setText(String.valueOf(qs.getAnswer()));
                qs.setId(choiceEnum.getId());
            }
            else
            {
                random = new Random();
                int value = random.nextInt(30);
                while (!answerSet.contains(value))
                {
                    answerSet.add(value);
                    currentChoiceButton.setText(String.valueOf(value));
                }
            }
        }
    }

    /**************************************************************************************
    *                                                                                     *
    * override onClick method to manage the correct answer count and total count          *
    *                                                                                     *
    ***************************************************************************************/
    @Override
    public void onClick(View v)
    {
        totalCount++;
        if (v.getId() == currentQuestionState.getId())
        {
            correctCount++;
            nextButtonCount++;
        }
        else
        {
            nextButtonCount++;
        }
    }
}