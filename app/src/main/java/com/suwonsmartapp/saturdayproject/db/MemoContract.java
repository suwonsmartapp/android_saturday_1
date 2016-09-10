package com.suwonsmartapp.saturdayproject.db;

import android.provider.BaseColumns;

public final class MemoContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MemoContract() {}

    /* Inner class that defines the table contents */
    public static abstract class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENTS = "contents";
    }

}
