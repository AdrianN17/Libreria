package com.adrian.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView txt_user,txt_pass;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_user = findViewById(R.id.txt_user);
        txt_pass = findViewById(R.id.txt_pass);
    }

    public void on_validar(View view)
    {
        String txt_user_string = txt_user.getText().toString();
        String txt_pass_string = txt_pass.getText().toString();

        if(TextUtils.isEmpty(txt_user_string)) {
            txt_user.setError("Ingrese Usuario");
            return;
        }

        if(TextUtils.isEmpty(txt_pass_string)) {
            txt_pass.setError("Ingrese Contraseña");
            return;
        }

        RealizarPost();

        if(i>2)
        {
            finishAffinity();
            System.exit(0);
        }

    }
    public void RealizarPost() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://35.202.0.220/libreria_api/api/login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        if(response.isEmpty())
                        {
                            i++;
                            Toast.makeText(getApplicationContext(),"Cuenta no valida , Intentos restantes  : " + (3-i),Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent i = new Intent(MainActivity.this, Interfaz_principal.class);
                            startActivityForResult(i, 0);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user", txt_user.getText().toString());
                params.put("pass", txt_pass.getText().toString());

                return params;
            }
        };
        queue.add(postRequest);
    }
}
