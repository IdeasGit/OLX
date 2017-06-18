package com.example.shippuden.olx;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//url video para cargar imagen desde URL
//https://www.youtube.com/watch?v=A-KphPemhhg&vl=es
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageView img_vehiculo;
    ImageView img_celular;
    ImageView img_consola;
    ImageView img_deporte;
    ImageView img_inmueble;
    ImageView img_musica;
    ImageView img_portatil;
    ImageView img_tv;
    Intent intent;

    Button btn_redes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_vehiculo = (ImageView) findViewById(R.id.btn_vehiculo);
        img_celular  = (ImageView) findViewById(R.id.btn_celular);
        img_consola  = (ImageView) findViewById(R.id.btn_consola);
        img_deporte  = (ImageView) findViewById(R.id.btn_deporte);
        img_inmueble = (ImageView) findViewById(R.id.btn_inmueble);
        img_musica   = (ImageView) findViewById(R.id.btn_musica);
        img_portatil  = (ImageView) findViewById(R.id.btn_laptop);
        img_tv  = (ImageView) findViewById(R.id.btn_tv);
        img_vehiculo.setOnClickListener(this);
        img_celular.setOnClickListener(this);
        img_consola.setOnClickListener(this);
        img_deporte.setOnClickListener(this);
        img_inmueble.setOnClickListener(this);
        img_musica.setOnClickListener(this);
        img_portatil.setOnClickListener(this);
        img_tv.setOnClickListener(this);

        //btn_redes  = (Button) findViewById(R.id.btnredes);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btn_redes = (FloatingActionButton) findViewById(R.id.btnredes);
        btn_redes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_redes.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_camara) {
            intent = new Intent(MainActivity.this, Publicar_Categoria.class);
            startActivity(intent);
        } else if (id == R.id.nav_perfil) {

        } else if (id == R.id.nav_categoria) {

        } else if (id == R.id.nav_monedero) {

        } else if (id == R.id.nav_anuncio) {

        } else if (id == R.id.nav_mensaje) {

        } else if (id == R.id.nav_favorito) {

        } else if (id == R.id.nav_busqueda_guardada) {

        } else if (id == R.id.nav_ayuda) {

        } else if (id == R.id.nav_ajustes) {

        } else if (id == R.id.btn_vehiculo) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vehiculo:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Vehiculos");
                startActivity(intent);
                break;
            case R.id.btn_inmueble:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Inmuebles");
                startActivity(intent);
                break;
            case R.id.btn_celular:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Celulares");
                startActivity(intent);
                break;
            case R.id.btn_tv:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Televisores");
                startActivity(intent);
                break;
            case R.id.btn_laptop:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Computadores");
                startActivity(intent);
                break;
            case R.id.btn_consola:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","Consolas");
                startActivity(intent);
                break;
            case R.id.btn_deporte:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","btn_deporte");
                startActivity(intent);
                break;
            case R.id.btn_musica:
                intent = new Intent(MainActivity.this, Productos.class);
                intent.putExtra("DATOS","btn_musica");
                startActivity(intent);
                break;
            case R.id.btnredes:
                //Boton redes sociales
                //https://javaheros.blogspot.com.co/2015/10/compartir-en-redes-sociales-en-android.html
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Compartiendo info desde app");
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
        }
    }
}
