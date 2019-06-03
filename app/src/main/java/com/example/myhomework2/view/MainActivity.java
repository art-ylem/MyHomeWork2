package com.example.myhomework2.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.myhomework2.R;
import com.example.myhomework2.view.EventInformation.FragmentInformation;
import com.example.myhomework2.view.Events.FragmentEvents;
import com.example.myhomework2.view.FilmInformation.FragmentFilmInformation;
import com.example.myhomework2.view.Movie.FragmentMovie;
import com.example.myhomework2.view.News.FragmentNews;

public class MainActivity extends AppCompatActivity implements FragmentInformation.OnFragmentInteractionListener, FragmentFilmInformation.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nv);
        drawerLayout = findViewById(R.id.activity_main);
        selectItem();


    }


    private void selectItem(){
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            switch(item.getItemId())
            {
                case R.id.actions:
                    frag(FragmentEvents.newInstance());
                    break;
                case R.id.news:
                    frag(FragmentNews.newInstance());
                break;
                case R.id.movie:
                    frag(FragmentMovie.newInstance());
                    break;
                default:
                    return true;
            }


            return false;

        });
    }

    public void frag(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void onAccountFragmentInteraction() {

    }
}
