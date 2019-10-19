package com.adrian.libreria;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class Base_activity extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.menu_1: {
                Intent i = new Intent(this, Prestar_o_donar.class);
                startActivityForResult(i, 0);
                break;
            }
            case R.id.menu_2: {
                Intent i = new Intent(this, En_espera.class);
                startActivityForResult(i, 0);
                break;
            }
            case R.id.menu_3: {
                Intent i = new Intent(this, Intercambiar.class);
                startActivityForResult(i, 0);
                break;
            }
            case R.id.menu_4: {
                Intent i = new Intent(this, Punto_de_acopio.class);
                startActivityForResult(i, 0);
                break;
            }
            case R.id.menu_5: {
                finishAffinity();
                System.exit(0);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
