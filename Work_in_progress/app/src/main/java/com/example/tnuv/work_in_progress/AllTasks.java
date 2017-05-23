package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        //category view
        ImageButton catButton = (ImageButton) findViewById(R.id.catView);
        catButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AllTasks.this, CategoryTasks.class);
                startActivity(intent);
            }
        });
        //add task
        Button addButton = (Button) findViewById(R.id.addTask);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AllTasks.this, AddTask.class);
                startActivity(intent);
            }
        });
    }
}
