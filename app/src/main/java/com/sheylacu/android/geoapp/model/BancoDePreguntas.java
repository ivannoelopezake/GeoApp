package com.sheylacu.android.geoapp.model;

import java.util.ArrayList;
import java.util.List;

public class BancoDePreguntas {
    private final List<Pregunta> banco;
    private int posicionActual;
    private int numElementos;

    public BancoDePreguntas(){
        banco = new ArrayList<>();
        posicionActual = - 1;
    }


    public void add(Pregunta pregunta) {
        banco.add(pregunta);
        if (size () ==1){
            posicionActual =0;
        }
    }

    public int getPosicionActual(){
        return posicionActual;
    }

    public int size(){
        return banco.size();
    }

    public Pregunta get(int posicion){

        posicionActual = posicion;
        return banco.get(posicion);
    }

    public boolean isEmpty (){
        return banco.isEmpty();
    }

    public Pregunta next(){
        if (isEmpty()){
            return null;
        }
        else {
            if (posicionActual == size() - 1){
                posicionActual = 0;
            }
            else {
                posicionActual++;
            }
            return banco.get(posicionActual);
        }
    }

    public Pregunta previous (){
        if (isEmpty()){
            return null;
        }
        else {
            if (posicionActual ==0){
                posicionActual = size() - 1;
            }
            else {
                posicionActual--;
            }
            return banco.get(posicionActual);
        }
    }
}
