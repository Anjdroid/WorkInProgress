package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskNote extends AppCompatActivity {

    private DBHelper db;
    private String id1;
    private int idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_note);

        //edit task
        ImageButton setButton = (ImageButton) findViewById(R.id.set);
        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TaskNote.this, EditTask.class);
                intent.putExtra("id", Integer.toString(idd));
                startActivity(intent);
            }
        });

        db = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        idd = Integer.parseInt(intent.getStringExtra("id"));
        long task_id = Long.parseLong(id1);
        // get task with said id
        Task t = db.getTask(task_id);

        TextView name = (TextView) findViewById(R.id.textView);
        name.setText(t.getName());
        TextView desc = (TextView) findViewById(R.id.editText2);
        desc.setText(String.valueOf(t.getDescription()));
        // TODO: get category spinner and set spinner value to task category
        TextView video = (TextView) findViewById(R.id.editText3);
        video.setText(t.getVideo());
        TextView image = (TextView) findViewById(R.id.editText4);
        image.setText(t.getImage());
        TextView deadline = (TextView) findViewById(R.id.editText5);
        deadline.setText(t.getDeadline());

        // set data in view
    }
}
