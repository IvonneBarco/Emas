package com.i015213.emas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class CreateAccountActivity extends AppCompatActivity {

    Button btnCreateAccount;
    TextInputEditText txtEmail, txtName, txtUser, txtPass, txtCPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.str_title_toolbar),true);

        //Programando Boton
        btnCreateAccount = (Button) findViewById(R.id.id_btn_createaccount);
        txtName = (TextInputEditText) findViewById(R.id.id_txt_name);
        txtEmail = (TextInputEditText) findViewById(R.id.id_txt_email);
        txtUser = (TextInputEditText) findViewById(R.id.id_txt_user);
        txtPass = (TextInputEditText) findViewById(R.id.id_txt_pass);
        txtCPass = (TextInputEditText) findViewById(R.id.id_txt_confpass);

        btnCreateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
            //
        });

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
