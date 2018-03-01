package com.quileia.quileiacrud;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import Negocio.Quileia;
import OpenHelper.AutoDB;
import Utilidades.Utilidades;

/**
 * Clase que maneja el diseño de los colores de las partes del vehículo.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class ColorActivity extends AppCompatActivity {

    private Spinner llantaS,baulS,puertaS;
    private final static String[] colores = {Utilidades.AZUL,
            Utilidades.VERDE,Utilidades.AMARILLO,
            Utilidades.ROJO};
    private ConstraintLayout layout;
    private Vista vista;
    private String llantasColor = Utilidades.AZUL, baulColor = Utilidades.AZUL,
            puertasColor = Utilidades.AZUL;
    private int id;
    private Button guardarButton;
    private AutoDB autoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        layout = (ConstraintLayout) findViewById(R.id.layoutColor);
        vista = new Vista(this);
        layout.addView(vista);

        autoDB = new AutoDB(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        llantaS = (Spinner) findViewById(R.id.spinnerLlanta);
        baulS = (Spinner) findViewById(R.id.spinnerBaul);
        puertaS = (Spinner) findViewById(R.id.spinnerPuerta);
        guardarButton = (Button) findViewById(R.id.buttonCambiarColor);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                colores);

        llantaS.setAdapter(adapter);
        baulS.setAdapter(adapter);
        puertaS.setAdapter(adapter);

        id = getIntent().getIntExtra(Utilidades.ID,-1);

        llantaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                llantasColor = (String) llantaS.getSelectedItem();
                layout.removeView(vista);
                vista = new Vista(getApplication());
                layout.addView(vista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        baulS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                baulColor = (String) baulS.getSelectedItem();
                layout.removeView(vista);
                vista = new Vista(getApplication());
                layout.addView(vista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        puertaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                puertasColor = (String) puertaS.getSelectedItem();
                layout.removeView(vista);
                vista = new Vista(getApplication());
                layout.addView(vista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=-1) {
                    if (actualizarAuto()) {
                        Toast.makeText(ColorActivity.this, "Se ha guardado.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ColorActivity.this, "Error guardando.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ColorActivity.this, "Error cargando datos de auto.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Clase con vista para manejar los dibujos.
     * @author Felipe Bautista
     * @version 22/02/2018
     * @since 1.0
     */
    private class Vista extends View{

        public Vista (Context c){
            super(c);
        }

        /**
         * Método sobreescrito para crear las imagenes especiales.
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {

            // llanta
            Paint paint = new Paint();
            Path path = new Path();
            Path path2 = new Path();

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            paint.setColor(Color.BLACK);

            path.addOval(150,400,380,600,Path.Direction.CW);
            canvas.drawPath(path,paint);

            paint.setStyle(Paint.Style.FILL);
            if(llantasColor.equals(Utilidades.AZUL))
                paint.setColor(Color.BLUE);
            if(llantasColor.equals(Utilidades.VERDE))
                paint.setColor(Color.GREEN);
            if(llantasColor.equals(Utilidades.AMARILLO))
                paint.setColor(Color.YELLOW);
            if(llantasColor.equals(Utilidades.ROJO))
                paint.setColor(Color.RED);
            canvas.drawPath(path,paint);

            path2.addOval(180,430,345,570, Path.Direction.CW);
            canvas.drawPath(path2,paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLACK);
            canvas.drawPath(path2,paint);

            //baúl
            Path path3 = new Path();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLACK);

            path3.moveTo(320, 850);
            path3.lineTo(500, 860);
            path3.lineTo(500, 880);
            path3.lineTo(320, 900);
            path3.lineTo(320, 850);
            canvas.drawPath(path3,paint);
            path3.moveTo(250, 900);
            path3.lineTo(70, 880);
            path3.lineTo(70, 860);
            path3.lineTo(250, 850);
            path3.lineTo(250, 900);
            canvas.drawPath(path3,paint);
            paint.setStyle(Paint.Style.FILL);
            if(baulColor.equals(Utilidades.AZUL))
                paint.setColor(Color.BLUE);
            if(baulColor.equals(Utilidades.VERDE))
                paint.setColor(Color.GREEN);
            if(baulColor.equals(Utilidades.AMARILLO))
                paint.setColor(Color.YELLOW);
            if(baulColor.equals(Utilidades.ROJO))
                paint.setColor(Color.RED);
            canvas.drawPath(path3,paint);

            // puerta
            Path path4 = new Path();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLACK);
            path4.moveTo(180, 1300);
            path4.lineTo(350,1300);
            path4.lineTo(350,1500);
            path4.lineTo(150,1500);
            path4.lineTo(150,1340);
            path4.lineTo(180,1300);
            canvas.drawPath(path4,paint);
            paint.setStyle(Paint.Style.FILL);
            if(puertasColor.equals(Utilidades.AZUL))
                paint.setColor(Color.BLUE);
            if(puertasColor.equals(Utilidades.VERDE))
                paint.setColor(Color.GREEN);
            if(puertasColor.equals(Utilidades.AMARILLO))
                paint.setColor(Color.YELLOW);
            if(puertasColor.equals(Utilidades.ROJO))
                paint.setColor(Color.RED);
            canvas.drawPath(path4,paint);

        }
    }

    /**
     * Método que actualiza el color de las llantas de un vehículo.
     * @return True si lo actualiza correctamente.
     */
    private boolean actualizarAuto(){
       return Quileia.actualizarAutoColor(autoDB,id,llantasColor);
    }
}
