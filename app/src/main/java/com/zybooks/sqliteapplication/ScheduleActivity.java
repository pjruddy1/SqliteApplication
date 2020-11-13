package com.zybooks.sqliteapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleFragment.OnClassSelectedListener {

    private ClassDatabase mClassDB;

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
    public void onClassSelected(int classId) {
        int mClassId = classId;

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_CLASS_ID, classId);
        startActivity(intent);
    }
}