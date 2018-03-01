package com.quileia.quileiacrud;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import Entidades.Vehiculo;
import Negocio.Quileia;
import OpenHelper.AutoDB;
import Utilidades.Utilidades;

/**
 * Clase principal, se desarrolló según tamaño de Nexus 5X.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    private Button crearButton, coloresButton;
    private RecyclerView autosLista;
    private ArrayList<Vehiculo> vehiculos = new ArrayList();
    private  AutoDB autoDB;
    private ArrayList placas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        autoDB = new AutoDB(this);

        crearButton =(Button) findViewById(R.id.buttonCrear);
        coloresButton = (Button) findViewById(R.id.buttonColores);
        autosLista = (RecyclerView) findViewById(R.id.listAutos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        autosLista.setLayoutManager(mLayoutManager);

        coloresButton.setVisibility(View.GONE);
        AdaptadorVehiculo adapter = null;
        vehiculos = Quileia.leer(autoDB);
        if(!vehiculos.isEmpty()) {
            for(Vehiculo ve:vehiculos){
                placas.add(ve.getPlaca());
            }
            adapter = new AdaptadorVehiculo(vehiculos);
            autosLista.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No hay vehículos", Toast.LENGTH_SHORT).show();
        }

        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InformacionActivity.class);
                intent.putExtra("accion",1);
                startActivity(intent);
            }
        });

        if(adapter!=null) {
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Vehiculo v = (Vehiculo) vehiculos.get(autosLista.getChildAdapterPosition(view));
                    Intent intent = new Intent(getApplicationContext(), InformacionActivity.class);
                    intent.putExtra(Utilidades.ACCION, 0);
                    intent.putExtra(Utilidades.ID, v.getId());
                    intent.putExtra(Utilidades.PLACA, v.getPlaca());
                    intent.putExtra(Utilidades.MARCA, v.getMarca());
                    intent.putExtra(Utilidades.MODELO, v.getModelo());
                    intent.putExtra(Utilidades.PUERTAS, v.getPuertas());
                    intent.putExtra(Utilidades.TIPO, v.getTipo());
                    intent.putExtra(Utilidades.COLOR, v.getColor());
                    intent.putStringArrayListExtra(Utilidades.PLACAS, placas);
                    startActivity(intent);
                }
            });
        }


        coloresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ColorActivity.class);
                startActivity(intent);
            }
        });
    }
}
