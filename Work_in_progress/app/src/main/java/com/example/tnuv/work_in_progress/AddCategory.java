package com.example.tnuv.work_in_progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddCategory extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Spinner spinner = (Spinner) findViewById(R.id.color_spinner);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.color_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        // initialize database helper
        db = new DBHelper(getApplicationContext());

        // get category data from view
        String name= "";
        String color = "";
        String location = "";

        // create new category
        Category c = new Category(name, color, location);
        long cat_id = db.createCategory(c);
    }

}
