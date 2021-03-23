/***************************************************************************
 *                                                                         *
 * CSCI 522                    Assignment 8                     Fall 2020  *
 *                                                                         *
 *   Class Name: SQLiteDBHelper.java                                       *
 *                                                                         *
 *    Developer: Anusha Kanagala                                           *
 *     Due Date: 09 December 2020                                          *
 *                                                                         *
 *      Purpose: This class is used to manage Create table, Retrieve,      *
 *               update and delete the data of the list from the           *
 *               database                                                  *
 *                                                                         *
 ***************************************************************************/

package edu.niu.cs.z1901136;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class SQLiteDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "list.db";     // Used to store name of the databse
    private static final String TABLE_NAME = "myItems_List";  // Used to store name of the table
    public static final String column_one = "_id";            // Used to store the name of first column
    public static final String column_two =  "ITEM";          // Used to store the name of second column
    public static final String column_three = "isComplete";   // Used to store the name of third column

    // Constructor of the class SQLiteDBHelper
    public SQLiteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 12);
    }

    /****************************************************************
    *                                                               *
    * onCreate method is override to create table in the database   *
    *                                                               *
    *****************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + column_one + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                column_two + " TEXT" + ");";
        db.execSQL(createTable);
    }

    /****************************************************************************
    *                                                                           *
    * onUpgrade method is override to upgrade the database from older version   *
    *                                                                           *
    *****************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String dropTable = "DROP TABLE " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    /**************************************************************************
    *                                                                         *
    * insertItem method is used to insert new data to the database            *
    *                                                                         *
    ***************************************************************************/
    public boolean insertItem(String item1, boolean isComplete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();              // Create instance of ContentValues
        contentValues.put(column_two, item1);                           // add the new item to add to Contentvalues
        contentValues.put(column_three, String.valueOf(isComplete));    // add the new item to add to Contentvalues

        long list_data = db.insert(TABLE_NAME, null, contentValues);  // Insert data to the table

        // Checks if data is inserted in to the table or not
        if(list_data == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    /****************************************************************************************
    *                                                                                       *
    * getListData method is used to retrieve the list of data available from the database   *
    *                                                                                       *
    *****************************************************************************************/
    public Cursor getListData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //select query to execute
        Cursor data = db.rawQuery("SELECT * FROM " +  TABLE_NAME,null);
        return data;
    }

    /**********************************************************************************************
    *                                                                                             *
    * retrieveItems method is used to retrieve the list of data available from the database   *
    *                                                                                             *
    ***********************************************************************************************/
    public Cursor retrieveItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //select query to execute
        Cursor data = db.rawQuery("SELECT *  FROM " +  TABLE_NAME,null);
        return data;
    }

    /******************************************************************************************************
    *                                                                                                     *
    * deleteItemsList method is used to retrieve the list of data available to delete from the database   *
    *                                                                                                     *
    *******************************************************************************************************/
    public Cursor deleteItemsList()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Select query to execute
        Cursor data = db.rawQuery("SELECT *  FROM " +  TABLE_NAME,null);
        return data;
    }

    /*********************************************************************************************************
    *                                                                                                        *
    * updateItem method is used to update value of the state of the checkbox in the database when changed    *
    *                                                                                                        *
    **********************************************************************************************************/
    public boolean updateItem(String item, boolean isComplete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Update query to execute
        String updateItem = "UPDATE " + TABLE_NAME + " SET " + column_three + " = " + "'" +  String.valueOf(isComplete) + "'" + " WHERE "
                + column_two + " = '" + item + "'";
        db.execSQL(updateItem);
        return true;
    }

    /**********************************************************************************************
    *                                                                                             *
    * deleteItem method is used to delete a row from the table when a radio button is selected    *
    *                                                                                             *
    ***********************************************************************************************/
    public boolean deleteItem(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete query to execute
        String deleteItem = "DELETE FROM " + TABLE_NAME+ " WHERE "+column_two+"='"+item+"'";
        db.execSQL(deleteItem);
        return true;
    }

    /*******************************************************************************************************
    *                                                                                                      *
    * postDeleteItemsList method is used to display list of data available in the database after deletion  *
    *                                                                                                      *
    ********************************************************************************************************/
    public Cursor postDeleteItemsList()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT *  FROM " + TABLE_NAME, null);
        return data;
    }

    /******************************************************************
    *                                                                 *
    * deleteAll method is used to delete all the rows from the table  *
    *                                                                 *
    *******************************************************************/
    public boolean deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        return true;
    }
}
