package com.example.car_parking.ui.home;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.car_parking.HomeActivity;
import com.example.car_parking.Main2Activity;
import com.example.car_parking.Parking;
import com.example.car_parking.R;
import com.example.car_parking.SqliteHelper;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
   ImageView maps;
   Button slot;
   TextView time,place;
    CountDownTimer countDownTimer;
  int  totalTimeCountInMilliseconds = 60 * 1000;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        time = (TextView)root.findViewById(R.id.textview12);
        place = (TextView)root.findViewById(R.id.textView11);
        maps = (ImageView)root.findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                String lat = "18.533299";
                        String lng = "73.829778";
                        String title = "Pavilion Mall Pune";
                intent.setData(Uri.parse("http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + title + ")"));
                Intent.createChooser(intent,"Launch Maps");
                startActivity(intent);


            }
        });

        slot = (Button)root.findViewById(R.id.slot);
        slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });






        return root;
    }
}