package com.example.car_parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "registerlogin";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";



    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";




    //COLUMN user name
    public static final String KEY_USER_NAME = "username";



    //COLUMN email
    public static final String KEY_EMAIL = "email";
    // Current table columns

    public static final String TABLE_CURRENT_BOOKINGS = "currentbookings";
    public static final String CURRENT_KEY_ID = "id";
    public static final String CURRENT_KEY_EMAIL = "email";
    public static final String CURRENT_TYPE = "type";
    public static final String CURRENT_ENTRY_TIME = "entrytime";
    public static final String CURRENT_DATE = "date";
    public static final String CURRENT_CAR_NUMBER = "carnumber";

    public static final String CURRENT_SLOT = "slot";
    public static final String CURRENT_VALIDITY = "validity";

    public static final String ALL_BOOKINGS = "allbookings";
    public static final String END_TIME = "endtime";
    public static final String BILL = "bill";




    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";


   // creating current booking table
    public static final String CURRENT_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_CURRENT_BOOKINGS
            + " ( "
            + CURRENT_KEY_ID + " INTEGER PRIMARY KEY, "
            + CURRENT_KEY_EMAIL + " TEXT, "
            + CURRENT_ENTRY_TIME + " TEXT, "
            + CURRENT_DATE + " TEXT, "
            + CURRENT_CAR_NUMBER + " TEXT, "
            + CURRENT_TYPE + " TEXT, "
            + CURRENT_VALIDITY + " TEXT, "
            + CURRENT_SLOT + " TEXT"
            + " ) ";
    public static final String ALL_BOOKINGS_TABLE = "CREATE TABLE " + ALL_BOOKINGS
            + " ( "
            + CURRENT_KEY_ID + " INTEGER PRIMARY KEY, "
            + CURRENT_KEY_EMAIL + " TEXT, "
            + CURRENT_ENTRY_TIME + " TEXT, "
            + END_TIME + " TEXT, "
            + CURRENT_DATE + " TEXT, "
            + CURRENT_CAR_NUMBER + " TEXT, "
            + CURRENT_TYPE + " TEXT, "
            + CURRENT_VALIDITY + " TEXT, "
            + CURRENT_SLOT + " TEXT, "
            + BILL + " TEXT"
            + " ) ";
//
//    private static final String CURRENT_BOOKINGS_TABLE = "CREATE TABLE "
//            + TABLE_CURRENT_BOOKINGS + "(" + CURRENT_KEY_ID + " INTEGER PRIMARY KEY,"
//            + CURRENT_KEY_EMAIL + " TEXT," + CURRENT_ENTRY_TIME + " TEXT,"
//            + CURRENT_DATE + " DATETIME" + ")";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CURRENT_BOOKINGS_TABLE);
        Log.d(TAG, "onCreate: CURRENT BOOKING TABLE");

        db.execSQL(SQL_TABLE_USERS);
        Log.d(TAG, "onCreate: TABLE USER CREATED");

        db.execSQL(ALL_BOOKINGS_TABLE);
        Log.d(TAG, "onCreate: ALL BOOKINGS TABLE CREATED");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void adduser(User user){

        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);
        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }
    public String username(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,new String[]{KEY_USER_NAME},KEY_EMAIL + "=?",new String[]{email},null,null,null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            String name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
            return name;
        }

        return email;
    }
    public String userid(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,new String[]{KEY_ID},KEY_EMAIL + "=?",new String[]{email},null,null,null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            String id = cursor.getString(cursor.getColumnIndex(KEY_ID));
            return id;
        }

        return email;
    }

    public void addcurrentbooking(String email,String entrytime,String date,String carnumber,String type,String slot,String validity){
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();
        // put email in values to insert
        values.put(CURRENT_KEY_EMAIL, email);
        //Put time in  @values
        values.put(CURRENT_ENTRY_TIME, entrytime);

        //Put date in  @values
        values.put(CURRENT_DATE, date);
        values.put(CURRENT_CAR_NUMBER, carnumber);
        values.put(CURRENT_SLOT, slot);
        values.put(CURRENT_VALIDITY, validity);
        db.execSQL("DELETE FROM "+ TABLE_CURRENT_BOOKINGS);

        long current_todo_id = db.insert(TABLE_CURRENT_BOOKINGS, null, values);



    }
    public void allbooking(String email,String entrytime,String endtime,String date,String carnumber,String type,String slot,String validity,String bill){



        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(ALL_BOOKINGS_TABLE);
        Log.d(TAG, "onCreate: ALL BOOKINGS TABLE CREATED");

        //create content values to insert
        ContentValues values = new ContentValues();
        // put email in values to insert
        values.put(CURRENT_KEY_EMAIL, email);
        //Put time in  @values
        values.put(CURRENT_ENTRY_TIME, entrytime);
        values.put(END_TIME, endtime);

        //Put date in  @values
        values.put(CURRENT_DATE, date);
        values.put(CURRENT_CAR_NUMBER, carnumber);
        values.put(CURRENT_SLOT, slot);
        values.put(CURRENT_VALIDITY, validity);
        values.put(BILL,bill);
     //   db.execSQL("delete from "+ TABLE_CURRENT_BOOKINGS);
        long all_todo_id = db.insert(ALL_BOOKINGS, null, values);



    }
    public void timeover(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+ TABLE_CURRENT_BOOKINGS);
    }
    public  void update(String id,String name,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username",name);
        cv.put("password",password);
        db.update(TABLE_USERS,cv,"id= "+id,null);
      //  db.execSQL("UPDATE users SET username= "+name+ " and password= "+password+" WHERE email="+email);

    }

}
