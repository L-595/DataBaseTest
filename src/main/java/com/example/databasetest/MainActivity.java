package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SimPhonebookContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //private MyDatabaseHelper dbHelper;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/contacts");
                ContentValues values = new ContentValues();// 开始组装第一条数据
                values.put("name", "张三");
                values.put("phone", "111111");
                values.put("gender", "男");
                Uri Uri = getContentResolver().insert(uri,values);
                newId = Uri.getPathSegments().get(1);

                values.clear(); //开始组装第二条数据
                values.put("name", "李四");
                values.put("phone", "22222");
                values.put("gender", "女");
                Uri Uri1 = getContentResolver().insert(uri,values);
                newId = Uri1.getPathSegments().get(2);

                /*db.insert("Book", null, values); // 插入第一条数据

                values.put("name", "数学类");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据*/
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/contacts"+ newId);
              /*  ContentValues values = new ContentValues();
                values.put("name", "数学类");
                values.put("pages", 510);
                values.put("price", 19.95);
                getContentResolver().update(uri,values,null,null);*/
            }
        });
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/contacts");
                getContentResolver().delete(uri,null,null);
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.databasetest.provider/contacts");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                //查询表中所有数据
                if (cursor !=null) {
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String phone = cursor.getString(cursor.getColumnIndex("phone"));
                        String gender = cursor.getString(cursor.getColumnIndex("gender"));
                        Log.d("MainActivity", " name is " + name);
                        Log.d("MainActivity", " phone number is " + phone);
                        Log.d("MainActivity", " gender is " + gender);

                    }
                    cursor.close();
                }

            }
        });
    }
}