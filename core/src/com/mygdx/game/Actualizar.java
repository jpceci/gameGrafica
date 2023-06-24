package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

public class Actualizar implements Runnable {
    private long tiempoActual, tiempoAnterior;


    public Actualizar(){
        tiempoActual = System.currentTimeMillis();
        tiempoAnterior = System.currentTimeMillis();
    }

    void act(){
        tiempoActual = System.currentTimeMillis();
        if (tiempoActual-tiempoAnterior >= 35) {
            if (TecladoController.estados[0]) //up
                NaveEspacial.instance.transform.trn(0,0.2f,0);
            if (TecladoController.estados[1]) //down
                NaveEspacial.instance.transform.trn(0,-0.2f,0);
            if (TecladoController.estados[2]) //left
                NaveEspacial.instance.transform.trn(0.2f,0,0);
            if (TecladoController.estados[3]) //right
                NaveEspacial.instance.transform.trn(-0.2f,0,0);
            for (int i = 0; i < NaveEspacial.balas.size(); i++) {
                Bala balaEnMovimiento = NaveEspacial.balas.get(i);
                balaEnMovimiento.instance.transform.trn(0,0,0.5f);
                Vector3 posicion = new Vector3();
                balaEnMovimiento.instance.transform.getTranslation(posicion);
                if (posicion.z == 50)
                    NaveEspacial.eliminarBala(balaEnMovimiento);
            }
            tiempoAnterior = tiempoActual;
        }

    }

    @Override
    public void run() {
        while (true){
            act();
        }
    }
}
