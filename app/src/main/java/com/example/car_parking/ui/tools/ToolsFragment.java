package com.example.car_parking.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.car_parking.Allbookings;
import com.example.car_parking.R;
import com.example.car_parking.currentbooking;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    Button b1,b2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
      b1 = (Button)(root).findViewById(R.id.current);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), currentbooking.class);
               startActivity(intent);
           }
       });
        b2 = (Button)(root).findViewById(R.id.previous);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Allbookings.class);
                startActivity(intent);
            }
        });
        return root;

    }
}