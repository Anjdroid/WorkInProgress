package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryTasks extends AppCompatActivity {

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
    }


}
