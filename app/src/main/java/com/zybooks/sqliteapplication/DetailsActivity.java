package com.zybooks.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    public static String EXTRA_CLASS_ID = "1";
    private Class mClass;
    private ClassDatabase mClassDB;
    private int mClassID;
    private String string;
    private Toast toast;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView dayTimeTextView;
    private TextView instructorTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mClass = new Class();
        mClassDB = ClassDatabase.getInstance(getApplicationContext());
        mClassID = getIntent().getIntExtra(EXTRA_CLASS_ID, 1);

        mClass = mClassDB.getClss(mClassID);

        nameTextView = findViewById(R.id.className);
        nameTextView.setText(mClass.getName());

        descriptionTextView = findViewById(R.id.classDescription);
        descriptionTextView.setText(mClass.getDescription());

        locationTextView = findViewById(R.id.classLocation);
        descriptionTextView.setText(mClass.getLocation());

        dayTimeTextView = findViewById(R.id.classDaysTimes);
        descriptionTextView.setText(mClass.getDaysTimes());

        instructorTextView = findViewById(R.id.classInstructor);
        descriptionTextView.setText(mClass.getInstructor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu for the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine which app bar item was chosen
        switch (item.getItemId()) {

            case R.id.delete:
                Class course = new Class();
                mClassDB = ClassDatabase.getInstance(getApplicationContext());
                course = mClassDB.getClss(mClassID);

                try {
                    mClassDB.deleteClass(course);
                    toast = Toast.makeText(getApplicationContext(),"Class Deleted",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                    Intent intent = new Intent(this, ScheduleActivity.class);
                    startActivity(intent);

                }catch (Exception e){
                    toast = Toast.makeText(getApplicationContext(),"Failed to Delete Class",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
                return true;

            case R.id.update:
                Intent intent = new Intent(this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_CLASS_ID, mClassID);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}