package com.exmple.www.finalmycollage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSqliteApp extends SQLiteOpenHelper {
    public static final String DATETASK = "datetask";
    public static final String DAYTASK = "daytask";
    public static final String HALLTASK = "halltask";
    public static final String NAMETABLE = "DatesDaly";
    public static final String NAMETASK = "nametask";
    public static final String NAMETHALECTURER = "namethelecturer";
    public static final String TIMETASK = "timetask";
    public static final String _IDTASK = "_id";

    public DBSqliteApp(Context context) {
        super(context, "DBApp", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DatesDaly (_id INTEGER PRIMARY KEY AUTOINCREMENT, nametask TEXT, timetask TEXT, daytask INTEGER, namethelecturer TEXT, datetask INTEGER ,halltask TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTSDatesDaly");
        onCreate(db);
    }

    public boolean insertNewTask(String name_task, String name_lecturer, String name_aplace, String time_task, int day_task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMETASK, name_task);
        contentValues.put(HALLTASK, name_aplace);
        contentValues.put(NAMETHALECTURER, name_lecturer);
        contentValues.put(TIMETASK, time_task);
        contentValues.put(DAYTASK, Integer.valueOf(day_task));
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long isInsert = sqLiteDatabase.insert(NAMETABLE, null, contentValues);
        sqLiteDatabase.close();
        return isInsert != -1;
    }

    public boolean deleteitem(String _id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int isDelete = sqLiteDatabase.delete(NAMETABLE, "_id = ?", new String[]{_id});
        sqLiteDatabase.close();
        if (isDelete > 0) {
            return true;
        }
        return false;
    }

    public Cursor getdalytaskdb(int day) {
        return getReadableDatabase().query(NAMETABLE, new String[]{_IDTASK, NAMETASK, HALLTASK, NAMETHALECTURER, TIMETASK, DAYTASK}, "daytask=?", new String[]{String.valueOf(day)}, null, null, null);
    }
}
