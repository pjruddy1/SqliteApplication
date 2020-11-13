package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewClassActivity extends AppCompatActivity {

    private EditText mNameEdit;
    private EditText mDescEdit;
    private EditText mLocEdit;
    private EditText mDayTimeEdit;
    private EditText mInstructorEdit;
    private ClassDatabase mClassDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);


    }


    public void InsertClick(View view) {
        mNameEdit.findViewById(R.id.class_name);
        mDescEdit.findViewById(R.id.class_description);
        mLocEdit.findViewById(R.id.class_location);
        mDayTimeEdit.findViewById(R.id.class_daytime);
        mInstructorEdit.findViewById(R.id.class_instructor);
        Class cls = new Class(mNameEdit.getText().toString(),mDescEdit.getText().toString(),mLocEdit.getText().toString(),mDayTimeEdit.getText().toString(),mInstructorEdit.getText().toString());
        mClassDB.getInstance(getApplicationContext()).addClass(cls);
    }
}