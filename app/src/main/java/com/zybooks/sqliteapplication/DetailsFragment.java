package com.zybooks.sqliteapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private Class mClass;

    public static DetailsFragment newInstance(int classId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("classId", classId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the band ID from the intent that started DetailsActivity
        int classId = 1;
        if (getArguments() != null) {
            classId = getArguments().getInt("classId");
        }

        mClass = ClassDatabase.getInstance(getContext()).getClss(classId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.className);
        nameTextView.setText(mClass.getName());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.classDescription);
        descriptionTextView.setText(mClass.getDescription());

        TextView locationTextView = (TextView) view.findViewById(R.id.classLocation);
        descriptionTextView.setText(mClass.getLocation());

        TextView dayTimeTextView = (TextView) view.findViewById(R.id.classDaysTimes);
        descriptionTextView.setText(mClass.getDaysTimes());

        TextView instructorTextView = (TextView) view.findViewById(R.id.classInstructor);
        descriptionTextView.setText(mClass.getInstructor());

        return view;
    }
}