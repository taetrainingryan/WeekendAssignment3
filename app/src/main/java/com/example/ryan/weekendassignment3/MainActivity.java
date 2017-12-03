package com.example.ryan.weekendassignment3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    switchToFragment1();
                    break;

                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    switchToFragment2();
                    break;

                    default: return false;
            }

            //updateNavigationBarState(item.getItemId());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ReservationsFragment()).commit();
    }

//    private void updateNavigationBarState(int actionId){
//        Menu menu = navigation.getMenu();
//
//        for (int i = 0, size = menu.size(); i < size; i++) {
//            MenuItem item = menu.getItem(i);
//            item.setChecked(item.getItemId() == actionId);
//        }
//    }

}
