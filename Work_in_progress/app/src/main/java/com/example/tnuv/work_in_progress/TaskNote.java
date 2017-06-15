package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

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

        // delete task
        ImageButton trashButton = (ImageButton) findViewById(R.id.trash);
        trashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // delete task
                db.deleteTask(idd);
                // go to all tasks activity
                Intent intent = new Intent(TaskNote.this, AllTasks.class);
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
        desc.setText("Description:  "+String.valueOf(t.getDescription()));
        TextView video = (TextView) findViewById (R.id.editText3);
        video.setText(t.getVideo());

        //image
        String url = t.getImage();
        Drawable dd = LoadImage(url);
        LinearLayout ll = (LinearLayout) findViewById(R.id.note);
        if (dd != null) {
            ImageView iv = new ImageView(this);
            iv.setBackground(dd);
            ll.addView(iv);
        }
        else {
            TextView tv = new TextView(this);
            tv.setText("no image");
            ll.addView(tv);
        }
        /*TextView image = (TextView) findViewById(R.id.editText4);
        image.setText(t.getImage());*/

        TextView deadline = (TextView) findViewById(R.id.editText5);
        deadline.setText("Deadline:  "+t.getDeadline());

        TextView category = (TextView) findViewById(R.id.editText22);
        Category c = db.getTaskCategory(task_id);
        category.setText("Category:  "+c.getName());

        ScrollView sv = (ScrollView) findViewById(R.id.scroll);
        sv.setBackgroundColor(Color.parseColor(c.getColor()));


        // set data in view
    }

    public static Drawable LoadImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
