package com.example.tnuv.work_in_progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditTask extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);

        db = new DBHelper(getApplicationContext());

        loadSpinnerData(db, spinner);

        // get task id from view
        long task_id = 1;
        Task t = db.getTask(task_id);

        // set data in view as instance

        // get updated data from view
        String name = "";
        String description = "";
        String video = "";
        String image = "";
        String deadline = "";

        long cat_id = 1;

        Task t2 = new Task(name, description, video, image, deadline);

        // update task
        int updateTask = db.updateTask(t2);
        // also update category of task
        int update = db.updateTaskCat(task_id, cat_id);

    }

    private void loadSpinnerData(DBHelper db, Spinner spinner) {

        // Spinner Drop down elements
        List<String> names = new ArrayList<String>();
        List<Category> categories = db.getAllTags();
        for (Category c : categories) {
            names.add(c.getName());
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


}
