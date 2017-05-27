package com.i015213.emas.Views;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.i015213.emas.R;
import com.i015213.emas.Views.Fragments.HomeFragment;
import com.i015213.emas.Views.Fragments.ProfileFragment;
import com.i015213.emas.Views.Fragments.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.id_bottombar);
        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_search:
                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.id_container_fragment, searchFragment) //Reemplaza la vista
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) //Hace la transición
                                .addToBackStack(null) //Cuando salga de esa vista me deje en null para que sea asignada por esa vista
                                .commit();
                        break;
                    case R.id.tab_home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.id_container_fragment, homeFragment) //Reemplaza la vista
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) //Hace la transición
                                .addToBackStack(null) //Cuando salga de esa vista me deje en null para que sea asignada por esa vista
                                .commit();
                        break;
                    case R.id.tab_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.id_container_fragment, profileFragment) //Reemplaza la vista
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) //Hace la transición
                                .addToBackStack(null) //Cuando salga de esa vista me deje en null para que sea asignada por esa vista
                                .commit();
                        break;
                }
            }
        });
    }
}
