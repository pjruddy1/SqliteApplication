package com.zybooks.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "class.db";
    private static ClassDatabase mClassDb;

    private static final class ClassTable {
        private static final String TABLE = "classes";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_DESC = "description";
        private static final String COL_LOC = "location";
        private static final String COL_DAYTIME = "daystimes";
        private static final String COL_INSTRUCTOR = "instructor";
    }

    public static ClassDatabase getInstance(Context context) {
        if (mClassDb == null) {
            mClassDb = new ClassDatabase(context);
        }
        return mClassDb;
    }

    private ClassDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create classes table
        db.execSQL("create table " + ClassTable.TABLE + " (" +
                ClassTable.COL_ID + " integer primary key autoincrement, " +
                ClassTable.COL_NAME + ", " +
                ClassTable.COL_DESC + ", " +
                ClassTable.COL_LOC + ", " +
                ClassTable.COL_DAYTIME + ", " +
                ClassTable.COL_INSTRUCTOR );

        ContentValues values = new ContentValues();
        values.put(ClassTable.COL_NAME, "ISYS 221");
        values.put(ClassTable.COL_DESC, "Android Mobile Dev");
        values.put(ClassTable.COL_LOC, "Online");
        values.put(ClassTable.COL_DAYTIME, "Everyday");
        values.put(ClassTable.COL_INSTRUCTOR, "Travis Bussler");
        db.insert(ClassTable.TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ClassTable.TABLE);
        onCreate(db);
    }

    public List<Class> getClasses() {
        List<Class> classes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + ClassTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Class cls = new Class();
                cls.setName(cursor.getString(1));
                cls.setDescription(cursor.getString(2));
                cls.setLocation(cursor.getString(3));
                cls.setDaysTimes(cursor.getString(4));
                cls.setInstructor(cursor.getString(5));
                classes.add(cls);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return classes;
    }

    public boolean addClass(Class cls) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassTable.COL_NAME, cls.getName());
        values.put(ClassTable.COL_DESC, cls.getDescription());
        values.put(ClassTable.COL_LOC, cls.getLocation());
        values.put(ClassTable.COL_DAYTIME, cls.getDaysTimes());
        values.put(ClassTable.COL_INSTRUCTOR, cls.getInstructor());
        long id = db.insert(ClassTable.TABLE, null, values);
        return id != -1;
    }

    public void updateClass(Class cls) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassTable.COL_NAME, cls.getName());
        values.put(ClassTable.COL_DESC, cls.getDescription());
        values.put(ClassTable.COL_LOC, cls.getLocation());
        values.put(ClassTable.COL_DAYTIME, cls.getDaysTimes());
        values.put(ClassTable.COL_INSTRUCTOR, cls.getInstructor());
        db.update(ClassTable.TABLE, values,
                ClassTable.COL_NAME + " = ?", new String[] { cls.getName() });
    }

    public void deleteClass(Class cls) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ClassTable.TABLE,
                ClassTable.COL_NAME + " = ?", new String[] { cls.getName() });
    }

    public Class getClss(int questionId) {
        Class aClass = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + ClassTable.TABLE +
                " where " + ClassTable.COL_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { Float.toString(questionId) });

        if (cursor.moveToFirst()) {
            Class cls = new Class();
            cls.setName(cursor.getString(1));
            cls.setDescription(cursor.getString(2));
            cls.setLocation(cursor.getString(3));
            cls.setDaysTimes(cursor.getString(4));
            cls.setInstructor(cursor.getString(5));
            return cls;
        }
        else return null;

    }
}
