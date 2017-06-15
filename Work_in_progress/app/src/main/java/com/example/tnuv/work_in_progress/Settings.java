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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class Settings extends AppCompatActivity {
    private DBHelper db;

    public class MyListener implements View.OnClickListener {
        int id;
        public MyListener(int id){
            this.id = id;
        }
        @Override
        public void onClick(View v) {
            Category c = db.getCategory(this.id);
            //db.deleteCategory(c, true);
            // A TUKI ZIHR SE TUT TASKI ODSTRANIJO?
            // DELETETASK
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DBHelper(getApplicationContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 0, 100, 0);

        LinearLayout lin = (LinearLayout) findViewById(R.id.linearno);
        List<Category> catt = db.getAllTags();
        int i = 0;
        for(Category c: catt) {
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(layoutParams);
            TextView tw = new TextView(this);
            tw.setText(c.getName());
            tw.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            tw.setId(c.getID());
            RelativeLayout rl = new RelativeLayout(this);
            ImageButton ib = new ImageButton(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.setMargins(5, 0, 20, 5);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            ib.setLayoutParams(params);
            ib.setId(c.getID());
            ib.setBackgroundResource(R.drawable.button1);
            ib.setImageResource(R.drawable.trash);
            ib.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            View v = new View(this);
            LinearLayout.LayoutParams vl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            vl.setMargins(100, 0, 100, 0);
            v.setLayoutParams(vl);
            v.setBackgroundColor(Color.parseColor("#A9A9A9"));
            rl.addView(ib);
            ll.addView(tw);
            ll.addView(rl);
            if (i != 0) {
                lin.addView(v);
            }
            else {
                i = 1;
            }
            lin.addView(ll);
            ib.setOnClickListener(new Settings.MyListener(ib.getId()));
        }
    }
}
