package com.example.tnuv.work_in_progress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


/**
 * Created by CaptainBat on 23. 05. 2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 16;

    // Database Name
    private static final String DATABASE_NAME = "todoDB";

    // tables
    private static final String TABLE_TASK = "task";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_TASK_CAT = "task_category";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NAME_TASK = "name_task";

    private static final String KEY_DESC = "description";
    private static final String KEY_VID = "video";
    private static final String KEY_IMG = "image";
    private static final String KEY_CREATED = "created";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_DONE = "done";

    private static final String KEY_COL = "color";
    private static final String KEY_LOC = "location";

    private static final String KEY_CAT_ID = "category_id";
    private static final String KEY_TASK_ID = "task_id";

    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME_TASK
            + " TEXT," + KEY_DESC + " TEXT," + KEY_VID
            + " TEXT," + KEY_IMG + " TEXT," + KEY_CREATED + " TEXT,"+  KEY_DEADLINE + " TEXT,"+ KEY_DONE + " INTEGER" + ")";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_COL + " TEXT," + KEY_LOC + " TEXT"+ ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_TASK_CATEGORY = "CREATE TABLE "
            + TABLE_TASK_CAT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TASK_ID + " INTEGER," + KEY_CAT_ID + " INTEGER" + ")";

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK_CAT);

        // create new tables
        onCreate(db);
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /* METHODS FOR TABLE TASK */

    /**
    * Creating a task
    * */
    public long createTask(Task t, long cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_TASK, t.getName());
        values.put(KEY_DESC, t.getDescription());
        values.put(KEY_VID, t.getVideo());
        values.put(KEY_IMG, t.getImage().toString());
        values.put(KEY_CREATED, getDateTime());
        values.put(KEY_DEADLINE, t.getDeadline().toString());

        // insert row
        long task_id = db.insert(TABLE_TASK, null, values);

        // assigning categories to task
        createCategoryTask(task_id, cat_id);

        return task_id;
    }

    /**
     * get a single task
     */
    public Task getTask(long task_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " WHERE "
                + KEY_ID + " = " + task_id;

        Log.e("LOG", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Task t = new Task();
        t.setID(c.getInt(c.getColumnIndex(KEY_ID)));
        t.setName((c.getString(c.getColumnIndex(KEY_NAME_TASK))));
        t.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
        t.setVideo((c.getString(c.getColumnIndex(KEY_VID))));
        // TODO: how to set image?
        t.setImage(c.getString(c.getColumnIndex(KEY_IMG)));
        // TODO: dates?
        t.setCreated(c.getString(c.getColumnIndex(KEY_CREATED)));
        t.setDeadline(c.getString(c.getColumnIndex(KEY_DEADLINE)));
        t.setDone(c.getInt(c.getColumnIndex(KEY_DONE)));

        return t;
    }

    /**
     * getting all tasks
     * */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK;

        Log.e("LOG", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Task t = new Task();
                t.setID(c.getInt(c.getColumnIndex(KEY_ID)));
                t.setName((c.getString(c.getColumnIndex(KEY_NAME_TASK))));
                t.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
                t.setVideo((c.getString(c.getColumnIndex(KEY_VID))));
                // TODO: how to set image?
                t.setImage(c.getString(c.getColumnIndex(KEY_IMG)));
                // TODO: dates?
                t.setCreated(c.getString(c.getColumnIndex(KEY_CREATED)));
                t.setDeadline(c.getString(c.getColumnIndex(KEY_DEADLINE)));
                t.setDone(c.getInt(c.getColumnIndex(KEY_DONE)));

                tasks.add(t);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * getting all tasks under single category name
     * */

    public List<Task> getAllTasksByCat(String cat_name) {
        List<Task> tasks = new ArrayList<Task>();

        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " td, "
                + TABLE_CATEGORY + " tg, " + TABLE_TASK_CAT + " tt WHERE tg."
                + KEY_NAME + " = '" + cat_name + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_CAT_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_TASK_ID;

        Log.e("LOG", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Task t = new Task();
                t.setID(c.getInt(c.getColumnIndex(KEY_ID)));
                t.setName((c.getString(c.getColumnIndex(KEY_NAME_TASK))));
                t.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
                t.setVideo((c.getString(c.getColumnIndex(KEY_VID))));
                // TODO: how to set image?
                t.setImage(c.getString(c.getColumnIndex(KEY_IMG)));
                // TODO: dates?
                t.setCreated(c.getString(c.getColumnIndex(KEY_CREATED)));
                t.setDeadline(c.getString(c.getColumnIndex(KEY_DEADLINE)));
                t.setDone(c.getInt(c.getColumnIndex(KEY_DONE)));
                Log.d("lala", t.getName());
                // add to task list
                tasks.add(t);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * Updating a task
     */
    public int updateTask(Task t) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_TASK, t.getName());
        values.put(KEY_DESC, t.getDescription());
        values.put(KEY_VID, t.getVideo());
        values.put(KEY_IMG, t.getImage().toString());
        values.put(KEY_DEADLINE, t.getDeadline().toString());

        // updating row
        return db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(t.getID()) });
    }

    /**
     * Set task done
     */
    public void taskIsDone(Task t, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DONE, value);

        db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(t.getID()) });
    }

    /**
     * Deleting a task
     */
    public void deleteTask(long task_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?",
                new String[] { String.valueOf(task_id) });
    }

    /* METHODS FOR TABLE CATEGORY */

    /**
     * Creating a category
     */
    public long createCategory(Category cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cat.getName());
        values.put(KEY_COL, cat.getColor());
        values.put(KEY_LOC, cat.getLocation());

        // insert row
        long cat_id = db.insert(TABLE_CATEGORY, null, values);

        return cat_id;
    }

    /**
     * getting all categories
     * */
    public List<Category> getAllTags() {
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        Log.e("LOG", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category ca = new Category();
                ca.setID(c.getInt((c.getColumnIndex(KEY_ID))));
                ca.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                ca.setColor(c.getString(c.getColumnIndex(KEY_COL)));
                ca.setLocation(c.getString(c.getColumnIndex(KEY_LOC)));

                // adding to category list
                categories.add(ca);
            } while (c.moveToNext());
        }
        return categories;
    }

    /**
     * get a single category
     */
    public Category getCategory(long cat_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " WHERE "
                + KEY_ID + " = " + cat_id;

        Log.e("LOG", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Category c1 = new Category();
        c1.setID(c.getInt(c.getColumnIndex(KEY_ID)));
        c1.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        c1.setColor(c.getString(c.getColumnIndex(KEY_COL)));
        c1.setLocation((c.getString(c.getColumnIndex(KEY_LOC))));

        return c1;
    }

    /**
     * Updating a category
     */
    public int updateCategory(Category cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cat.getName());

        // updating row
        return db.update(TABLE_CATEGORY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(cat.getID()) });
    }

    /**
     * Deleting a category (+ deletes all tasks if boolean is true)
     */
    public void deleteCategory(Category cat, boolean should_delete_all_cat_tasks) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting category
        // check if tasks under this category -- should also be deleted
        if (should_delete_all_cat_tasks) {
            // get all tasks under this category
            List<Task> allCatTasks = getAllTasksByCat(cat.getName());

            // delete all tasks
            for (Task t : allCatTasks) {
                // delete task
                deleteTask(t.getID());
            }
        }
        // now delete the category
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?",
                new String[] { String.valueOf(cat.getID()) });
    }

    /* METHOD FOR TABLE CATEGORY_TASK */

    /*
     * Creating category_task -> assigning a category to a task
     */
    public long createCategoryTask(long task_id, long cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID, task_id);
        values.put(KEY_CAT_ID, cat_id);

        long id = db.insert(TABLE_TASK_CAT, null, values);

        return id;
    }

    public Category getTaskCategory(long task_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TASK_CAT + " WHERE "
                + KEY_TASK_ID + " = " + task_id;

        Log.e("LOG", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        long idd = c.getLong(c.getColumnIndex(KEY_CAT_ID));
        Log.e("cursor", String.valueOf(idd));

        Category c1 = getCategory(idd);

        return c1;
    }

    /**
     * Updating a task category
     */
    public int updateTaskCat(long id, long cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_ID, cat_id);

        // updating row
        return db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /**
     * Deleting a task category
     */
    public void deleteTaskCat(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
