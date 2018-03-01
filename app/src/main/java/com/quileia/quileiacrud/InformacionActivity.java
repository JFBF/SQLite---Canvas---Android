package com.quileia.quileiacrud;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Negocio.Quileia;
import OpenHelper.AutoDB;
import Utilidades.Utilidades;

/**
 * Clase que permite editar,crear y eliminar un vehículo.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class InformacionActivity extends AppCompatActivity {

    private EditText placa,marca,modelo,puertas,tipo,color;
    private Button crearButton, actualizarButton,eliminarButton, colorButton;
    private AutoDB autoDB;
    private String aPlaca,aModelo,aMarca,aTipo,aColor;
    private int aPuertas,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoDB = new AutoDB(this);
        autoDB.abrir();

        placa = (EditText) findViewById(R.id.editTextPlaca);
        marca = (EditText) findViewById(R.id.editTextMarca);
        modelo = (EditText) findViewById(R.id.editTextModelo);
        puertas = (EditText) findViewById(R.id.editTextPuertas);
        tipo = (EditText) findViewById(R.id.editTextTipo);
        color = (EditText) findViewById(R.id.editTextColorN);

        crearButton = (Button) findViewById(R.id.buttonCrearAccion);
        actualizarButton = (Button) findViewById(R.id.buttonActualizar);
        eliminarButton = (Button) findViewById(R.id.buttonEliminar);
        colorButton = (Button) findViewById(R.id.buttonColor);

        int accion = getIntent().getIntExtra(Utilidades.ACCION,-1);
        if(accion == -1){
            Toast.makeText(this, "Error, vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }else if(accion == 1){
            crearButton.setVisibility(View.VISIBLE);
            actualizarButton.setVisibility(View.GONE);
            eliminarButton.setVisibility(View.GONE);
            colorButton.setVisibility(View.GONE);
        }else if(accion == 0){
            crearButton.setVisibility(View.GONE);
            actualizarButton.setVisibility(View.VISIBLE);
            eliminarButton.setVisibility(View.VISIBLE);
            colorButton.setVisibility(View.VISIBLE);

            aPlaca = getIntent().getStringExtra(Utilidades.PLACA);
            aMarca = getIntent().getStringExtra(Utilidades.MARCA);
            aModelo = getIntent().getStringExtra(Utilidades.MODELO);
            aTipo = getIntent().getStringExtra(Utilidades.TIPO);
            aPuertas = getIntent().getIntExtra(Utilidades.PUERTAS,-1);
            aColor = getIntent().getStringExtra(Utilidades.COLOR);
            id = getIntent().getIntExtra(Utilidades.ID,-1);

            placa.setText(aPlaca);
            marca.setText(aMarca);
            modelo.setText(aModelo);
            tipo.setText(aTipo);
            puertas.setText(aPuertas+"");
            if(aColor!=null){
                color.setText(aColor);
            }
            else{
                color.setText("No asignado.");
            }
        }

        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarForma()){
                    if(crearAuto()){
                        Toast.makeText(InformacionActivity.this, "Se ha guardado.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(InformacionActivity.this, "Error creando.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarActualizacion()){
                    if(validarForma()){
                        if(actualizarAuto()){
                            Toast.makeText(InformacionActivity.this, "Se ha actualizado.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(InformacionActivity.this, "Error actualizando.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(InformacionActivity.this, "No ha hecho cambios.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoDB.abrir();
                if(Quileia.eliminarAuto(autoDB,id)) {
                    Toast.makeText(InformacionActivity.this, "Se ha eliminado.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    autoDB.cerrar();
                }
                else {
                    Toast.makeText(InformacionActivity.this, "Error eliminando.", Toast.LENGTH_SHORT).show();
                    autoDB.cerrar();
                }
            }
        });

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ColorActivity.class);
                intent.putExtra(Utilidades.ID,id);
                startActivity(intent);
            }
        });
    }

    /**
     * Método para validar el formato de los datos ingresados.
     * @return true si todos los parametros cumplen.
     */
    private	boolean validarForma()	{
        boolean valid	=	true;
        String	pla	=	placa.getText().toString();
        if	(TextUtils.isEmpty(pla))	{
            placa.setError("Requerido.");
            valid	=	false;
        }else{
            placa.setError(null);
        }
        String	mar	=	marca.getText().toString();
        if	(TextUtils.isEmpty(mar))	{
            marca.setError("Requerido.");
            valid	=	false;
        }else{
            marca.setError(null);
        }
        String	mode	=	modelo.getText().toString();
        if	(TextUtils.isEmpty(mode))	{
            modelo.setError("Requerido.");
            valid	=	false;
        }else{
            if(mode.length()<4){
                modelo.setError("4 dígitos.");
            }else{
                modelo.setError(null);
            }
        }
        if	(TextUtils.isEmpty(puertas.getText().toString()))	{
            puertas.setError("Requerido.");
            valid	=	false;
        }else{
            int	puer = Integer.parseInt(puertas.getText().toString());
            if(puer<1){
                puertas.setError("Mínimo 1 puerta.");
            }else
                puertas.setError(null);
        }
        String	tip	=	tipo.getText().toString();
        if	(TextUtils.isEmpty(tip))	{
            tipo.setError("Requerido.");
            valid	=	false;
        }else{
            tipo.setError(null);
        }
        return	valid;
    }

    /**
     * Método para crear un vehículo.
     * @return true si crea el vehículo correctamente.
     */
    private boolean crearAuto(){
        return Quileia.crearAuto(autoDB,placa.getText().toString().trim(),
                marca.getText().toString().trim(),modelo.getText().toString().trim(),
                tipo.getText().toString().trim(),Integer.parseInt(puertas.getText().toString().trim()),
                color.getText().toString().trim());
    }

    /**
     * Método para validar si se cambiaron los datos
     * y si la nueva placa ya esta registrada.
     * @return true si se hicieron cambios y la
     * placa no esta duplicada.
     */
    private boolean validarActualizacion (){
        boolean valid = false;
        if(!placa.getText().toString().toUpperCase().trim().equals(aPlaca)) {
            valid = true;
            ArrayList placas = getIntent().getIntegerArrayListExtra(Utilidades.PLACAS);
            if(placas.contains(placa.getText().toString().trim())){
                Toast.makeText(this, "Placa ya registrada.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(!marca.getText().toString().trim().equals(aMarca))
            valid = true;
        if(!modelo.getText().toString().trim().equals(aModelo))
            valid = true;
        if(Integer.parseInt(puertas.getText().toString())!=aPuertas)
            valid = true;
        if(!tipo.getText().toString().trim().equals(aTipo))
            valid = true;
        return valid;
    }

    /**
     * Método para actualizar el vehículo.
     * @return true si hace la actualización correctamente.
     */
    private boolean actualizarAuto(){
        return Quileia.actualizarAuto(autoDB,id,placa.getText().toString().trim().toUpperCase(),
                marca.getText().toString().trim(),
                modelo.getText().toString().trim(),
                tipo.getText().toString().trim(),
                Integer.parseInt(puertas.getText().toString().trim()));
    }
}
