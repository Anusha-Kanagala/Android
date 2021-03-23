/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: SecondActivity.java                                       *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to display the list of items available *
 *               in the database and also contains menu options to add new *
 *               items or delete existing tasks from the list              *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SecondActivity extends AppCompatActivity
{
    SQLiteDBHelper myDB_Helper;
    ListView listview;
    SimpleCursorAdapter simpleCsrAdptr;
    Cursor cursor;
    ListDataAdapter lstAdapter;
    Context context;
    long[] checkedIdList;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listview = (ListView) findViewById(R.id.mylist);
        myDB_Helper = new SQLiteDBHelper(this);   // create instance of SQLiteDBHelper
        cursor = myDB_Helper.retrieveItems();             // Invoke method retrieveItems to display list data

        // data regarding which column to get data from the table
        String[] columns_to_get_data_from = new String[]{
                SQLiteDBHelper.column_two,
        };

        // List of views to put the data in
        int[] itemviews_to_place_data_in = new int[]{
                R.id.Textview_displayList,
        };

        // instance of SimpleCursorAdapter
       simpleCsrAdptr = new SimpleCursorAdapter(this,
                R.layout.list_items,
                cursor,
                columns_to_get_data_from,
                itemviews_to_place_data_in,
               0);
        // get and instance of SimpleCursorAdapter the listviewitem_record layout
        listview.setAdapter(simpleCsrAdptr);

        // instance of ListDataAdapter
        lstAdapter = new ListDataAdapter(this,
                R.layout.list_items,
                cursor,
                columns_to_get_data_from,
                itemviews_to_place_data_in,
                R.id.checkbox_list,
                myDB_Helper);

        // set the Adapter data to the listview
        listview.setAdapter(lstAdapter);
    }

    /*******************************************************************************************
    *                                                                                          *
    * onCreateOptionsMenu method is to create layout with Add, delete icons for to do list     *
    *                                                                                          *
    ********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*********************************************************************************************
    *                                                                                            *
    * onOptionsItemSelected method is used to move to another activity on click of add or delete *
    * list icons in main menu.                                                                   *
    *                                                                                            *
    **********************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // checks if the menu item selected is the add list icon
            case R.id.addList_icon:
                Intent intent = new Intent(this,
                        MainActivity.class);
                this.startActivity(intent);
                return true;
            // checks if the menu item selected is the delete list icon
            case R.id.deleteList_icon:
                Intent intent_delete = new Intent(this,
                        DeleteActivity.class);
                this.startActivity(intent_delete);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}