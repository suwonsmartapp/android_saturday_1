package com.suwonsmartapp.saturdayproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junsuk on 16. 9. 10..
 */
public class StudentMangerDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentManger.db";

    // CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, dept_no TEXT NOT NULL)
    private static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL)",
            StudentManagerContract.StudentEntry.TABLE_NAME,
            StudentManagerContract.StudentEntry._ID,
            StudentManagerContract.StudentEntry.COLUMN_NAME_NAME,
            StudentManagerContract.StudentEntry.COLUMN_NAME_DEPT_NO);


    public StudentMangerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 스키마가 변경되었을 때, 업그레이드 처리
    }

}
