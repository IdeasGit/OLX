package com.example.shippuden.olx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shippuden.olx.Objetos.Articulos;
import com.example.shippuden.olx.Objetos.FirebaseReferencias;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shippuden on 06/06/2017.
 */

public class Publicar extends AppCompatActivity {
    private EditText e_descripcion, e_valor;
    String dato = "", e_nombreContacto, e_celular, e_direccion, e_correo, e_fotoPerfil, e_imgagenProd;
    Button btn_enviar, btnfotostorage;
    ImageView miImagen;
    StorageReference storageRef;
    ProgressDialog progressDialog;
    private static int GALLERY_INTENT = 1;
    private static int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicar);
        //Como enviar data a Firebase Database https://www.youtube.com/watch?v=8fHO67t9dhI
        //Como subir una imagen Firebase Storage https://www.youtube.com/watch?v=pNleQQhVfd0
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(FirebaseReferencias.PRODUCTO_REFERENCIA);
        storageRef = FirebaseStorage.getInstance().getReference();

        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        if(extras!=null){
            dato=extras.getString("DATOS");
        }
        setTitle("Categoria "+dato);
        miImagen=(ImageView) findViewById(R.id.imgFoto);
        progressDialog=new ProgressDialog(this);
        e_descripcion=(EditText) findViewById(R.id.et_descripcion);
        e_valor=(EditText) findViewById(R.id.et_valor);
        //Estos datos se deben obtener de los datos de autenticacion
        e_nombreContacto="Nombre Contacto";
        e_celular="3113225422";
        e_direccion="DireccionABC";
        e_correo="correo@123.com";
        e_fotoPerfil="https://imagenesprueba.000webhostapp.com/imgolx/imgperfil.png";
        btn_enviar=(Button) findViewById(R.id.btnenvia);
        btnfotostorage=(Button) findViewById(R.id.btnsubir);
        btnfotostorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Articulos articulo=new Articulos(e_descripcion.getText().toString(),e_valor.getText().toString(),getfechaActual(),e_imgagenProd, e_nombreContacto, e_celular, e_direccion, e_correo, e_fotoPerfil);
                myRef.child(dato).push().setValue(articulo);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            progressDialog.setMessage("Subiendo...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            Uri uri = data.getData();
            StorageReference filePath = storageRef.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri descargarFoto= taskSnapshot.getDownloadUrl();
                    Log.i("INFO_FOTO",descargarFoto.toString());
                    Glide.with(Publicar.this)
                            .load(descargarFoto)
                            .fitCenter()
                            .centerCrop()
                            .into(miImagen);
                    e_imgagenProd=descargarFoto.toString();
                    Toast.makeText(Publicar.this,"La imagen fue cargada satisfactoriamente",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static String getfechaActual(){
        Date fechaActual = new Date();
        SimpleDateFormat apptivaWeb = new SimpleDateFormat("dd-MM-yyyy");
        return apptivaWeb.format(fechaActual);
    }
}
