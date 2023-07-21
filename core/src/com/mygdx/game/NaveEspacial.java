package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;


import java.util.ArrayList;

public class NaveEspacial extends Actors {

    public Model model;
    public static Model modelBala;
    public static ModelInstance instance;
    private static TecladoController controladorTeclado;
    public static ArrayList<Bala> balas;
    private static int i;
    public static boolean collision;
    public btCollisionShape naveShape;
    public static btCollisionObject naveObject;
    public static int vida;
    private SpriteBatch spriteBatch;
    public Texture texture;

    public NaveEspacial() {
        ObjLoader loader = new ObjLoader();
        spriteBatch = new SpriteBatch();
        texture = new Texture("heart.png");
        model = loader.loadModel(Gdx.files.internal("ship.obj"));
        modelBala = loader.loadModel(Gdx.files.internal("bala.obj"));
        instance = new ModelInstance(model);
        balas = new ArrayList<>();
        vida = 3;
        controladorTeclado = new TecladoController();
        collision = false;
        naveShape =  new btSphereShape(0.8f);
        naveObject = new btCollisionObject();
        naveObject.setCollisionShape(naveShape);
        naveObject.setCollisionFlags(naveObject.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
        naveObject.setWorldTransform(instance.transform);
        i = 0;

    }

    public static void disparar() {
        Vector3 posicionNave = new Vector3();
        instance.transform.getTranslation(posicionNave);
        Bala bala = new Bala(posicionNave, modelBala);
        balas.add(bala);
    }

    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(instance);
        batch.end();

        for (int j = 0; j < balas.size() ; j++) {
            Bala balaActual = balas.get(j);
            if (!balaActual.marcar)
                balaActual.draw(batch,camera);
            else {
                balas.remove(balaActual);
                j--;
              //  System.out.println(balas.size());
            }
        }

    }

    @Override
    public void act() {

        spriteBatch.begin();
        for (int j = 0; j < vida; j++) {
            spriteBatch.draw(texture, j * 60, 0, 70, 50);
        }
        spriteBatch.end();

    }

    public static void mover(float x, float y, float z) {
        instance.transform.trn(x,y,z);
        naveObject.setWorldTransform(instance.transform);
    }

    public static void gameOver(){
        controladorTeclado.dispose();
    }


    @Override
    public void dispose(){
        model.dispose();
        modelBala.dispose();
        controladorTeclado.dispose();
        spriteBatch.dispose();
    }


}
