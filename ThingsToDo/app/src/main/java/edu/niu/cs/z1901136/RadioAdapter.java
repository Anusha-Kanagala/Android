/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: RadioAdapter.java                                         *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to manage the items to be deleted      *
 *               from the database when the associated radio button is     *
 *               selected                                                  *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class RadioAdapter extends CursorAdapter
{
    private Context listContext_delete;
    private Cursor cursor_delete;
    private String[] listColumns_delete;
    private int[] views_delete;
    private int listLayout_delete;
    private boolean[] selected;
    private int radio_button_view;
    private SQLiteDBHelper dbHelper;

    // Constructor for the Radio Adapter
    RadioAdapter(Context context, int layout, Cursor csr, String[] from_columns, int[] to_views, int radioButtonView, SQLiteDBHelper myDB_helper)
    {
        super(context,csr, false);
        listContext_delete = context;
        listLayout_delete = layout;
        cursor_delete = csr;
        listColumns_delete = from_columns;
        views_delete = to_views;
        radio_button_view = radioButtonView;
        dbHelper = myDB_helper;
    }

    // Implement newView method of CursorAdapter to create a new view to hold the cursor data
    @Override
    public View newView(Context context, Cursor csr, ViewGroup parent)
    {
        //Initialise an int array for the checkboxes
       selected = new boolean[csr.getCount()];
        return LayoutInflater.from(context).inflate(listLayout_delete,parent,false);
    }

    // Implement bindView method of CursorAdapter to bind existing view to the cursor data
    @Override
    public void bindView(final View view_c, Context context, Cursor csr)
    {
        for (int i = 0; i < listColumns_delete.length; i++)
        {
            ((TextView) view_c.findViewById(views_delete[i])).setText(csr.getString(csr.getColumnIndex(listColumns_delete[i])));
        }

        final String item = csr.getString(csr.getColumnIndex("ITEM"));
        final RadioButton currentRadioButton = (RadioButton) view_c.findViewById(radio_button_view);

        currentRadioButton.setOnClickListener(new View.OnClickListener()
        {
            /*************************************************************************************
            *                                                                                    *
            * onClick method is override to delete selected item from the table in the database  *
            *                                                                                    *
            **************************************************************************************/
            @Override
            public void onClick(View v)
            {
               boolean isDeleteSuccessful = dbHelper.deleteItem(item);    // Invoke method deleteItem
              //Checks if delete is succesful and updates the list of items in the table
               if(isDeleteSuccessful){
                   cursor_delete = dbHelper.postDeleteItemsList();

                   // Displays the available list data after delete
                   while(cursor_delete.moveToNext())
                   {
                       ((TextView) view_c.findViewById(views_delete[0])).setText(cursor_delete.getString(cursor_delete.getColumnIndex("ITEM")));
                        notifyDataSetChanged();
                   }
               }
            }
        });
    }
}
