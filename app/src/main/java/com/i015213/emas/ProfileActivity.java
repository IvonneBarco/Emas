package com.i015213.emas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        onConfigToolbar(getResources().getString(R.string.str_title_toolbar_profile), true);
    }

    public void onConfigToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_menu_change_name:
                Toast.makeText(this, "Cambiando nombre", Toast.LENGTH_LONG ).show();
                TextView str_txt_name = (TextView) findViewById(R.id.id_name);
                str_txt_name.setText(getString(R.string.name));
                return true;
            case R.id.itm_menu_update:
                Toast.makeText(this, "Actualizando", Toast.LENGTH_LONG ).show();
                return true;
            case R.id.itm_menu_return:
                Toast.makeText(this, "Regresar", Toast.LENGTH_LONG ).show();
                setContentView(R.layout.activity_container);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
