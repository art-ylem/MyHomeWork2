package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.myhomework2.R;
import com.example.myhomework2.view.EventInformation.FragmentInformation;
import com.example.myhomework2.view.Events.FragmentEvents;
import com.example.myhomework2.view.FilmInformation.FragmentFilmInformation;
import com.example.myhomework2.view.Movie.FragmentMovie;
import com.example.myhomework2.view.News.FragmentNews;
import com.example.myhomework2.view.map.FragmentMap;

public class MainActivity extends BaseActivity implements FragmentInformation.OnFragmentInteractionListener, FragmentFilmInformation.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nv);
        drawerLayout = findViewById(R.id.activity_main);

        selectItem();
        setupDrawerIcon();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentNews.newInstance()).commit();
    }


    private void selectItem(){
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            switch(item.getItemId())
            {
                case R.id.actions:
                    launchFrag(FragmentEvents.newInstance());
                    break;
                case R.id.news:
                    launchFrag(FragmentNews.newInstance());
                break;
                case R.id.movie:
                    launchFrag(FragmentMovie.newInstance());
                    break;
                case R.id.map:
                    launchFrag(FragmentMap.newInstance());
                    break;
                case R.id.info:
                    launchFrag(InfoFragment.newInstance());
                    break;
                default:
                    return true;
            }


            return false;

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //если drawerLayout открыт, то принажатии на стрелочку закрыть
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawers();
                }
                else {//если drawerLayout закрыт, то принажатии на 3 белые полоски - открыть
                    drawerLayout.openDrawer(Gravity.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchFrag(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

    }
    public void launchFragWitchBackStack(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void setupDrawerIcon() {
        ActionBarDrawerToggle mDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout,0, 0);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onAccountFragmentInteraction() {

    }
}
