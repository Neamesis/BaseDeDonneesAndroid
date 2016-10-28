package com.humanbooster.basededonnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Nea on 26/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_ID = "_id";
    public static final String CONTACT_COLUMN_FIRSTNAME = "firstname";
    public static final String CONTACT_COLUMN_PHONE = "telephone";


    private static final String DB_NAME = "phonebookDB";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contact ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "firstname TEXT,"
                + "telephone TEXT);"
        );
        insertContact(db, "Nea", "Julie", "0606060606");
        insertContact(db, "Deleglise", "Kevin", "0102030502");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS contact");
        // onCreate(db);
    }

    public boolean insertContact (SQLiteDatabase db, String name, String firstname,  String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("firstname", firstname);
        contentValues.put("telephone", phone);
        db.insert("contact", null, contentValues);
        return true;
    }

}
