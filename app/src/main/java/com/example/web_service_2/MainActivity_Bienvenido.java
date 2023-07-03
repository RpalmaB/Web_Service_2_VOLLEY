package com.example.web_service_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity_Bienvenido extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bienvenido);
        /*TextView txtMensaje = findViewById(R.id.txtMensaje);*/

        Bundle bundle = this.getIntent().getExtras();
       /* txtMensaje.setText("Hola, Bienvenido \n" +
                bundle.getString("USUARIO")+ " "+ bundle.getString("PASS"));*/

        Map<String, String> datos = new HashMap<String, String>();
        datos.put("fuente", "1");

        WebService ws= new WebService("https://api.uealecpeterson.net/public/clientes/search"
                , datos, MainActivity_Bienvenido.this, MainActivity_Bienvenido.this);
        ws.execute("POST", "Authorization", "Bearer" + bundle.getString("Token"));
    }

    @Override
     public void processFinish(String result) throws JSONException {
    TextView Informacion = (TextView)findViewById(R.id.txtMensaje);
    String lstBancos="";
    JSONObject JSONOBJ = new  JSONObject(result);
    JSONArray JSONlista = JSONOBJ.getJSONArray("productos");
    for(int i=0; i< JSONlista.length();i++){
    JSONObject producto= JSONlista.getJSONObject(i);
    lstBancos = lstBancos +" ("+i+") "+producto.getString("barcode").toString()+" "+" "+" "+producto.getString("descripcion").toString()
            +producto.getString("costo").toString()+" "+" "+" "+producto.getString("impuesto").toString()+"\n";

    }
    Informacion.setText(lstBancos);

    }

}