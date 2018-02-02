package com.example.android.inventoryappstageone.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by khadijah on 1/31/2018.
 */
public class BooksDbHelper extends SQLiteOpenHelper {
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * Name of the database file
     */
    public static final String DATABASE_NAME = "Books.db";

    //Constructor
    public BooksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the books table
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BooksContract.BooksEntry.TABLE_NAME + " ("
                + BooksContract.BooksEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BooksContract.BooksEntry.COLUMN_BOOK_NAME + " TEXT, "
                + BooksContract.BooksEntry.COLUMN_BOOK_PRICE + " INTEGER, "
                + BooksContract.BooksEntry.COLUMN_BOOK_QUANTITY + " INTEGER, "
                + BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_NAME + " STRING, "
                + BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_PHONE + " INTEGER, "
                + BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_EMAIL + " TEXT);";
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}