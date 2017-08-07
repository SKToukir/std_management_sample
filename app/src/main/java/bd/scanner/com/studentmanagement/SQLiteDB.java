package bd.scanner.com.studentmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by faiza on 8/4/17.
 */

public class SQLiteDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_NAME = "student_name";
    public static final String COLUMN_DEPARTMENT = "student_dpt";
    public static final String COLUMN_RESULT = "student_result";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "student.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_STUDENT_ID + TEXT_TYPE + COMMA_SEP +
                    COLUMN_DEPARTMENT + TEXT_TYPE + COMMA_SEP +
                    COLUMN_RESULT + TEXT_TYPE +  " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void create(Student student){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getStdName());
        values.put(COLUMN_STUDENT_ID, student.getStdId());
        values.put(COLUMN_DEPARTMENT, student.getStdDpt());
        values.put(COLUMN_RESULT, student.getStdResult());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                TABLE_NAME,
                null,
                values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_STUDENT_ID,COLUMN_DEPARTMENT,COLUMN_RESULT};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                COLUMN_NAME + " ASC";

        Cursor c = db.query(
                TABLE_NAME,                    // The table to query
                projection,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }

    public void update(Student student){
        SQLiteDatabase db = getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getStdName());
        values.put(COLUMN_STUDENT_ID, student.getStdId());
        values.put(COLUMN_DEPARTMENT, student.getStdDpt());
        values.put(COLUMN_RESULT, student.getStdResult());

        // Which row to update, based on the ID
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(student.getId()) };

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
        Log.d("IDD-count",String.valueOf(count));
    }

    public void delete(int id){
        Log.d("IDD",String.valueOf(id));
        SQLiteDatabase db = getReadableDatabase();

        // Define 'where' part of query.
        String selection = COLUMN_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        // Issue SQL statement.
        db.delete(TABLE_NAME, selection, selectionArgs);
    }
}