package com.zybooks.sqliteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleFragment.OnClassSelectedListener {

    private ClassDatabase mClassDB;
    private static final String KEY_CLASS_ID = "classId";
    private int mClassId;
    private String mString;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mClassDB.getInstance(getApplicationContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.schedule_fragment_container);

        if (fragment == null) {
            fragment = new ScheduleFragment(mClassDB);
            fragmentManager.beginTransaction()
                    .add(R.id.schedule_fragment_container, fragment)
                    .commit();
        }

        if (savedInstanceState != null && savedInstanceState.getInt(KEY_CLASS_ID) != 0) {
            mClassId = savedInstanceState.getInt(KEY_CLASS_ID);
            fragment = DetailsFragment.newInstance(mClassId);
            getSupportFragmentManager().beginTransaction().commit();
        }
        //registerForContextMenu(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save state when something is selected
        if (mClassId != -1) {
            savedInstanceState.putInt(KEY_CLASS_ID, mClassId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu for the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine which app bar item was chosen
        switch (item.getItemId()) {
            
            case R.id.add:
                addClass();
                return true;
           
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addClass() {
        Intent intent = new Intent(this, NewClassActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClassSelected(int classID) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_CLASS_ID, classID);
        startActivity(intent);
    }


}