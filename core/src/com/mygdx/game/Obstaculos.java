package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.CollisionObjectWrapper;
import com.badlogic.gdx.physics.bullet.collision.btBoxBoxCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithm;
import com.badlogic.gdx.physics.bullet.collision.btCollisionAlgorithmConstructionInfo;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDispatcherInfo;
import com.badlogic.gdx.physics.bullet.collision.btManifoldResult;
import com.badlogic.gdx.physics.bullet.collision.btSphereSphereCollisionAlgorithm;


import java.util.ArrayList;

public class Obstaculos extends Actors {
    public static ArrayList<Asteroide> asteroides;
    public static Model model;
    public btCollisionConfiguration collisionConfig;
    public static btDispatcher dispatcher;
    public static boolean collision;
    public static btCollisionAlgorithmConstructionInfo ci;

    public Obstaculos() {
        Bullet.init();
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        collision =false;
        asteroides = new ArrayList<>();
        ObjLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("rock.obj"));
//        asteroides.add(new Asteroide(model, -5, 5, 3, -3));
//        asteroides.add(new Asteroide(model, 5, -5, 3, -3));
        ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
    }
    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {

        for (int j = 0; j < asteroides.size() ; j++) {
            Asteroide asteroide = asteroides.get(j);
            if (!asteroide.marcar)
                asteroide.draw(batch,camera);
            else {
                asteroides.remove(asteroide);
                j--;
            }
        }

    }

    @Override
    protected void act() {

    }

    public static void agregarAsteroide(){

        asteroides.add(new Asteroide(model));
    }

    @Override
    protected void dispose() {
        for (Asteroide asteroide:asteroides) {
            asteroide.dispose();
        }
        model.dispose();
        collisionConfig.dispose();
        ci.dispose();
        dispatcher.dispose();
    }
}
