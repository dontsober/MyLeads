package com.sarmale.myleads.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.sarmale.myleads.model.DBDefinition;
import com.sarmale.myleads.model.DBLeadsHelper;
import com.sarmale.myleads.model.Lead;

import java.util.ArrayList;
import java.util.List;

public class DBLeadController {
    Context context;
    String DEFAULT_WHERE_CLAUSES=null;
    String [] DEFAULT_WHERE_PARAMETERS=null;

    public DBLeadController(Context context){
        this.context=context;
    }

    public Long writeLeadDB(Lead lead)
    {
        DBLeadsHelper dbHelper = new DBLeadsHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBDefinition.LeadsEntry.COLUMN_NAME_NAME, lead.getName());
        values.put(DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME, lead.getLastName());
        values.put(DBDefinition.LeadsEntry.COLUMN_NAME_PHONE, lead.getPhone());
        values.put(DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL, lead.getEmail());

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBDefinition.LeadsEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    public List<Lead> readAllLeads(String whereClause, String [] whereParameters){
        DBLeadsHelper dbHelper = new DBLeadsHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DBDefinition.LeadsEntry.COLUMN_NAME_NAME,
                DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME,
                DBDefinition.LeadsEntry.COLUMN_NAME_PHONE,
                DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL
        };

        String sortOrder =
                DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME + " DESC";

        Cursor cursor = db.query(
                DBDefinition.LeadsEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                whereClause,              // The columns for the WHERE clause
                whereParameters,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List <Lead> leads = new ArrayList<Lead>();
        while(cursor.moveToNext()) {
           // long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)); not necesary here
            Lead lead = new Lead();
            lead.setName(cursor.getString(cursor.getColumnIndex(DBDefinition.LeadsEntry.COLUMN_NAME_NAME)));
            lead.setLastName(cursor.getString(cursor.getColumnIndex(DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME)));
            lead.setPhone(cursor.getString(cursor.getColumnIndex(DBDefinition.LeadsEntry.COLUMN_NAME_PHONE)));
            lead.setEmail(cursor.getString(cursor.getColumnIndex(DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL)));
            lead.setId(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)));
            leads.add(lead);

        }
        cursor.close();
        db.close();
        dbHelper.close();
        return leads;
    }

    public List<Lead> readAllLeads() {
        return readAllLeads(DEFAULT_WHERE_CLAUSES, DEFAULT_WHERE_PARAMETERS);
    }

    public List<Lead> searchLeadByID(String id)
    {

        String whereClause = BaseColumns._ID + " = ?";
        String[] whereArgs = new String[] {
                id
        };
        return readAllLeads(whereClause, whereArgs);
    }

    public Boolean updateLead(Lead lead)
    {
       /* String updateQuery = "UPDATE "+DBDefinition.LeadsEntry.TABLE_NAME+" SET "
                + DBDefinition.LeadsEntry.COLUMN_NAME_NAME +" = "+lead.getName()
                + DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME +" = "+lead.getLastName()
                + DBDefinition.LeadsEntry.COLUMN_NAME_PHONE +" = "+lead.getPhone()
                + DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL +" = "+lead.getEmail()
                + " WHERE " + BaseColumns._ID + " = " + lead.getId();

        //String updateQuery = "UPDATE "+DBDefinition.LeadsEntry.TABLE_NAME+" SET name = "+"'"++"' "+ "WHERE salary = "+"'"++"'";

        DBLeadsHelper dbHelper = new DBLeadsHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(updateQuery);*/
        ContentValues cv = new ContentValues();

        cv.put(DBDefinition.LeadsEntry.COLUMN_NAME_NAME,lead.getName());
        cv.put(DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME,lead.getLastName());
        cv.put(DBDefinition.LeadsEntry.COLUMN_NAME_PHONE,lead.getPhone());
        cv.put(DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL,lead.getEmail());
        DBLeadsHelper dbHelper = new DBLeadsHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id= db.update(DBDefinition.LeadsEntry.TABLE_NAME, cv, "_id = ?", new String[]{String.valueOf(lead.getId())});
        if(id > 0) {
            return true;
        }
        return false;

    }


    public boolean deleteLead(Lead lead)
    {

        DBLeadsHelper dbHelper = new DBLeadsHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Integer rowId = lead.getId();
        Boolean result = db.delete(DBDefinition.LeadsEntry.TABLE_NAME, BaseColumns._ID + "=" + rowId, null) > 0;
        return result;
    }
}
