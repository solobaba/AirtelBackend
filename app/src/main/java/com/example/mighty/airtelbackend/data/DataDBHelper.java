package com.example.mighty.airtelbackend.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.mighty.airtelbackend.data.DataContract.DataEntry;

public class DataDBHelper extends SQLiteOpenHelper {

//    public static final String LOG_TAG = DataDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "shelter.db";
    private static int DATABASE_VERSION = 1;

    public DataDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table was made in petContract, so im just using the same reference to allow for easier code refactor later
        String SQL_CREATE_PETS_TABLE  = "CREATE TABLE " + DataEntry.TABLE_NAME + "(" +
                DataEntry.COLUMN_NAME_id + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " +
                DataEntry.COLUMN_PET_NAME + " TEXT NOT NULL" + ", " +
                DataEntry.COLUMN_PET_BREED + " TEXT" + ", " +
                DataEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL" + ", " +
                DataEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //still at version 1 so nothing should need to be changed
    }
}
