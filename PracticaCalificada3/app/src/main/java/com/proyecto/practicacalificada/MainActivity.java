package com.proyecto.practicacalificada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {
    EditText txtConsulta;

    ListView recy;
    JsonObjectRequest jobs;
    JSONArray lisData;
    List<Vehiculo> lista;
    String url="http://10.0.2.2:8083/SerTransito/Controla.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy = (ListView)findViewById(R.id.listView1);
        txtConsulta = (EditText)findViewById(R.id.txtNombre);

         lista = new ArrayList<>();
    }

    public void consulta(View view){
        String consulta = txtConsulta.getText().toString();
        String enlace = url + "?tag=consulta2&cad="+consulta;
        lista = new ArrayList<>();

        jobs = new JsonObjectRequest(Request.Method.GET, enlace, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    lisData = response.getJSONArray("dato");
                    ArrayList listaCad = new ArrayList();

                    if(lisData == null){
                        Toast.makeText(getApplication() , "No se encontraron datos" ,Toast.LENGTH_LONG).show();
                    }else{
                        Log.w("datos" , lisData.toString());

                        for(int i = 0 ; i<lisData.length();i++){
                            JSONObject fila =(JSONObject)lisData.get(i);
                            Vehiculo p = new Vehiculo();
                            p.setNroPlaca(fila.getString("pla"));
                            p.setNombre(fila.getString("nombre"));
                            p.setColor(fila.getString("color"));
                            p.setModelo(fila.getString("modelo"));
                            lista.add(p);
                            listaCad.add(p.getNombre());
                        }

                        ArrayAdapter<String> adapta =new ArrayAdapter<String>(getApplication() ,
                                android.R.layout.simple_list_item_1 , listaCad);
                        recy.setAdapter(adapta);

                        recy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Vehiculo v = lista.get(position);
                                Intent it=new Intent(MainActivity.this,activity_Papeletas.class);
                                it.putExtra("codigo",v.getNroPlaca());
                                it.putExtra("nombre",v.getNombre());
                                startActivity(it);
                            }
                        });
                    }
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