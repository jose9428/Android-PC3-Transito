package com.proyecto.practicacalificada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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
import java.util.List;

public class activity_Papeletas extends AppCompatActivity {
    TextView propietario;
    TextView txtTotal;
    RecyclerView recy;
    JsonObjectRequest jobs;
    JSONArray lisData;

    String url="http://10.0.2.2:8083/SerTransito/Controla.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__papeletas);

        propietario = (TextView)findViewById(R.id.txtPropieatario);
        txtTotal= (TextView)findViewById(R.id.txtTotal);
        recy =(RecyclerView)findViewById(R.id.recy2);

        String nombreProp = getIntent().getStringExtra("nombre");
        String codigo =getIntent().getStringExtra("codigo");

        propietario.setText("Papeleta de : "+nombreProp);
        Cargar(codigo);
    }

    public void Cargar(String code){
        String enlace = url + "?tag=consulta3&cad="+code;


        jobs = new JsonObjectRequest(Request.Method.GET, enlace, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                double total = 0;
                try{
                    lisData = response.getJSONArray("dato");
                    Log.w("datos" , lisData.toString());
                    List<Papeleta> lista = new ArrayList<>();
                    for(int i = 0 ; i<lisData.length();i++){
                        JSONObject fila =(JSONObject)lisData.get(i);
                        Papeleta p = new Papeleta();
                        p.setNroPap(fila.getString("pape"));
                        p.setFecha(fila.getString("fecha"));
                        p.setInfraccion(fila.getString("desp"));
                        p.setMulta(fila.getDouble("monto"));
                        lista.add(p);

                        total+= p.getMulta();
                    }

                    txtTotal.setText("Total a Pagar => "+total);

                    Adapta1 adapta = new Adapta1(lista ,getApplication());
                    recy.setLayoutManager(new LinearLayoutManager(getApplication()));
                    recy.setAdapter(adapta);

                }catch (Exception ex){
                    Toast.makeText(getApplication() , "Respuesta : "+ex.getMessage() ,Toast.LENGTH_LONG).show();
                    Log.w("err" , ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication() , "Error : "+ error.getMessage() ,
                        Toast.LENGTH_LONG).show();
                Log.w("error" , error.getMessage());
            }
        });
        RequestQueue cola = Volley.newRequestQueue(getApplication());
        cola.add(jobs);

    }

}