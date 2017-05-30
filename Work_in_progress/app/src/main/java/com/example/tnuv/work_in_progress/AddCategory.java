package com.example.tnuv.work_in_progress;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddCategory extends AppCompatActivity {

    private DBHelper db;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        // initialize database helper
        db = new DBHelper(getApplicationContext());

        ColorPickerView colorPickerView = (ColorPickerView) findViewById(R.id.color_picker_view);
        colorPickerView.addOnColorChangedListener(new OnColorChangedListener() {
            @Override public void onColorChanged(int selectedColor) {
                // Handle on color change
                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toString(selectedColor));
            }
        });
        colorPickerView.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
                String barva = Integer.toHexString(selectedColor);
                //int hel = Color.parseColor(barva);
                color = "#" +barva.substring(2);
                Toast.makeText(
                        AddCategory.this,
                        "selectedColor: " + color,
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveCat);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.editText);
                EditText et2 = (EditText) findViewById(R.id.editText2);
                String name= et.getText().toString();
                String location = et2.getText().toString();
                // create new category
                Category c = new Category(name, color, location);
                long cat_id = db.createCategory(c);
                Intent intent = new Intent(AddCategory.this, CategoryTasks.class);
                startActivity(intent);
            }
        });

    }

}
