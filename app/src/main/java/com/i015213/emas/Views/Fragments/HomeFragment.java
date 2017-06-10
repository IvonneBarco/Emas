package com.i015213.emas.Views.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.i015213.emas.Adapters.FavoritesAdapter;
import com.i015213.emas.Adapters.FavoritesCarAdapter;
import com.i015213.emas.Data.DataUser;
import com.i015213.emas.LoginActivity;
import com.i015213.emas.Models.Car;
import com.i015213.emas.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    SwipeRefreshLayout swipeRefreshLayout;
    ListView listaFavor;
    DataUser dataUser;
    FavoritesAdapter adapterFavorites;
    List<Car> carList;
    static List<Car> carRoutes;
    Car car;


    public HomeFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_home, container, false);
        listaFavor = (ListView) view.findViewById(R.id.id_lv_fovorites);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_fragment_home_swipe);
        dataUser = new DataUser(getActivity());
        dataUser.open();
        LoginActivity.userLogin = dataUser.checkStatusLogin();


        showTolbar(getResources().getString(R.string.txt_title_toolbar_Container_home),true);
        setHasOptionsMenu(true);


        carList = dataUser.listFavorites(LoginActivity.userLogin.getId());
        if (carList.size()<=0) Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_field_favorites), Toast.LENGTH_SHORT).show();
        else {
            adapterFavorites = new FavoritesAdapter(getActivity().getApplicationContext(), carList);
            listaFavor.setAdapter(adapterFavorites);
            registerForContextMenu(listaFavor);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cargar_datos:
                //onClickButton();
                return (true);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_favorites, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        car = adapterFavorites.getItem(info.position);
        switch (item.getItemId()){
            case R.id.id_item_menu_more_Information:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                carRoutes = dataUser.listRouesCar(car.getRoute()) ;
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
                return (true);
            case  R.id.id_item_menu_favorite:
                dataUser.deleteFavorites(LoginActivity.userLogin.getId(), car.getId());
                adapterFavorites = new FavoritesAdapter(getActivity().getApplicationContext(), carList);
                listaFavor.setAdapter(adapterFavorites);
                registerForContextMenu(listaFavor);
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_descart_favorite) + car.getRoute(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void swipeRefresh(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                carList = dataUser.listFavorites(LoginActivity.userLogin.getId());
                if (carList.size()<=0) Toast.makeText(getActivity().getApplicationContext(),getString(R.string.txt_field_favorites), Toast.LENGTH_SHORT).show();
                else {
                    adapterFavorites = new FavoritesAdapter(getActivity().getApplicationContext(), carList);
                    listaFavor.setAdapter(adapterFavorites);
                    registerForContextMenu(listaFavor);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    public static class DialogoAlerta extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final String[] items= new String[carRoutes.size()];

            for (int i=0;i<carRoutes.size();i++ )
            {
                items[i]=carRoutes.get(i).getNeighborhood();
            }

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());

            builder.setTitle(getString(R.string.txt_neighborhood_routes))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.i("Dialogos", "Opción elegida: " + carRoutes.get(item).getRoute());
                        }
                    });

            return builder.create();
        }
    }

}
