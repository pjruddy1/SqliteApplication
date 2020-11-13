package com.zybooks.sqliteapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;


public class ScheduleFragment extends Fragment {

    public Object mTag;
    public int mClassId;
    private ClassDatabase clssDB;
    private OnClassSelectedListener mListener;

    public interface OnClassSelectedListener {
        void onClassSelected(int classId);
    }

    public ScheduleFragment(ClassDatabase mClassDB) {
        clssDB = mClassDB;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        LinearLayout layout = (LinearLayout) view;

        // Create the buttons using the band names and ids from BandDatabase

        List<Class> classList = ClassDatabase.getInstance(getContext()).getClasses();
        for (Class cls : classList) {
            Button button = new Button(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 10);   // 10 px
            button.setLayoutParams(layoutParams);

            button.setText(cls.getName());
            button.setTag(Integer.toString(cls.getId()));

            mTag = button.getTag();
            // All the buttons have the same click listener
            button.setOnClickListener(buttonClickListener);

            // Add the button to the LinearLayout
            layout.addView(button);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClassSelectedListener) {
            mListener = (OnClassSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBandSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Notify activity of band selection
            String classId = (String) view.getTag();
            mListener.onClassSelected(Integer.parseInt(classId));
        }
    };



//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // Start DetailsActivity
//
//            Intent intent = new Intent(getActivity(), DetailsActivity.class);
//            String classId = (String) view.getTag();
//            intent.putExtra(EXTRA_CLASS_ID, classId);
//            startActivity(intent);
//        }
//    };


}