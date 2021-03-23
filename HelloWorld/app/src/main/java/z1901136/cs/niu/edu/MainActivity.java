/***************************************************************
 *                                                             *
 * CSCI 522           Assignment 4              Fall 2020      *
 *                                                             *
 *   Class Name: MainActivity.java                             *
 *                                                             *
 *    Developer: Anusha Kanagala                               *
 *     Due Date: 25 September 2020                             *
 *                                                             *
 *      Purpose: This class is used to display two different   *
 *               messages on each subsequent click of the      *
 *               button independently.                         *
 *                                                             *
 ***************************************************************/

package z1901136.cs.niu.edu;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

    boolean flag = true;   // Used to manage the button click events
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content to activity in activity_main.xml
        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);  //links OnClickListener to the button
    }

/**************************************************************************************************************
*                                                                                                             *
* onClick method is override to display two different messages simultaneously on UI when a button is clicked  *
*                                                                                                             *
***************************************************************************************************************/
    public void onClick(View view)
    {
        TextView txtGreet = findViewById(R.id.TextGreet);
        if(flag == true)
        {
            txtGreet.setText("GoodBye"); // sets the message on textview to GoodBye when button is clicked
            flag = false;
        }
        else if(flag== false)
        {
            txtGreet.setText("Hello"); // sets the message on textview to Hello when button is clicked
            flag = true;
        }
    }
}