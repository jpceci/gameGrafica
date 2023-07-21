package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;

import java.util.List;

public class Bala extends Actors {
    public Model model;
    public ModelInstance instance;
    public boolean marcar;
    public btCollisionShape balaShape;
    public btCollisionObject balaObject;


    public Bala(Vector3 posicionNave, Model modelBala) {
        model = modelBala;
        instance = new ModelInstance(model);
        float scaleFactor = 0.05f;
        instance.transform.scl(scaleFactor);
        instance.transform.rotate(1, 0, 0, 90);
        instance.transform.setTranslation(posicionNave);
        marcar = false;

        balaShape =  new btSphereShape(0.8f);
        balaObject = new btCollisionObject();
        balaObject.setCollisionShape(balaShape);
        balaObject.setCollisionFlags(balaObject.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
        balaObject.setWorldTransform(instance.transform);
    }

    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        instance.transform.trn(0,0,0.5f);
        balaObject.setWorldTransform(instance.transform);
        verificarLimite();
        batch.render(instance);
        batch.end();
    }

    private void verificarLimite() {
        Vector3 posicion = new Vector3();
        instance.transform.getTranslation(posicion);
         if (posicion.z == 50)
             marcar = true;
    }

    @Override
    protected void act() {

    }

    @Override
    public void dispose(){
        model.dispose();
        balaObject.dispose();
        balaShape.dispose();
    }
}
