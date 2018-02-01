package com.example.android.inventoryappstageone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.inventoryappstageone.data.BooksContract;
import com.example.android.inventoryappstageone.data.BooksDbHelper;

public class MainActivity extends AppCompatActivity {

    private BooksDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new BooksDbHelper(this);

        //Insert dummy Book to Books.db
        InsertBooks();

        //Read data from Books.db
        Cursor cursor = ReadFromDb();

        //display the results as LOG message
        displayResults(cursor);
    }

    public void InsertBooks() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues booksValues = new ContentValues();

        //Create a ContentValues object where column names are the keys
        //ADD DUMMY book DATA (INSERT)
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_NAME, "khadijah");
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_PRICE, 200);
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_QUANTITY, 10);
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_NAME, "Amazone");
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_EMAIL, "amazone@aa.com");
        booksValues.put(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_PHONE, 122345678);

        // Insert a new row for the current book in the database, returning the ID of that new row.
        // The first argument for db.insert() is the Books table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for current book.
        db.insert(BooksContract.BooksEntry.TABLE_NAME, null, booksValues);

        //Show results in log
        Log.d("MESSAGE", "Insert to DB done successfully");
    }

    public Cursor ReadFromDb() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // select all columns in the table
        String[] projection = {BooksContract.BooksEntry._ID,
                BooksContract.BooksEntry.COLUMN_BOOK_NAME,
                BooksContract.BooksEntry.COLUMN_BOOK_PRICE,
                BooksContract.BooksEntry.COLUMN_BOOK_QUANTITY,
                BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_NAME,
                BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_PHONE,
                BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_EMAIL};

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the books table.
        //SELECT --READ DATA
        Cursor cursor = db.query(BooksContract.BooksEntry.TABLE_NAME,//The table to query
                projection, //The columns to return
                null,//The columns for the WHERE clause
                null,//The values for the WHERE clause
                null,//Don't group the rows
                null,//Don't filter by row groups
                null); // The sort order

        return cursor;
    }

    public void displayResults(Cursor cursor) {
        //Create a string to hold the contents of table books
        StringBuilder displayView = new StringBuilder();
        try {
            // Create a header in the String that looks like this:
            //id - name - price - quantity - supplierName - supplierPhone - supplierEmail
            displayView.append("The books table contains " + cursor.getCount() + " books.\n\n"); // Display the number of rows in the Cursor
            displayView.append(BooksContract.BooksEntry._ID + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_NAME + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_PRICE + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_QUANTITY + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_NAME + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_PHONE + " - " +
                    BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_EMAIL + "\n");

            /*-----------------------HOW TO ACCESS THE VALUES INSIDE THE TABLE-----------------------*/
            // Extract the index of each column, this index will be used to extract the values for each row
            int idColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry._ID);//0
            int nameColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_NAME);//1
            int priceColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_PRICE);//2
            int quantityColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_QUANTITY);//3
            int supplierNameColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_NAME);//4
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_PHONE);//5
            int supplierEmailColumnIndex = cursor.getColumnIndex(BooksContract.BooksEntry.COLUMN_BOOK_SUPPLIER_EMAIL);//6

            // Iterate through all the returned rows in the cursor
            //ROW BY ROW
            while (cursor.moveToNext()) {
                //the row index will change each iteration && the colomn index is fixed
                // Use that index of the coloumn to extract the String or Int value of the word at the current row the cursor is on.
                int currentBookID = cursor.getInt(idColumnIndex);
                String currentBookName = cursor.getString(nameColumnIndex);
                int currentBookPrice = cursor.getInt(priceColumnIndex);
                int currentBookQuantity = cursor.getInt(quantityColumnIndex);
                String currentBookSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentBookSupplierPhone = cursor.getInt(supplierPhoneColumnIndex);
                String currentBookSupplierEmail = cursor.getString(supplierEmailColumnIndex);
                //Now finished access One Row

                // Add the values for each column of the current row
                // in the cursor >>> in the String variable
                //Add full one row contents to the string variable
                displayView.append(("\n" + currentBookID + " - " + currentBookName + " - " +
                        currentBookPrice + " - " + currentBookQuantity + " - " + currentBookSupplierName + " - " +
                        currentBookSupplierPhone + " - " + currentBookSupplierEmail));
            }
            //Now finished access all rows in the tables, Finally display the quiry result as LOG message
            Log.d("RESULTS", displayView.toString());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}