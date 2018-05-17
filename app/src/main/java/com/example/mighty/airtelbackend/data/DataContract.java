package com.example.mighty.airtelbackend.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    //might need to add +"/"+
//    public static final String CONTENT_AUTHORITY = "com.example.mighty.airtelbackend";
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //content constants
    private DataContract() {
    }

//    public static String getSQL_create_entries() {
//        return SQL_create_entries;
//    }

    public static final class DataEntry implements BaseColumns {
        //content URI to access the petsProvider
//        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        //table and column names as constraints
        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_NAME_id = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        //Gender options below
        public static final int GENDER_MALE = 0;
        public static final int GENDER_FEMALE = 1;
        public static final int GENDER_UNKNOWN = 2;

//        public static boolean isValidGender(Integer gender) {
//            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
//                return true;
//            }
//            return false;
//        }
    }
}
