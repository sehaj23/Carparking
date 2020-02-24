package com.example.car_parking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.car_parking.R;
import com.example.car_parking.SqliteHelper;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> ENTRYTIME = new ArrayList<String>();
    private ArrayList<String> CARNUMBER = new ArrayList<String>();
 //   private ArrayList<String> Age = new ArrayList<String>();
    public CustomAdapter(Context  context,ArrayList<String> Id,ArrayList<String> Name, ArrayList<String> MailId
    )
    {
        this.mContext = context;
        this.id = id;
        this.CARNUMBER = CARNUMBER;
        this.ENTRYTIME = ENTRYTIME;
       // this.Age=Age;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final    viewHolder holder;
        sqliteHelper =new SqliteHelper(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, null);
            holder = new viewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.tvid);
            holder.ENTRYTIME = (TextView) convertView.findViewById(R.id.tvname);
            holder.CARNUMBER = (TextView) convertView.findViewById(R.id.tvmailid);
         //   holder.age = (TextView) convertView.findViewById(R.id.tvage);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.id.setText(id.get(position));
        holder.ENTRYTIME.setText(ENTRYTIME.get(position));
        holder.CARNUMBER.setText(CARNUMBER.get(position));
   //     holder.age.setText(Age.get(position));
        return convertView;

    }
    public class viewHolder {
        TextView id;
        TextView ENTRYTIME;
        TextView CARNUMBER;
        //TextView age;
    }
}
