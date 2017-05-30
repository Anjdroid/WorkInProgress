package com.example.tnuv.work_in_progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
        /*ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);*/

        db = new DBHelper(getApplicationContext());

        loadSpinnerData(db, spinner);

        // get task data from view
        String name = "";
        String description = "";
        String video = "";
        String image = "";
        String deadline = "";
        Task t = new Task(name, description, video, image, deadline);

        // get category id from view
        long cat_id = 1;

        // create new task
        long task_id = db.createTask(t, cat_id);

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
