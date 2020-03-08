package com.example.car_parking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CarAdapter extends ArrayAdapter<Car_Description> {
    Context mCtx;
    int listLayoutRes;
    List<Car_Description> car_descriptions;
    SQLiteDatabase mDatabase;

    public CarAdapter(Context mCtx, int listLayoutRes, List<Car_Description> car_descriptions, SQLiteDatabase mDatabase) {
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
        View view = inflater.inflate(R.layout.currentbookinglistview, null);
        final Car_Description car_description = car_descriptions.get(position);


        TextView textViewName = view.findViewById(R.id.textview12);
        TextView textViewDept = view.findViewById(R.id.carnumber);
        TextView textViewSalary = view.findViewById(R.id.slot);
        //    TextView textViewJoiningDate = view.findViewById(R.id.exitime);

        textViewName.setText(car_description.getEntrytime());
        textViewDept.setText(car_description.getCarnumber());
        textViewSalary.setText(car_description.getSlotnumber());
        //   textViewJoiningDate.setText(car_description.getExittime());
        //   Button buttonDelete = view.findViewById(R.id.buttondelete);
        //  Button buttonEdit = view.findViewById(R.id.buttonedit);

        //adding a clicklistener to button
//        buttonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateEmployee(car_description);
//            }
//        });
//
//        //the delete operation
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
//                builder.setTitle("Are you sure?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String sql = "DELETE FROM parking WHERE carnumber = ?";
//                        mDatabase.execSQL(sql, new String[]{car_description.getCarnumber()});
//
//                        reloadcarsFromDatabase();
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
//
//

        return view;
    }
//    private void updateEmployee(final Car_Description car_descriptions) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
//
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view = inflater.inflate(R.layout.update_car_layout, null);
//        builder.setView(view);
//
//
//        final EditText editTextName = view.findViewById(R.id.carnumberupdate);
//        final EditText editTextSalary = view.findViewById(R.id.carslotupdate);
//        // final Spinner spinnerDepartment = view.findViewById(R.id.spinnerDepartment);
//
//        editTextName.setText(car_descriptions.getCarnumber());
//        //  editTextSalary.setText(String.valueOf(employee.getSalary()));
//
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//
//        view.findViewById(R.id.buttonupdate).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editTextName.getText().toString().trim();
//                //    String salary = editTextSalary.getText().toString().trim();
//                //  String dept = spinnerDepartment.getSelectedItem().toString();
//
//                if (name.isEmpty()) {
//                    editTextName.setError("Name can't be blank");
//                    editTextName.requestFocus();
//                    return;
//                }
////
////                if (salary.isEmpty()) {
////                    editTextSalary.setError("Salary can't be blank");
////                    editTextSalary.requestFocus();
////                    return;
////                }
//
//                String sql = "UPDATE parking \n" +
//                        "SET entrytime = ?, \n" +
//
//                        "slot = ? \n" +
//                        "WHERE carnumber = ?;\n";
//
//                mDatabase.execSQL(sql, new String[]{name,car_descriptions.getCarnumber()});
//                Toast.makeText(mCtx, "Employee Updated", Toast.LENGTH_SHORT).show();
//                //   reloadEmployeesFromDatabase();
//
//                dialog.dismiss();
//            }
//        });
//    }

    private void reloadcarsFromDatabase() {
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM parking", null);
        if (cursorEmployees.moveToFirst()) {
            car_descriptions.clear();
            do {
                car_descriptions.add(new Car_Description(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getString(4),
                        cursorEmployees.getString(5),
                        cursorEmployees.getString(6)
                        // cursorEmployees.getDouble(4)
                ));
            } while (cursorEmployees.moveToNext());
        }
        cursorEmployees.close();
        notifyDataSetChanged();
    }
}