package com.example.car_parking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AllBookingCarAdapter extends ArrayAdapter<Car_Description> {
    Context mCtx;
    int listLayoutRes;
    List<Car_Description> car_descriptions;
    SQLiteDatabase mDatabase;
    public AllBookingCarAdapter(Context mCtx, int listLayoutRes, List<Car_Description> car_descriptions, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, car_descriptions);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.car_descriptions = car_descriptions;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.allbookinglistview, null);
        final Car_Description car_description = car_descriptions.get(position);

        TextView entrytime = view.findViewById(R.id.entrytime);
        TextView endtime = view.findViewById(R.id.endtime);
        TextView carnumber = view.findViewById(R.id.carnumber);
        TextView bill = view.findViewById(R.id.bill);
        //    TextView textViewJoiningDate = view.findViewById(R.id.exitime);

        entrytime.setText(car_description.getEntrytime());
       carnumber.setText(car_description.getCarnumber());
        endtime.setText(car_description.getEndtime());
        bill.setText(car_description.getBill());

       return view;
    }
}
