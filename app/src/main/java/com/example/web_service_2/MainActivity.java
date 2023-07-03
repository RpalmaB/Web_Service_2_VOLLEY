package com.example.web_service_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity
 implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void limpiar (View View)
    {
        EditText usuario = findViewById(R.id.txtNombre);
        EditText pass = findViewById(R.id.txtContra);
        usuario.setText("");
        pass.setText("");
    }
    public void login (View View)
    {
        EditText usuario =(EditText) findViewById(R.id. txtNombre);
        EditText pass = (EditText) findViewById(R.id. txtContra);
        String usuario1;
        String pass1;

        usuario1= usuario.getText().toString();
        pass1 = pass.getText().toString();
        /*Bundle b = new Bundle();
        b.putString("USUARIO", usuario1);
        b.putString("PASS", pass1);

        Intent intent = new Intent(MainActivity.this, MainActivity_Bienvenido.class);
        intent.putExtras(b);

        startActivity(intent);*/

        Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo", usuario1);

        datos.put("clave", pass1);

        /*datos.put("correo", usuario1);
        datos.put("clave", pass1);*/

        WebService ws= new WebService("https://api.uealecpeterson.net/public/login"
                , datos, MainActivity.this, MainActivity.this);
        ws.execute("POST", "Authorization", "Bearer");

    }

        /*WebService ws= new WebService(
                "https://api.uealecpeterson.net/public/login"
                , datos, MainActivity.this, MainActivity.this);
        ws.execute("POST");*/




    @Override
    public void processFinish(String result) throws JSONException {

        TextView txtResp = findViewById(R.id.txtResp);
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject.has("error")){
            txtResp.setText("Resp: " +jsonObject.getString("error"));
        }
        else {
            Bundle b = new Bundle();
            b.putString("Token: " ,jsonObject.getString("access_token"));
            Intent intent = new Intent(MainActivity.this, MainActivity_Bienvenido.class);
            intent.putExtras(b);
            startActivity(intent);
        }

    }
}