package com.sarmale.myleads.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBLeadsHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBDefinition.LeadsEntry.TABLE_NAME + " (" +
                    DBDefinition.LeadsEntry._ID + " INTEGER PRIMARY KEY," +
                    DBDefinition.LeadsEntry.COLUMN_NAME_NAME + " TEXT," +
                    DBDefinition.LeadsEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    DBDefinition.LeadsEntry.COLUMN_NAME_PHONE + " TEXT," +
                    DBDefinition.LeadsEntry.COLUMN_NAME_EMAIL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBDefinition.LeadsEntry.TABLE_NAME;

    public DBLeadsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
