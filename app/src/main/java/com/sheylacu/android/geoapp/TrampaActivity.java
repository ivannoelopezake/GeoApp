package com.sheylacu.android.geoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrampaActivity extends AppCompatActivity {

    public static String EXTRA_RESPUESTA_ES_CORRECTA = "com.sheylacu.android.geoapp.Respuesta_Es_Correcta";
    public static String EXTRA_SE_MOSTRO_RESPUESTA = "com.sheylacu.android.geoapp.Se_Mostro_Respuesta";
    private Button mBotonMostrarRespuesta;
    private TextView mRespuestaTexView;

    public static boolean respuestaMostrada(Intent result){
        return result.getBooleanExtra(EXTRA_SE_MOSTRO_RESPUESTA, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trampa);

        mRespuestaTexView = findViewById(R.id.respuesta_textview);
        mBotonMostrarRespuesta = findViewById(R.id.boton_mostrar_respuesta);

        mBotonMostrarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                boolean RespuestaEsCorrecta = intent.getBooleanExtra(EXTRA_RESPUESTA_ES_CORRECTA, false);
                if (RespuestaEsCorrecta){
                    mRespuestaTexView.setText("Cierto");
                }
                else {
                    mRespuestaTexView.setText("Falso");
                }
               setSeMostroRespuestaResult(true);
            }
        });
    }
    private void setSeMostroRespuestaResult (boolean seMostroRespuestaResult){
        Intent datos = new Intent();
        datos.putExtra(EXTRA_SE_MOSTRO_RESPUESTA, seMostroRespuestaResult);
        setResult(RESULT_OK, datos);
    }
}
