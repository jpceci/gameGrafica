package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.bullet.collision.CollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDispatcherInfo;
import com.badlogic.gdx.physics.bullet.collision.btManifoldResult;
import com.badlogic.gdx.physics.bullet.collision.btSphereSphereCollisionAlgorithm;

public class Colisiones {

    final static boolean checkCollision(btCollisionObject objeto1, btCollisionObject objeto2) {
        final CollisionObjectWrapper co1 = new CollisionObjectWrapper(objeto1);
        final CollisionObjectWrapper co2 = new CollisionObjectWrapper(objeto2);
        final btCollisionAlgorithm algorithm = new btSphereSphereCollisionAlgorithm(null,Obstaculos.ci,co1.wrapper,co2.wrapper);
        final btDispatcherInfo info = new btDispatcherInfo();
        final btManifoldResult result = new btManifoldResult(co1.wrapper, co2.wrapper);
        algorithm.processCollision(co1.wrapper,co2.wrapper,info,result);
        final boolean r = result.getPersistentManifold().getNumContacts() > 0;
        result.dispose();
        info.dispose();
        algorithm.dispose();
        co1.dispose();
        co2.dispose();

        return r;
    }

    public static void verificarColisionAsteroides(){
        for (int i = 0; i < Obstaculos.asteroides.size() - 1; i++) {
            Asteroide asteroide = Obstaculos.asteroides.get(i);
            for (int j = i + 1; j < Obstaculos.asteroides.size(); j++) {
                Asteroide asteroideAComparar = Obstaculos.asteroides.get(j);
                boolean collision = checkCollision(asteroide.asteroideObject, asteroideAComparar.asteroideObject);
                if (collision) {
                    asteroide.aplicarColision();
                    asteroideAComparar.aplicarColision();
                }
            }
        }
    }

    public static void verificarColisionBalas(Sound soundExplosion){
        for (int i = 0; i < NaveEspacial.balas.size() - 1; i++) {
            Bala bala = NaveEspacial.balas.get(i);
            for (int j = 0; j < Obstaculos.asteroides.size(); j++) {
                Asteroide asteroideAComparar = Obstaculos.asteroides.get(j);
                boolean collision = checkCollision(bala.balaObject, asteroideAComparar.asteroideObject);
                if (collision) {
                    soundExplosion.play(0.2f);
                    NaveEspacial.balas.remove(bala);
                    Obstaculos.asteroides.remove(asteroideAComparar);
                }
            }
        }
    }

    public static void verificarColisionNave(Sound soundOver, Sound golpe){
            for (int j = 0; j < Obstaculos.asteroides.size(); j++) {
                Asteroide asteroideAComparar = Obstaculos.asteroides.get(j);
                boolean collision = checkCollision(NaveEspacial.naveObject, asteroideAComparar.asteroideObject);
                if (collision) {
                    golpe.play(0.3f);
                    Actualizar.tiempoInmunidad = System.currentTimeMillis();
                    if (NaveEspacial.vida == 1) {
                        soundOver.play(0.5f);
                        NaveEspacial.vida--;
                        NaveEspacial.gameOver();
                    } else {
                        NaveEspacial.vida--;
                    }
                }
            }
        }
}
