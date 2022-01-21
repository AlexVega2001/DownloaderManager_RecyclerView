package com.example.downloadermanager_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.downloadermanager_recyclerview.Adapter.AdptFicheros;
import com.example.downloadermanager_recyclerview.Model.DownloadManagerClass;
import com.example.downloadermanager_recyclerview.Model.Ficheros;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Ficheros> lstfich;
    AdptFicheros adapterFich;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rcvFicheros);
        lstfich = new ArrayList<>();
        getFiles();
    }

    public void getFiles() {
        String jsonUrl = "https://my-json-server.typicode.com/AlexVega2001/jsonServerFake/files";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        saveFileClass(response);

                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapterFich = new AdptFicheros(lstfich, getApplicationContext());
                        recyclerView.setAdapter(adapterFich);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error de Conexión:" +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayReq);
    }

    public void saveFileClass(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                Ficheros ficheros = new Ficheros();
                ficheros.setId(Integer.parseInt(object.getString("id")));
                ficheros.setDescription("Fichero " + (i + 1) + " : " + object.getString("description"));
                ficheros.setTamanho("Tamaño : " + object.getString("Tamaño"));
                ficheros.setUrl(object.getString("url"));
                lstfich.add(ficheros);
            }
        } catch (JSONException jsonException) {
            Toast.makeText(getApplicationContext(),
                    "Error: " + jsonException.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

}