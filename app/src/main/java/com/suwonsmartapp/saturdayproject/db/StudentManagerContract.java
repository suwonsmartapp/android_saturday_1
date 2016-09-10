package com.suwonsmartapp.saturdayproject.db;

import android.provider.BaseColumns;

public final class StudentManagerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public StudentManagerContract() {}

    /* Inner class that defines the table contents */
    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DEPT_NO = "dept_no";
    }

}
