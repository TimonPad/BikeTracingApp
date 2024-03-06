package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.myapplication.sessions.MyDatabase;
import com.example.myapplication.sessions.SessionsUserDAO;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup database to be accessed from fragments
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "mydb").allowMainThreadQueries().build();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerToggle();

        // Set a listener to handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_connection:
                        loadFragment(new ConnectionFragment());
                        break;
                    case R.id.nav_current_session:
                        loadFragment(new CurrentSessionFragment());
                        break;
                    case R.id.nav_total_distance:
                        loadFragment(new TotalDistanceFragment());
                        break;
                    case R.id.nav_session_user:
                        loadFragment(new SessionUserFragment());
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        // Load the "Connection" fragment initially
        if (savedInstanceState == null) {
            loadFragment(new ConnectionFragment());
            navigationView.setCheckedItem(R.id.nav_connection);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // Replace the current fragment
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setupDrawerToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
