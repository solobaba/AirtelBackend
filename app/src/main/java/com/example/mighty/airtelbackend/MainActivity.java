package com.example.mighty.airtelbackend;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mighty.airtelbackend.data.DataDBHelper;
import com.example.mighty.airtelbackend.data.DataContract;
import com.example.mighty.airtelbackend.data.DataContract.DataEntry;


public class MainActivity extends AppCompatActivity {

    private DataDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test text to see SQL input line
//        TextView testText = (TextView) findViewById(R.id.testText1);
//        testText.setText(PetContract.getSQL_create_entries());

        // Setup FAB to open EditorActivity
       FloatingActionButton fab = findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, EditorActivity.class);
               startActivity(intent);
           }
       });


        mDbHelper = new DataDBHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    private void displayDatabaseInfo() {
        DataDBHelper mDbHelper = new DataDBHelper(this);
        // Create and/or open a database to read from it
        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);
//part of a test to display data
//        String[] projection = {DataEntry.COLUMN_NAME_id, DataEntry.COLUMN_PET_NAME, DataEntry.COLUMN_PET_BREED, DataEntry.COLUMN_PET_GENDER, DataEntry.COLUMN_PET_WEIGHT,};
//
//        //Cursor cursor = db.query(PetEntry.TABLE_NAME, projection, null, null, null, null, null);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataEntry.TABLE_NAME, null);
//        getContentResolver().query(DataEntry.CONTENT_URI,projection,null,null,null);


        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount() + " pets" + "\n\n");

//            displayView.append((DataEntry._ID + " | " + DataEntry.COLUMN_PET_NAME + " | " + DataEntry.COLUMN_PET_BREED + " | " + DataEntry.COLUMN_PET_GENDER + " | " + DataEntry.COLUMN_PET_WEIGHT + "\n"));
//            int idColumnIndex = cursor.getColumnIndex(DataEntry._ID);
//            int nameColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_PET_NAME);
//            int breedColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_PET_BREED);
//            int genderColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_PET_GENDER);
//            int weightColumIndex = cursor.getColumnIndex(DataEntry.COLUMN_PET_WEIGHT);
//
//            while (cursor.moveToNext()) {
//
//                int currentID = cursor.getInt(idColumnIndex);
//                String currentName = cursor.getString(nameColumnIndex);
//                String currentBreed = cursor.getString(breedColumnIndex);
//                String currentGender = cursor.getString(genderColumnIndex);
//
//                switch (currentGender) {
//                    case "0":
//                        currentGender = "Male";
//                        break;
//                    case "1":
//                        currentGender = "Female";
//                        break;
//                    case "2":
//                        currentGender = "unKnown";
//                        break;
//                }
//
//                String currentWeight = cursor.getString(weightColumIndex);
//
//                displayView.append(("\n" + currentID + " | " + currentName + " | " + currentBreed + " | " + currentGender + " | " + currentWeight));
//            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertPet(String name, String breed, int gender, String weight) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_PET_NAME, name);
        values.put(DataEntry.COLUMN_PET_BREED, breed);
        values.put(DataEntry.COLUMN_PET_GENDER, gender);
        values.put(DataEntry.COLUMN_PET_WEIGHT, weight);

        long newRowId = db.insert(DataEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New Row ID " + newRowId);

//        Uri newUri = getContentResolver().insert(DataEntry.CONTENT_URI,values);

        //displays a toast message contraining the line number
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet("toto", "saint bernard", DataContract.DataEntry.GENDER_MALE, "10");
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
