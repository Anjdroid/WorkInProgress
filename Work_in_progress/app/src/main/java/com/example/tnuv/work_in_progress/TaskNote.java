package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskNote extends AppCompatActivity {

    private DBHelper db;
    private String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_note);

        db = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        long task_id = Long.parseLong(id1);
        // get task with said id
        Task t = db.getTask(task_id);

        TextView name = (TextView) findViewById(R.id.textView);
        name.setText(t.getName());
        EditText desc = (EditText) findViewById(R.id.editText2);
        desc.setText(String.valueOf(t.getDescription()));
        // TODO: get category spinner and set spinner value to task category
        EditText video = (EditText) findViewById(R.id.editText3);
        video.setText(t.getVideo());
        EditText image = (EditText) findViewById(R.id.editText4);
        image.setText(t.getImage());
        EditText deadline = (EditText) findViewById(R.id.editText5);
        deadline.setText(t.getDeadline());

        // set data in view
    }
}
