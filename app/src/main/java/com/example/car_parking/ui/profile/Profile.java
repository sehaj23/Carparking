package com.example.car_parking.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.car_parking.R;
import com.example.car_parking.SqliteHelper;

import org.w3c.dom.Text;

public class Profile extends Fragment {
    SharedPreferences sharedPreferences;
    SqliteHelper sqliteHelper;
    Button update;
    EditText name,emaill,passwordd;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //  final TextView textView = root.findViewById(R.id.text_home);
//        profileViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        update = root.findViewById(R.id.update);

        sharedPreferences = getActivity().getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);

        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password",null);
        final String id = sharedPreferences.getString("id",null);
        sqliteHelper = new SqliteHelper(getActivity());
        String username = sqliteHelper.username(email);
        name = (EditText)(root).findViewById(R.id.name);
        emaill = (EditText)(root).findViewById(R.id.email);
        passwordd = (EditText)(root).findViewById(R.id.password);
        name.setText(username);
        emaill.setText(email);
        passwordd.setText(password);



       // String username = sql.username(email);
        final TextView edit = root.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
                name.setEnabled(true);
                passwordd.setEnabled(true);


            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editname = name.getText().toString();
                String editemail = emaill.getText().toString();
                String editpass = passwordd.getText().toString();
                sqliteHelper.update(id,editname,editpass);
                Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_LONG).show();

                update.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                name.setEnabled(false);
                passwordd.setEnabled(false);

            }
        });

        return root;
//    }
    }
}