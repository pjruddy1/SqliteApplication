package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    static String EXTRA_CLASS_ID;
    private EditText mNameEdit;
    private EditText mDescEdit;
    private EditText mLocEdit;
    private EditText mDayTimeEdit;
    private EditText mInstructorEdit;
    private ClassDatabase mClassDB;
    private Class mClass;
    private int mClassID;
    private String string;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mNameEdit = findViewById(R.id.update_name);
        mDescEdit = findViewById(R.id.update_description);
        mLocEdit = findViewById(R.id.update_location);
        mDayTimeEdit = findViewById(R.id.update_daytime);
        mInstructorEdit = findViewById(R.id.update_instructor);

        mClass = new Class();
        mClassDB = ClassDatabase.getInstance(getApplicationContext());
        mClassID = getIntent().getIntExtra(EXTRA_CLASS_ID, 1);

        mClass = mClassDB.getClss(mClassID);

        mNameEdit.setText(mClass.getName());
        mDescEdit.setText(mClass.getDescription());
        mLocEdit.setText(mClass.getLocation());
        mDayTimeEdit.setText(mClass.getDaysTimes());
        mInstructorEdit.setText(mClass.getInstructor());
    }

    public void UpdateClick(View view) {
        String updateName;
        mClass = new Class();
        mClassDB = ClassDatabase.getInstance(getApplicationContext());
        mClassID = getIntent().getIntExtra(EXTRA_CLASS_ID, 1);

        mClass = mClassDB.getClss(mClassID);
        updateName = mClass.getName();

        mNameEdit = findViewById(R.id.update_name);
        mDescEdit = findViewById(R.id.update_description);
        mLocEdit = findViewById(R.id.update_location);
        mDayTimeEdit = findViewById(R.id.update_daytime);
        mInstructorEdit = findViewById(R.id.update_instructor);
        Class cls = new Class(mNameEdit.getText().toString(),mDescEdit.getText().toString(),mLocEdit.getText().toString(),mDayTimeEdit.getText().toString(),mInstructorEdit.getText().toString());
        try {
            mClassDB.getInstance(getApplicationContext()).updateClass(cls, updateName);
            toast= Toast.makeText(getApplicationContext(),"Class Updated",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
        }catch (Exception e) {
            mNameEdit.setText("");
            mDescEdit.setText("");
            mLocEdit.setText("");
            mDayTimeEdit.setText("");
            mInstructorEdit.setText("");

            toast = Toast.makeText(getApplicationContext(), "Failed to update Class", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();
        }
    }
}