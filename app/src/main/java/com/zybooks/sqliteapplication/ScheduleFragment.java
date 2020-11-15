package com.zybooks.sqliteapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ScheduleFragment extends Fragment {

    public Object mTag;
    public int mClassId;
    private ClassDatabase clssDB;
    private OnClassSelectedListener mListener;
    private String mString;
    private Toast toast;

    public interface OnClassSelectedListener {
        void onClassSelected(int classId);
    }

    public ScheduleFragment(ClassDatabase mClassDB) {
        clssDB = mClassDB;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.class_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send classes to recycler view
        ClassAdapter adapter = new ClassAdapter(ClassDatabase.getInstance(getContext()).getClasses());
        recyclerView.setAdapter(adapter);


        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:

                Class course = new Class();
                clssDB = ClassDatabase.getInstance(getContext());
                course = clssDB.getClss(Integer.parseInt(mString));

                try {
                    clssDB.deleteClass(course);
                    toast = Toast.makeText(getContext(),"Class Deleted",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                    Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                    startActivity(intent);

                }catch (Exception e){
                    toast = Toast.makeText(getContext(),"Failed to Delete Class",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
                return true;

            case R.id.update:
                clssDB = ClassDatabase.getInstance(getContext());
                course = clssDB.getClss(Integer.parseInt(mString));
                //Intent intent = new Intent(this, UpdateCourseActivity.class);
                //intent.putExtra(UpdateCourseActivity.EXTRA_CLASS_ID, String.valueOf(course.getId()));
                //startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }



    private class ClassHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Class mClass;

        private TextView mNameTextView;

        public ClassHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.schedule_item_class, parent, false));
            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
            mNameTextView = itemView.findViewById(R.id.clsName);

        }

        public void bind(Class cls) {
            mClass = cls;
            mNameTextView.setText(mClass.getName());
        }

        @Override
        public void onClick(View view) {
            // Tell ScheduleActivity what class was clicked
            mListener.onClassSelected(mClass.getId());
        }
    }

    private class ClassAdapter extends RecyclerView.Adapter<ClassHolder> {

        private List<Class> mClasses;

        public ClassAdapter(List<Class> classes) {
            mClasses = classes;
        }

        @Override
        public ClassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ClassHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ClassHolder holder, int position) {
            Class cls = mClasses.get(position);
            holder.bind(cls);
        }

        @Override
        public int getItemCount() {
            return mClasses.size();
        }
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

//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // Notify activity of band selection
//            mTag = view.getTag();
//            String className = (String) view.getTag();
//            mListener.onClassSelected(Integer.parseInt(className));
//        }
//    };



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