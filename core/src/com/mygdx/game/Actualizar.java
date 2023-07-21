package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;

public class Actualizar implements Runnable {
    private long tiempoActual, tiempoAnterior, tiempoDisparo;
    private static Sound sound;
    private static Sound soundExplosion;
    private static Sound soundOver;
    private static Sound soundGolpe;
    public static long tiempoInmunidad;

    public Actualizar(){
        tiempoActual = System.currentTimeMillis();
        tiempoAnterior = System.currentTimeMillis();
        tiempoDisparo = System.currentTimeMillis();
        tiempoInmunidad = System.currentTimeMillis();;
        sound = Gdx.audio.newSound(Gdx.files.internal(Sonidos.disparo));
        soundExplosion = Gdx.audio.newSound(Gdx.files.internal(Sonidos.explosion));
        soundOver = Gdx.audio.newSound(Gdx.files.internal(Sonidos.gameOver));
        soundGolpe = Gdx.audio.newSound(Gdx.files.internal(Sonidos.golpe));

    }

    void act(){

        tiempoActual = System.currentTimeMillis();
        if (tiempoActual-tiempoAnterior >= 35) {
            if (TecladoController.estados[0]) //up
                NaveEspacial.mover(0,0.2f,0);
            if (TecladoController.estados[1]) //down
                NaveEspacial.mover(0,-0.2f,0);
            if (TecladoController.estados[2]) //left
                NaveEspacial.mover(0.2f,0,0);
            if (TecladoController.estados[3]) //right
                NaveEspacial.mover(-0.2f,0,0);
            tiempoAnterior = tiempoActual;
        }
        if (tiempoActual - tiempoDisparo >= 150) {
            Colisiones.verificarColisionAsteroides();
            Colisiones.verificarColisionBalas(soundExplosion);
            if (tiempoActual - tiempoInmunidad >= 2000) {
                Colisiones.verificarColisionNave(soundOver, soundGolpe);
            }
           if (TecladoController.estados[4]) {//disparo
                NaveEspacial.disparar();
                sound.play(0.3f);
           }
            if (Obstaculos.asteroides.size() < 11) {
               Obstaculos.agregarAsteroide();
            }
            //System.out.println(Obstaculos.asteroides.size());
            tiempoDisparo = tiempoActual;
        }
    }

    @Override
    public void run() {
        while (true){
            act();
        }
    }
}
