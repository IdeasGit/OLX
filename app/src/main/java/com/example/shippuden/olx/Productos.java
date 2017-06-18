package com.example.shippuden.olx;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shippuden.olx.Objetos.Articulos;
import com.github.snowdream.android.widget.SmartImageView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Shippuden on 23/05/2017.
 */

public class Productos extends AppCompatActivity {
    ListView listaProd;
    ArrayList IdProducto=new ArrayList();
    ArrayList Descrpcion=new ArrayList();
    ArrayList Valor=new ArrayList();
    ArrayList Fecha=new ArrayList();
    ArrayList urlImagen=new ArrayList();
    ArrayList nomContact=new ArrayList();
    ArrayList celular=new ArrayList();
    ArrayList direccion=new ArrayList();
    ArrayList email=new ArrayList();
    ArrayList imgPerfil=new ArrayList();
    DatabaseReference dbArticulos;
    String dato;

    public Productos() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);
        //Recive dato
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        if(extras!=null){
            dato=extras.getString("DATOS");
        }
        setTitle(dato);
        listaProd = (ListView)findViewById(R.id.listaproductos);
        cargarLista();
        listaProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intentcont;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view.findViewById(R.id.idproducto);
                ArrayList alContacto=getContacto(Integer.parseInt(tv.getText().toString()));
                intentcont = new Intent(Productos.this, Contacto.class);
                intentcont.putExtra("DATOCONT",alContacto);//Se envia ArrayList con los datos de contacto al activity Contacto
                startActivity(intentcont);
                Toast.makeText(getApplicationContext(),alContacto.get(1).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Carga el ListView con los elementos de acuerdo a la categoria seleccionada
    private void cargarLista() {
        Log.i("INFO_Producto","cargarLista");

        final ProgressDialog progressDialog = new ProgressDialog(Productos.this);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        dbArticulos = FirebaseDatabase.getInstance().getReference()
                .child("Articulos")
                    .child(dato);

        dbArticulos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("INFO_Producto","cargarLista_dataSnapshot: "+dataSnapshot);
                Descrpcion.clear();
                Valor.clear();
                Fecha.clear();
                urlImagen.clear();
                nomContact.clear();
                celular.clear();
                direccion.clear();
                email.clear();
                imgPerfil.clear();
                IdProducto.clear();
                Integer contador =0;
                for (DataSnapshot matchSnapShot: dataSnapshot.getChildren()) {
                    Articulos art= matchSnapShot.getValue(Articulos.class);
                    String valor =  art.getValor();

                    Descrpcion.add(art.getDescripcion());
                    Valor.add(art.getValor());
                    Fecha.add(art.getFecha());
                    urlImagen.add(art.getImgagenProd());
                    nomContact.add(art.getNombreContacto());
                    celular.add(art.getCelular());
                    direccion.add(art.getDireccion());
                    email.add(art.getCorreo());
                    imgPerfil.add(art.getFotoPerfil());
                    IdProducto.add(contador);
                    contador++;

                    listaProd.setAdapter(new ImagenAdapter(getApplicationContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e("ErrorDB!", String.valueOf(databaseError.toException()));
            }
        });

    }
    private class ImagenAdapter extends BaseAdapter{
        Context ctx;
        LayoutInflater layoutIflater;
        SmartImageView smartImageView;
        TextView txtDescripcion;
        TextView txtPrecio;
        TextView txtFechaP;
        TextView txtIdProducto;

        public ImagenAdapter(Context applicationContext){
            this.ctx=applicationContext;
            layoutIflater=(LayoutInflater)ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return urlImagen.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("INFO_Producto","ImagenAdapter_getView_position: "+position);
            ViewGroup viewGroup=(ViewGroup)layoutIflater.inflate(R.layout.activity_main_item, null);
            smartImageView=(SmartImageView) viewGroup.findViewById(R.id.imagen_producto);
            txtDescripcion=(TextView) viewGroup.findViewById(R.id.descripcio_producto);
            txtPrecio=(TextView) viewGroup.findViewById(R.id.precio_producto);
            txtFechaP=(TextView) viewGroup.findViewById(R.id.fecha_producto);
            txtIdProducto=(TextView) viewGroup.findViewById(R.id.idproducto);
            Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
            smartImageView.setImageUrl(urlImagen.get(position).toString(),rect);

            txtDescripcion.setText(Descrpcion.get(position).toString());
            txtPrecio.setText(Valor.get(position).toString());
            txtFechaP.setText(Fecha.get(position).toString());
            txtIdProducto.setText(IdProducto.get(position).toString());
            return viewGroup;
        }
    }
    //Retorna un Arraylist con los elemntos del item seleccionado, recive como parametro el id del elemento seleccionado
    public ArrayList getContacto(int pIdProducto){
        Log.i("INFO_Producto","getContacto_pIdProducto: "+pIdProducto);

        int pprdstr=pIdProducto; //id de producto seleccionado

        ArrayList datosContacto=new ArrayList();

        datosContacto.add(dato);  //Categoria
        datosContacto.add(Descrpcion.get(pprdstr));
        datosContacto.add(Valor.get(pprdstr));
        datosContacto.add(Fecha.get(pprdstr));
        datosContacto.add(urlImagen.get(pprdstr));
        datosContacto.add(nomContact.get(pprdstr));
        datosContacto.add(celular.get(pprdstr));
        datosContacto.add(direccion.get(pprdstr));
        datosContacto.add(email.get(pprdstr));
        datosContacto.add(imgPerfil.get(pprdstr));
        datosContacto.add(pprdstr); //Id del producto

        return datosContacto;
    }
}
