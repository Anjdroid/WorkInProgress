package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        Button addTask = (Button) findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name_t = (EditText) findViewById(R.id.editText);
                EditText desc_t = (EditText) findViewById(R.id.editText2);
                EditText video_t = (EditText) findViewById(R.id.editText);
                EditText image_t = (EditText) findViewById(R.id.editText2);
                String name= name_t.getText().toString();
                String desc = desc_t.getText().toString();
                String video= video_t.getText().toString();
                String image = image_t.getText().toString();
                String deadline= "";
                // TODO deadline + category
                // create new task
                Task t = new Task(name, desc, video, image, deadline);
                long task_id = db.createTask(t, 10);

                Intent intent = new Intent(AddTask.this, AllTasks.class);
                startActivity(intent);
            }
        });

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
