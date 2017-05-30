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

        setUpDatabase();

        List ts = db.getAllTasks();

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
            rl.addView(cb);
            click++;
            cb.setOnClickListener(new MyListener(cb.getId()));
        }
    }

    public void setUpDatabase() {
        db = new DBHelper(getApplicationContext());

        // create categories
        Category c1 = new Category("Food", "ffa500", "");
        Category c2 = new Category("Home", "ffa500", "");
        Category c3 = new Category("Transport", "ffa500", "");
        Category c4 = new Category("Clothing", "ffa500", "");
        Category c5 = new Category("Health", "ffa500", "");
        Category c6 = new Category("Savings", "ffa500", "");
        Category c7 = new Category("Education", "ffa500", "");
        Category c8 = new Category("Cosmetics", "ffa500", "");
        Category c9 = new Category("Fun", "ffa500", "");

        // insert categories in db
        long cat1 = db.createCategory(c1);
        long cat2 = db.createCategory(c2);
        long cat3 = db.createCategory(c3);
        long cat4 = db.createCategory(c4);
        long cat5 = db.createCategory(c5);
        long cat6 = db.createCategory(c6);
        long cat7 = db.createCategory(c7);
        long cat8 = db.createCategory(c8);
        long cat9 = db.createCategory(c9);

        // create tasks
        Task t1 = new Task("kupi jogurt", "to je opis", "ni videa","ni slike", "15. 6. 2017");
        Task t2 = new Task("kupi brisače", "to je opis", "ni videa","ni slike", "");
        Task t3 = new Task("nova karta za trolo", "to je opis", "ni videa","ni slike", "");
        Task t4 = new Task("kupi kratke hlače", "to je opis", "ni videa","ni slike", "15. 7. 2017");
        Task t5 = new Task("nalgesin", "to je opis", "ni videa","ni slike", "20. 8. 2017");
        Task t6 = new Task("50e za počitnice na stran", "to je opis", "ni videa","ni slike", "13. 6. 2017");
        Task t7 = new Task("vrni knjige", "to je opis", "ni videa","ni slike", "");
        Task t8 = new Task("kupi puder", "to je opis", "ni videa","ni slike", "25. 6. 2017");
        Task t9 = new Task("aqua luna", "to je opis", "ni videa","ni slike", "");
        Task t10 = new Task("kupi mleko", "to je opis", "ni videa","ni slike", "");

        //insert tasks into db under categories
        long t1_id = db.createTask(t1, cat1);
        long t2_id = db.createTask(t2, cat2);
        long t3_id = db.createTask(t3, cat3);
        long t4_id = db.createTask(t4, cat4);
        long t5_id = db.createTask(t5, cat5);
        long t6_id = db.createTask(t6, cat6);
        long t7_id = db.createTask(t7, cat7);
        long t8_id = db.createTask(t8, cat8);
        long t9_id = db.createTask(t9, cat9);
        long t10_id = db.createTask(t10, cat1);
    }
}
