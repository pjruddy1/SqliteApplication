package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewClassActivity extends AppCompatActivity {

    private EditText mNameEdit;
    private EditText mDescEdit;
    private EditText mLocEdit;
    private EditText mDayTimeEdit;
    private EditText mInstructorEdit;
    private Button mInsertButton;
    private ClassDatabase mClassDB;
    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);


    }


    public void InsertClick(View view) {
        mNameEdit = findViewById(R.id.class_name);
        mDescEdit = findViewById(R.id.class_description);
        mLocEdit = findViewById(R.id.class_location);
        mDayTimeEdit = findViewById(R.id.class_daytime);
        mInstructorEdit = findViewById(R.id.class_instructor);
        Class cls = new Class(mNameEdit.getText().toString(),mDescEdit.getText().toString(),mLocEdit.getText().toString(),mDayTimeEdit.getText().toString(),mInstructorEdit.getText().toString());
        try {
            mClassDB.getInstance(getApplicationContext()).addClass(cls);
             toast=Toast.makeText(getApplicationContext(),"Class Added",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
        }catch (Exception e){
            mNameEdit.setText("");
            mDescEdit.setText("");
            mLocEdit.setText("");
            mDayTimeEdit.setText("");
            mInstructorEdit.setText("");

            toast = Toast.makeText(getApplicationContext(),"Failed to Add Class",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }

    }
}