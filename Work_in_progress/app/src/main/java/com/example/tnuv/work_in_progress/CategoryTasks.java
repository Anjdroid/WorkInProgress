package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

public class CategoryTasks extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tasks);

        //all tasks view
        ImageButton addButton = (ImageButton) findViewById(R.id.listView);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CategoryTasks.this, AllTasks.class);
                startActivity(intent);
            }
        });

        //add category
        Button addCat = (Button) findViewById(R.id.addTask);
        addCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CategoryTasks.this, AddCategory.class);
                startActivity(intent);
            }
        });

        Log.d("lala", "lala");

        db = new DBHelper(getApplicationContext());
        List<Category> cats = db.getAllTags();
        Log.d("cats", cats.toString());
        for (Category c:cats) {
            List<Task> tasks = db.getAllTasksByCat(c.getName());
            Log.d("taks", tasks.toString());
        }


    }


}
