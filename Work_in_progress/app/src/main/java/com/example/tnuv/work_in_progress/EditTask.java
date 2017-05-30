package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditTask extends AppCompatActivity {

    private DBHelper db;
    private String id1;
    private int idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);

        db = new DBHelper(getApplicationContext());

        loadSpinnerData(db, spinner);

        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        final long task_id = Long.parseLong(id1);
        Task t = db.getTask(task_id);

        // set data in view as instance
        EditText name = (EditText) findViewById(R.id.editText);
        name.setText(t.getName());
        EditText desc = (EditText) findViewById(R.id.editText2);
        desc.setText(String.valueOf(t.getDescription()));
        // TODO: get category spinner and set spinner value to task category
        EditText video = (EditText) findViewById(R.id.editText3);
        video.setText(t.getVideo());
        EditText image = (EditText) findViewById(R.id.editText4);
        image.setText(t.getImage());
        // TODO: set date

        //save button
        Button saveButoon = (Button) findViewById(R.id.addTask);
        saveButoon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.editText)).getText().toString();
                String desc = ((EditText) findViewById(R.id.editText2)).getText().toString();
                String video = ((EditText) findViewById(R.id.editText3)).getText().toString();
                String image = ((EditText) findViewById(R.id.editText4)).getText().toString();
                // TODO: deadline and category
                Task t = db.getTask(task_id);
                t.setName(name);
                t.setDescription(desc);
                t.setVideo(video);
                t.setImage(image);
                db.updateTask(t);
                Task r2 = db.getTask(task_id);
                String lala = t.getName();
                Log.e("ime", lala);

                Intent intent = new Intent(EditTask.this, AllTasks.class);
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
