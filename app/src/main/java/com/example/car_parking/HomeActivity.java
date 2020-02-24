package com.example.car_parking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.car_parking.ui.home.HomeFragment;
import com.example.car_parking.ui.profile.Profile;
import com.example.car_parking.ui.share.ShareFragment;
import com.example.car_parking.ui.slideshow.SlideshowFragment;
import com.example.car_parking.ui.tools.ToolsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    SqliteHelper sql;
    SharedPreferences sharedPreferences;
    TextView subtitle, title,currentlocation;
    LocationManager locationManager;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        View header = navigationView.getHeaderView(0);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        sql = new SqliteHelper(this);
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        navigationView.setNavigationItemSelectedListener(this);

        String username = sql.username(email);
      //  Toast.makeText(HomeActivity.this, username, Toast.LENGTH_LONG).show();
        subtitle = (TextView) header.findViewById(R.id.textView);
        subtitle.setText(email);
        title = (TextView) header.findViewById(R.id.headername);
        title.setText(username);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
            fragment = new Profile();


        } else if (id == R.id.bookings) {
            fragment = new ToolsFragment();

        } else if (id == R.id.settings) {
            fragment = new ShareFragment();

        } else if(id == R.id.home){
            fragment = new HomeFragment();


        }
        else if(id == R.id.nav_share){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            SharedPreferences preferences =getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            finish();
            Toast.makeText(this,"Successfully Logged Out",Toast.LENGTH_LONG).show();
            startActivity(intent);

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
