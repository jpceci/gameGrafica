package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class Bala extends Actors {
    public Model model;
    public ModelInstance instance;


    public Bala(Vector3 posicionNave) {
        ObjLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("bala.obj"));
        instance = new ModelInstance(model);

        float scaleFactor = 0.05f;
        instance.transform.scl(scaleFactor);
        instance.transform.rotate(1, 0, 0, 90);
        instance.transform.setTranslation(posicionNave);
    }

    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(instance);
        batch.end();
    }

    @Override
    protected void act() {

    }

    @Override
    public void dispose(){
        model.dispose();
    }
}
