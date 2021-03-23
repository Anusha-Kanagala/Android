/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: DeleteActivity.java                                       *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to display the items to be deleted     *
 *               from the database when the associated radio button is     *
 *               selected                                                  *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity
{
    SQLiteDBHelper myDB_Helper = new SQLiteDBHelper(this);
    ListView listview_delete;
    SimpleCursorAdapter simpleCsrAdptr_delete;
    Cursor cursor;
    RadioAdapter radio_Adapter;
    private ArrayList<String> postDeleteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_delete);
        listview_delete = (ListView) findViewById(R.id.mylist_delete);
        Button back = findViewById(R.id.button_back);
        Button delete_all = findViewById(R.id.delete_All);

        // Implements onClick method of OnClickListener interface
        back.setOnClickListener(new View.OnClickListener()
        {
            /*************************************************************************************
            *                                                                                    *
            * onClick method is override to move to main menu when clicked on back button        *
            *                                                                                    *
            **************************************************************************************/
            @Override
            public void onClick(View v)
            {
                // Create instance of Intent
                Intent intent = new Intent(DeleteActivity.this, SecondActivity.class);
                startActivity(intent);   // starting activity
            }
        });

        // Implement onClick method of OnClickListener interface
        delete_all.setOnClickListener(new View.OnClickListener()
        {
            /*************************************************************************************
            *                                                                                    *
            * onClick method is override to delete all items from the table in database          *
            *                                                                                    *
            **************************************************************************************/
            @Override
            public void onClick(View v)
            {
               Boolean isDeleteSuccesfull = myDB_Helper.deleteAll();   // Invoke method deleteAll to delete all items from table
                Cursor data = myDB_Helper.getListData();               // Invoke method getListData
                if(isDeleteSuccesfull)
                {
                    listview_delete.setAdapter(null);                 // set the Adapter data to the ListView
                }
            }
        });

        cursor = myDB_Helper.deleteItemsList();           // invoke deleteItemsList and assign data to cursor

        // data regarding which column to get data from the table
        String[] columns_to_get_data_from = new String[]{
                SQLiteDBHelper.column_two,
        };
        // List of views to put the data in
        int[] itemviews_to_place_data_in = new int[]{
                R.id.textview_listItem,};

        // instance of SimpleCursorAdapter
        simpleCsrAdptr_delete = new SimpleCursorAdapter(DeleteActivity.this,
                R.layout.activity_delete,
                cursor,
                columns_to_get_data_from,
                itemviews_to_place_data_in,
                0);

        // set adapter data to the listview
        listview_delete.setAdapter(simpleCsrAdptr_delete);

        // instance of RadioAdapter constructor
        radio_Adapter = new RadioAdapter(this,
                R.layout.activity_delete,
                cursor,
                columns_to_get_data_from,
                itemviews_to_place_data_in,
                R.id.radiobutton_list,
                myDB_Helper
        );
        // set adapter data to the listview
        listview_delete.setAdapter(radio_Adapter);
    }
}