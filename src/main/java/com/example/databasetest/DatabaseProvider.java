package com.example.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseProvider extends ContentProvider {
    public static final int CONTACTS_DIR = 0;
    public static final int CONTACTS_ITEM = 1;
   //public static final int CATEGORY_DIR = 2;
   // public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contacts",CONTACTS_DIR);
        uriMatcher.addURI(AUTHORITY,"contacts/#",CONTACTS_ITEM);
      //  uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
       // uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(),"Contacts.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                cursor = db.query("Contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACTS_ITEM:
                String name = uri.getPathSegments().get(1);
                cursor = db.query("Contacts",projection,"name=?",new String[]{ name },null,null,sortOrder);
                break;
           /* case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id=?",new String[]{ categoryId },null,null,sortOrder);
                break;
            default:
                break;*/
        }
       return cursor;
    }


    /*public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }


    public int update(Uri uri, ContentValues values,String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
               updateRows = db.update("Book",values,"id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String catagoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category",values,"id = ?",new String[]{ catagoryId });
                break;
            default:
                break;
        }
          return updateRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
               deletedRows = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book","id = ?",new String[] { bookId });
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String catagoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category","id = ?",new String[] { catagoryId });
                break;
            default:
                break;
        }
        return deletedRows;
    }*/

    @Override
    public String getType(Uri uri) {
       switch (uriMatcher.match(uri)){
           case CONTACTS_DIR:
               return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.contacts";
           case CONTACTS_ITEM:
               return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.contacts";
         /*  case CATEGORY_DIR:
               return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";
           case CATEGORY_ITEM:
               return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";*/
       }
       return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


}