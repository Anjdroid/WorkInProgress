package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    private DBHelper db;
    private List<String> names;
    private List<Category> categories;
    private Category selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);

        db = new DBHelper(getApplicationContext());

        loadSpinnerData(db, spinner);

        // back button
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });

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

                DatePicker date = (DatePicker) findViewById(R.id.datePicker2);
                int day1 = date.getDayOfMonth();
                int month1 = date.getMonth() + 1;
                int year1 = date.getYear();

                String deadline = Integer.toString(day1)+". "+Integer.toString(month1)+". "+Integer.toString(year1);
                Log.d("dd", deadline);

                // create new task
                Task t = new Task(name, desc, video, image, deadline);
                long task_id = db.createTask(t, selectedCategory.getID());
                Intent intent = new Intent(AddTask.this, AllTasks.class);
                startActivity(intent);
            }
        });

    }

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener()
    {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            selectedCategory = categories.get(pos);
            Log.e("lala", selectedCategory.getName());

        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };

    private void loadSpinnerData(DBHelper db, Spinner spinner) {

        // Spinner Drop down elements
        names = new ArrayList<String>();
        categories = db.getAllTags();
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
        spinner.setOnItemSelectedListener(spinnerListener);
    }


}
