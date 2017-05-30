package com.example.tnuv.work_in_progress;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import java.util.List;

public class CategoryTasks extends AppCompatActivity {
    private DBHelper db;
    int click = 1;

    public class MyListener implements View.OnClickListener {
        int id;
        public MyListener(int id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CategoryTasks.this, TaskNote.class);
            intent.putExtra("id", Integer.toString(this.id));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tasks);
        db = new DBHelper(getApplicationContext());

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
        //location view
        ImageButton locButton = (ImageButton) findViewById(R.id.locView);
        locButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CategoryTasks.this, MapsView.class);
                startActivity(intent);
            }
        });
        //settings
        ImageButton setButton = (ImageButton) findViewById(R.id.set);
        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CategoryTasks.this, Settings.class);
                startActivity(intent);
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 30, 100, 30);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 30, 100, 30);

        LinearLayout rl = (LinearLayout) findViewById(R.id.linear);
        List<Category> cats = db.getAllTags();
        for(Category c: cats) {
            List<Task> tasks = db.getAllTasksByCat(c.getName());
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(layoutParams);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setId(c.getID());
            ll.setBackgroundColor(Color.parseColor(c.getColor()));
            TextView tw = new TextView(this);
            tw.setText(c.getName());
            tw.setGravity(Gravity.CENTER_HORIZONTAL);
            tw.setTypeface(Typeface.create("casual", Typeface.NORMAL));
            tw.setTextSize(25);
            ll.addView(tw);
            for(Task t: tasks) {
                LinearLayout ll2 = new LinearLayout(this);
                ll2.setLayoutParams(layoutParams2);
                CheckBox cb = new CheckBox(this);
                cb.setTypeface(Typeface.create("casual", Typeface.NORMAL));
                cb.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 5f));
                TextView tw2 = new TextView(this);
                tw2.setText(t.getName());
                tw2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
                cb.setId(t.getID());
                tw2.setId(t.getID());
                ll2.addView(cb);
                ll2.addView(tw2);
                ll.addView(ll2);
                ll.setPadding(100, 0, 100, 0);
                tw2.setOnClickListener(new CategoryTasks.MyListener(cb.getId()));
            }
            Button ib = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
            params.setMargins(0, 0, 0, 20);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            ib.setLayoutParams(params);
            ib.setBackgroundResource(R.drawable.button2);
            ib.setText("+");
            ib.setTextColor(Color.parseColor("#FFE600"));
            ib.setTypeface(Typeface.create("casual", Typeface.BOLD));
            ib.setTextSize(20);
            ll.addView(ib);
            rl.addView(ll);
        }
    }


}
