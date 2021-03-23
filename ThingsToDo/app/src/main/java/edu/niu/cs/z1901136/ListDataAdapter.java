/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: ListDataAdapter.java                                      *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to manage the checkbox state of the    *
 *               list data displayed from the database when the state is   *
 *               updated                                                   *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ListDataAdapter extends CursorAdapter
{
    private Context listContext;
    private Cursor cursor;
    private String[] listColumns;
    private int[] views;
    private int listLayout;
    private boolean[] selected;
    private int checkBoxView;
    private SQLiteDBHelper dbHelper;

    // Constructor for the Custom Cursor Adapter
   ListDataAdapter(Context context, int layout, Cursor csr, String[] from_columns, int[] to_views, int checkbox_view, SQLiteDBHelper myDB_helper)
   {
        super(context,csr, false);
        listContext = context;
        listLayout = layout;
        cursor = csr;
        listColumns = from_columns;
        views = to_views;
        checkBoxView = checkbox_view;
        dbHelper = myDB_helper;
    }

    // Implement newView method of CursorAdapter to create a new view to hold the cursor data
    @Override
    public View newView(Context context, Cursor csr, ViewGroup parent)
    {
       //Initialise an int array for the checkboxes
        selected = new boolean[csr.getCount()];
        return LayoutInflater.from(context).inflate(listLayout,parent,false);
    }

    // Implement bindView method of CursorAdapter to bind existing view to the cursor data
    @Override
    public void bindView(View view, Context context,  Cursor csr)
    {
           // int restore_cursor_position = cursor.getPosition();

        // Place the data from the cursor into the respective TextView
        for (int i = 0; i < listColumns.length; i++)
        {
            ((TextView) view.findViewById(views[i])).setText(csr.getString(csr.getColumnIndex(listColumns[i])));
        }

        final String item = csr.getString(csr.getColumnIndex("ITEM"));  // get the value of column in the the table

        // Set the checkbox
        final CheckBox currentCheckBox = (CheckBox) view.findViewById(checkBoxView);
        currentCheckBox.setChecked(Boolean.parseBoolean(csr.getString(csr.getColumnIndex("isComplete"))));
        currentCheckBox.setTag(new Long(cursor.getLong(cursor.getColumnIndex(SQLiteDBHelper.column_two))));

        // Implement onCheckedChanged of OnCheckedChangeListener to update the checkbox state
        currentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                dbHelper.updateItem(item, isChecked);     // Invoke method updateItem to manage checkbox state in database
            }
        });
    }
}

