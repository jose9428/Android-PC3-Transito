package com.proyecto.practicacalificada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class activity_GrabarPapeletas extends AppCompatActivity {
    EditText txtPlaca;
    EditText txtInfraccion;
    EditText txtPolicia;
    Button botonAceptar;
    JsonObjectRequest jobs;

    String url="http://10.0.2.2:8083/SerTransito/Controla.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__grabar_papeletas);

        txtPlaca = (EditText)findViewById(R.id.txtPlaca);
        txtInfraccion = (EditText)findViewById(R.id.txtInfraccion);
        txtPolicia = (EditText)findViewById(R.id.txtPolicia);
    }

    public void registrar(View view){
        String enlace = url + "?tag=adicion&pla="+txtPlaca.getText()+"&cod="+
                txtInfraccion.getText()+"&pol="+txtPolicia.getText();
        jobs = new JsonObjectRequest(Request.Method.GET, enlace, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String res = response.getString("dato");

                    if(res.equalsIgnoreCase("ok")){
                        Toast.makeText(activity_GrabarPapeletas.this, "Papeleta registrada correctamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity_GrabarPapeletas.this, "No se ha podido registrar papeleta", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(getApplication() , "Respuesta : "+ex.getMessage() ,Toast.LENGTH_LONG).show();
                    Log.w("err" , ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("error" , error.getMessage());
            }
        });
        RequestQueue cola = Volley.newRequestQueue(getApplication());
        cola.add(jobs);
    }
}