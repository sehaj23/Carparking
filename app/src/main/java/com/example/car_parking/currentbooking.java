package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class currentbooking extends AppCompatActivity {
    List<Car_Description> car_descriptions;
    SQLiteDatabase mDatabase;
    ListView listViewCar;
    CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentbooking);
        listViewCar = (ListView) findViewById(R.id.currentlist);
        car_descriptions = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(SqliteHelper.DATABASE_NAME, MODE_PRIVATE, null);

        //this method will display the employees in the list
        showCarsFromDatabase();
    }
    private void showCarsFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorcars = mDatabase.rawQuery("SELECT * FROM currentbookings", null);

        //if the cursor has some data
        if (cursorcars.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the current booking list
                car_descriptions.add(new Car_Description(
                        cursorcars.getInt(0),
                        cursorcars.getString(1),
                        cursorcars.getString(2),
                        cursorcars.getString(3),
                        cursorcars.getString(4),
                        cursorcars.getString(5),
                        cursorcars.getString(6)

                        //   cursorEmployees.getString(4)

                ));
            } while (cursorcars.moveToNext());
        }
        //closing the cursor
        cursorcars.close();

        //creating the adapter object
        adapter = new CarAdapter(this, R.layout.currentbookinglistview, car_descriptions, mDatabase);

        //adding the adapter to listview
        listViewCar.setAdapter(adapter);
    }
}
