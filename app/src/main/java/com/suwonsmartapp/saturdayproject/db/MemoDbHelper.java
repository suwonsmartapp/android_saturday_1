package com.suwonsmartapp.saturdayproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junsuk on 16. 9. 10..
 */
public class MemoDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Memo.db";

    // CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, dept_no TEXT NOT NULL)
    private static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT DEFAULT '제목 없음', %s TEXT)",
            MemoContract.MemoEntry.TABLE_NAME,
            MemoContract.MemoEntry._ID,
            MemoContract.MemoEntry.COLUMN_NAME_TITLE,
            MemoContract.MemoEntry.COLUMN_NAME_CONTENTS);


    public MemoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 스키마가 변경되었을 때, 업그레이드 처리

        // 기존 데이타 백업

        // 테이블 삭제

        // 테이블 다시 만들기

        // 데이타 복원
    }

}
