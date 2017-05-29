package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class AllTasks extends AppCompatActivity {
    DBHelper db;
    int click = 1;

    public class MyListener implements View.OnClickListener {
        int id;
        public MyListener(int id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AllTasks.this, TaskNote.class);
            intent.putExtra("id", Integer.toString(this.id));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        db = new DBHelper(getApplicationContext());

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

        LinearLayout rl = (LinearLayout) findViewById(R.id.linear);
        List<Task> taski = db.getAllTasks();
        for(Task t: taski) {
            CheckBox cb = new CheckBox(this);
            cb.setTypeface(Typeface.create("casual", Typeface.NORMAL));
            cb.setPadding(100, 0, 100, 0);
            cb.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            cb.setText(t.getName());
            cb.setId(t.getID());
            float hel2 = (float) ((click-1)*130);
            cb.setTranslationY(hel2);
            click++;
            cb.setOnClickListener(new MyListener(cb.getId()));
        }
    }
}
