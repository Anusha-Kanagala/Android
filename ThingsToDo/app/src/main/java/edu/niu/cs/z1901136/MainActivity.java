/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: MainActivity.java                                         *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to add new items to the listview  in   *
 *               the database and  display the available items in the list *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    SQLiteDBHelper myDB_Helper;
    private EditText newItem_list;
    private Button add_btn, back_btn;
    private ListView item_List;
    private TextView itemsToAdd;
    private ArrayList<String> newly_added_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsToAdd = findViewById(R.id.textview_items_add);
        newItem_list = findViewById(R.id.editText_list);
        add_btn = findViewById(R.id.button_addToList);
        back_btn = findViewById(R.id.button_back);
        item_List = findViewById(R.id.list_of_items);

        // Implement OnClickListener interface
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            /*******************************************************************************************
            *                                                                                          *
            * onClick method is override to move to main menu after additon of new items to the list   *
            *                                                                                          *
            ********************************************************************************************/
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);      // starting second Activity
            }
        });

        // Implement OnClickListener interface
        add_btn.setOnClickListener(new View.OnClickListener()
        {
            /**********************************************************************************************
            *                                                                                             *
            * onClick method is override to add the items to the list in the database from the edit text  *
            *                                                                                             *
            ***********************************************************************************************/
            @Override
            public void onClick(View v)
            {
                // Clear the array list on every addition of new item to avoid duplicates in the list
                newly_added_items.clear();
                String item_added = newItem_list.getText().toString();  // get the new item value from the EditText box

                if(newItem_list.length() != 0)     // Checks if a new item is available to add to the list
                {
                    AddItems(item_added, false);      // invoke method AddItems with new item and checkbox state
                    newItem_list.setText("");                    // Empty edit text box
                }
                else
                {
                    // Displays toast message if there is no new item given in the edit text
                    Toast.makeText(MainActivity.this, "Please write something in the EditText to add items to the list",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /**********************************************************************************************
    *                                                                                             *
    * AddItems method is override to add new items to the list in the database from the edit text *
    *                                                                                             *
    ***********************************************************************************************/
    public void AddItems(String newItem, boolean isComplete)
    {
        myDB_Helper = new SQLiteDBHelper(this);
        boolean insertItem = myDB_Helper.insertItem(newItem, isComplete);   // invoke method insertItem

        myDB_Helper = new SQLiteDBHelper(this);
        Cursor data = myDB_Helper.getListData();      // invoke method getListData() and store the data in cursor

            while(data.moveToNext())   // checks if there is data available in cursor
            {
                newly_added_items.add(data.getString(1));      // add new item to the ArrayList
                if(newly_added_items == null)
                {
                    Toast.makeText(MainActivity.this, "OOPS!! Empty Database ",Toast.LENGTH_LONG).show();
                }
                else
                {
                ListAdapter listAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,newly_added_items);
                item_List.setAdapter(listAdapter);     // set the Adapter data to the ListView
                }
            }
        }
    }
