package com.i015213.emas.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.i015213.emas.Data.DataUser;
import com.i015213.emas.LoginActivity;
import com.i015213.emas.Models.User;
import com.i015213.emas.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    List<User> userList;
    DataUser dataUser;
    TextView name,email,username;
    User user;
    Button signOut;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        showTolbar(getResources().getString(R.string.txt_title_toolbar_profile),true);
        setHasOptionsMenu(true);

        name = (TextView) view.findViewById(R.id.id_name);
        username = (TextView) view.findViewById(R.id.id_username);
        signOut = (Button) view.findViewById(R.id.id_btn_fragment_profile_SignOut);
        email = (TextView) view.findViewById(R.id.id_useremail);

        dataUser = new DataUser(getActivity());
        dataUser.open();
        user = dataUser.checkStatusLogin();

        name.setText(user.getName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_sign_off), Toast.LENGTH_SHORT).show();
                dataUser.statusOff(user.getUsername(),user.getPassword());
                goLogginActivity();
            }
        });

        return view ;
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void goLogginActivity(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
