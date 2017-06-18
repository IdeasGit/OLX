package com.example.shippuden.olx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

/**
 * Created by Shippuden on 11/06/2017.
 */

public class Publicar_Categoria extends AppCompatActivity implements View.OnClickListener {
    LinearLayout l_vehiculo;
    LinearLayout l_inmueble;
    LinearLayout l_computador;
    LinearLayout l_televisor;
    LinearLayout l_celular;
    LinearLayout l_consola;
    public Publicar_Categoria() {

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicar_categoria);
        l_vehiculo=(LinearLayout) findViewById(R.id.pc_vehiculo);
        l_inmueble=(LinearLayout) findViewById(R.id.pc_inmueble);
        l_computador=(LinearLayout) findViewById(R.id.pc_computador);
        l_televisor=(LinearLayout) findViewById(R.id.pc_televisor);
        l_celular=(LinearLayout) findViewById(R.id.pc_celular);
        l_consola=(LinearLayout) findViewById(R.id.pc_consola);
        l_vehiculo.setOnClickListener(this);
        l_inmueble.setOnClickListener(this);
        l_computador.setOnClickListener(this);
        l_televisor.setOnClickListener(this);
        l_celular.setOnClickListener(this);
        l_consola.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(Publicar_Categoria.this, Publicar.class);
        switch(v.getId()){
            case R.id.pc_vehiculo:
                intent.putExtra("DATOS","Vehiculos");
                startActivity(intent);
                break;
            case R.id.pc_inmueble:
                intent.putExtra("DATOS","Inmuebles");
                startActivity(intent);
                break;
            case R.id.pc_computador:
                intent.putExtra("DATOS","Computadores");
                startActivity(intent);
                break;
            case R.id.pc_televisor:
                intent.putExtra("DATOS","Televisores");
                startActivity(intent);
                break;
            case R.id.pc_celular:
                intent.putExtra("DATOS","Celulares");
                startActivity(intent);
                break;
            case R.id.pc_consola:
                intent.putExtra("DATOS","Consolas");
                startActivity(intent);
                break;
        }
    }
}
