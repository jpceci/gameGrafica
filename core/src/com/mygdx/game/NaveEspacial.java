package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

public class NaveEspacial extends Actors {

    public Model model;
    public static ModelInstance instance;
    private TecladoController controladorTeclado;
    public static List<Bala> balas;

    public NaveEspacial() {

        ObjLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("ship.obj"));
        instance = new ModelInstance(model);
        balas = new LinkedList<>();
        controladorTeclado = new TecladoController();

    }

    public static void disparar() {
        Vector3 posicionNave = new Vector3();
        instance.transform.getTranslation(posicionNave);
        Bala bala = new Bala(posicionNave);
        balas.add(bala);
    }

    public static void eliminarBala(Bala bala){
        balas.remove(bala);
    }

    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(instance);
        batch.end();

        for (Bala bala: balas) {
            bala.draw(batch,camera);
        }
    }

    @Override
    public void act() {

    }

    @Override
    public void dispose(){
        model.dispose();
        controladorTeclado.dispose();
    }


}
