package com.example.android.inventoryappstageone.data;

import android.provider.BaseColumns;

/**
 * Created by khadijah on 1/31/2018.
 */
public final class BooksContract {
    public static final class BooksEntry implements BaseColumns {
        public final static String TABLE_NAME = "books";
        public final static String COLUMN_BOOK_NAME = "name";//STRING
        public final static String COLUMN_BOOK_PRICE = "price";//INTEGER
        public final static String COLUMN_BOOK_QUANTITY = "quantity";//INTEGER
        public final static String COLUMN_BOOK_SUPPLIER_NAME = "supplierName";//STRING
        public final static String COLUMN_BOOK_SUPPLIER_PHONE = "supplierPhone";//INTEGER
        public final static String COLUMN_BOOK_SUPPLIER_EMAIL = "supplierMail";//STRING
    }
}
