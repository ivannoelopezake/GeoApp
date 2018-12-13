package com.sheylacu.android.geoapp.model;

public class Pregunta {

    private int mIdResTexto;
    private boolean mVerdadera;


    public Pregunta (int idResTexto, boolean verdadera){
        mIdResTexto = idResTexto;
        mVerdadera = verdadera;
    }

    public int getIdResTexto() { return mIdResTexto; }   //* constructor que inicializa *//

    public void setIdResTexto ( int idRestexto) { mIdResTexto = idRestexto; }

    public boolean isVerdadera() { return  mVerdadera; }

    public void setVerdadera ( boolean verdadera) { mVerdadera = verdadera; }

}
