package com.example.tnuv.work_in_progress;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CaptainBat on 23. 05. 2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todoManager";

    // tables
    private static final String TABLE_TASK = "task";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_TASK_CAT = "task_category";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private static final String KEY_DESC = "description";
    private static final String KEY_VID = "video";
    private static final String KEY_IMG = "image";
    private static final String KEY_CREATED = "created";
    private static final String KEY_PH_NO = "deadline";

    private static final String KEY_COL = "color";
    private static final String KEY_LOC = "location";

    private static final String KEY_CAT_ID = "category_id";
    private static final String KEY_TASK_ID = "task_id";

    private static final String CREATE_TABLE_TASK = "CREATE TABLE IF NOT EXSIST "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME
            + " TEXT, " + KEY_DESC + " TEXT, " + KEY_VID
            + " TEXT, " + KEY_IMG +" IMAGE, " + KEY_CREATED + "DATETIME, "+  KEY_PH_NO + "DATETIME "+ ")";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXSIST " + TABLE_CATEGORY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_COL + " TEXT," + KEY_LOC + "TEXT "+ ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_TASK_CATEGORY = "CREATE TABLE IF NOT EXSIST "
            + TABLE_TASK_CAT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TASK_ID + " INTEGER," + KEY_CAT_ID + " INTEGER " + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_TASK_CATEGORY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
    }

    // add task

    // add category

    // delete category

    // delete task

    // update task

    // update category

    // get single task

    // get single category

    // get all tasks

    // get all categories

}
