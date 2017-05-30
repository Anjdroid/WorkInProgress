package com.example.tnuv.work_in_progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TaskNote extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_note);

        db = new DBHelper(getApplicationContext());

        long task_id = 1;
        // get task with said id
        Task t = db.getTask(task_id);

        // set data in view
    }
}
