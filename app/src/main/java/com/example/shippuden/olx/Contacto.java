package com.example.shippuden.olx;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Shippuden on 29/05/2017.
 */
public class Contacto extends AppCompatActivity{
    ArrayList datocontacto=new ArrayList();
    ImageView ImgPerfil;
    TextView nombre_contacto;
    TextView telefono_contacto;
    TextView dir_contacto;
    TextView correo_contacto;
    TextView txtDescripcion;
    TextView txtPrecio;
    TextView txtFechaP;
    SmartImageView smartImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);
        ImgPerfil=(ImageView)findViewById(R.id.imgperfil);

        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        if(extras!=null){
            datocontacto=extras.getStringArrayList("DATOCONT");
        }
        setTitle("Datos de Contacto");
        //ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.contacto, null);
        smartImageView=(SmartImageView) findViewById(R.id.imgperfil);
        nombre_contacto=(TextView) findViewById(R.id.nom);
        telefono_contacto=(TextView) findViewById(R.id.tel);
        dir_contacto=(TextView) findViewById(R.id.dir);
        correo_contacto=(TextView) findViewById(R.id.email);
        txtDescripcion=(TextView) findViewById(R.id.dsc_producto);
        txtFechaP=(TextView) findViewById(R.id.fch_producto);
        txtPrecio=(TextView) findViewById(R.id.prc_producto);


        Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
        //Log.i("INFO_R",datocontacto.get(9).toString());
        smartImageView.setImageUrl(datocontacto.get(9).toString(),rect);
        nombre_contacto.setText(datocontacto.get(5).toString());
        telefono_contacto.setText(datocontacto.get(6).toString());
        dir_contacto.setText(datocontacto.get(7).toString());
        correo_contacto.setText(datocontacto.get(8).toString());
        txtDescripcion.setText(datocontacto.get(1).toString());
        txtFechaP.setText(datocontacto.get(3).toString());
        txtPrecio.setText(datocontacto.get(2).toString());

        smartImageView=(SmartImageView) findViewById(R.id.img_producto);
        Rect rect2=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
        smartImageView.setImageUrl(datocontacto.get(4).toString(),rect2);

    }
}
