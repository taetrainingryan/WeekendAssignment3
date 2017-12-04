package com.example.ryan.weekendassignment3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    @BindView(R.id.navigation) BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    switchToFragment1();
                    break;

                case R.id.navigation_dashboard:
                    switchToFragment2();
                    break;

                    default: return false;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation);
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new MapsFragment())
                .commit();
    }

    public void switchToFragment1() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MapsFragment()).commit();
    }

    public void switchToFragment2() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ReservationsFragment())
                .addToBackStack("MapsFragment").commit();
    }


}
