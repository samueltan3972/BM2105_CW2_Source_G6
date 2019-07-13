package example.bm2105_cw2_source_g6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import example.bm2105_cw2_source_g6.database.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inti_cafe_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Database
    @Override
    public void onCreate(SQLiteDatabase db){
        // create user table
        db.execSQL(User.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(String username, String contact_num, String password) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(User.COLUMN_NAME, username);
        values.put(User.COLUMN_CONTACT_NUM, contact_num);
        values.put(User.COLUMN_PASSWORD, password);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public User getUser(String username) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_CONTACT_NUM, User.COLUMN_PASSWORD},
                User.COLUMN_NAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_CONTACT_NUM)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));

        // close the db connection
        cursor.close();

        return user;
    }

    
}
