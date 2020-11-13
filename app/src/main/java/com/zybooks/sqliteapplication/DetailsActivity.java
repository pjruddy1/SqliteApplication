package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static String EXTRA_CLASS_ID = "1";
    private Class mClass;
    private ClassDatabase mClassDB;
    private int mClassID;
    private String string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mClass = new Class();
        mClassDB = ClassDatabase.getInstance(getApplicationContext());
        mClassID = getIntent().getIntExtra(EXTRA_CLASS_ID, 1);

        mClass = mClassDB.getClss(mClassID);

        TextView nameTextView = findViewById(R.id.className);
        nameTextView.setText(mClass.getName());

        TextView descriptionTextView = findViewById(R.id.classDescription);
        descriptionTextView.setText(mClass.getDescription());

        TextView locationTextView = findViewById(R.id.classLocation);
        descriptionTextView.setText(mClass.getLocation());

        TextView dayTimeTextView = findViewById(R.id.classDaysTimes);
        descriptionTextView.setText(mClass.getDaysTimes());

        TextView instructorTextView = findViewById(R.id.classInstructor);
        descriptionTextView.setText(mClass.getInstructor());
    }
}