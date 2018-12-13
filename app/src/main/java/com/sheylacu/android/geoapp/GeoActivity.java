package com.sheylacu.android.geoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sheylacu.android.geoapp.model.BancoDePreguntas;
import com.sheylacu.android.geoapp.model.Pregunta;

public class GeoActivity extends AppCompatActivity {

    private final String KEY_POSICION_ACTUAL= "posicion_actual";
    private final int REQUEST_CODE_SE_MOSTRO_RESPUESTA =0;
    private Button mBotonCierto;
    private Button mBotonFalso;
    private Button mBotonAnterior;
    private Button mBotonSiguiente;
    private Button mBotonVerRespuesta;
    private TextView mTextoPregunta;
    private BancoDePreguntas banco;
    private Pregunta mPreguntaActual;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int posicion = banco.getPosicionActual();
        outState.putInt(KEY_POSICION_ACTUAL, posicion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        createBancoDePreguntas();
        mPreguntaActual = banco.get(0);

        if (savedInstanceState !=null){
            int posicion = savedInstanceState.getInt(KEY_POSICION_ACTUAL);
        mPreguntaActual = banco.get(posicion);
        }


        mTextoPregunta = (TextView) findViewById(R.id.texto_pregunta);
        mBotonCierto = (Button) findViewById(R.id.boton_cierto);
        mBotonFalso = (Button) findViewById(R.id.boton_falso);
        mBotonVerRespuesta = (Button) findViewById(R.id.boton_mostrar_respuesta);
        mBotonAnterior = (Button) findViewById(R.id.boton_anterior);
        mBotonSiguiente = (Button) findViewById(R.id.boton_siguiente);

        actualizarPregunta();

        mBotonCierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(true);
            }
        });

        mBotonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(false);
            }
        });

        mBotonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreguntaActual = banco.previous();
                actualizarPregunta();
            }
        });

        mBotonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreguntaActual = banco.previous();
                actualizarPregunta();
            }
        });

        mBotonVerRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeoActivity.this, TrampaActivity.class);

                boolean isRespuestaCorrecta =mPreguntaActual.isVerdadera();
                intent.putExtra(TrampaActivity.EXTRA_RESPUESTA_ES_CORRECTA, isRespuestaCorrecta);

               // startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_SE_MOSTRO_RESPUESTA);
            }
        });
    }

    private void createBancoDePreguntas(){
        banco = new BancoDePreguntas();
        banco.add(new Pregunta(R.string.texto_pregunta_1, false));
        banco.add(new Pregunta(R.string.texto_pregunta_2,  true));
        banco.add(new Pregunta(R.string.texto_pregunta_3, true));
        banco.add(new Pregunta(R.string.texto_pregunta_4,  true));
        banco.add(new Pregunta(R.string.texto_pregunta_5,  false));

    }

    private void actualizarPregunta(){
        mTextoPregunta.setText(mPreguntaActual.getIdResTexto());
    }

    private void verificarRespuesta(boolean botonOprimido) {
        boolean respuestaEsVerdadera = mPreguntaActual.isVerdadera();
        if (botonOprimido == respuestaEsVerdadera){
            Toast.makeText(GeoActivity.this,
                    R.string.texto_correcto,
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(GeoActivity.this,
                    R.string.texto_incorrecto,
                    Toast.LENGTH_SHORT).show();
        }
    }

}
