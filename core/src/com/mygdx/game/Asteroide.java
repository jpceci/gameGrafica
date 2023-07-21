package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g3d.model.Node;

import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;

public class Asteroide extends Actors{

    public Model modelAsteroide;
    public ModelInstance instance;

    public boolean marcar;
    public Environment environment;
    public float pendienteX, pendienteY, pendienteZ;
    public btCollisionShape asteroideShape;
    public btCollisionObject asteroideObject;


    //public Asteroide(Model model, float x1, float x2, float y1, float y2){
        public Asteroide(Model model){
        float randomX = generarRandom(10, -10f);
        float randomY = generarRandom(8, -4);
        float randomX2 = generarRandom(10f, -10f);
        float randomY2 = generarRandom(8, -4);

//        float randomX = x1;
//        float randomY = y1;
//        float randomX2 = x2;
//        float randomY2 = y2;

        calcularPendiente(randomX, randomX2, randomY, randomY2);
        Vector3 posicionRandom = new Vector3(randomX,randomY,50);
        modelAsteroide = model;
        instance = new ModelInstance(modelAsteroide);
        instance.transform.scl(0.02f);
        instance.transform.setTranslation(posicionRandom);
        marcar = false;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        asteroideShape =  new btSphereShape(0.8f);
        asteroideObject = new btCollisionObject();
        asteroideObject.setCollisionShape(asteroideShape);
        asteroideObject.setCollisionFlags(asteroideObject.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
        asteroideObject.setWorldTransform(instance.transform);

    }
    private void calcularPendiente(float x1, float x2, float y1, float y2) {
        //float velocidad = 400;
        float velocidad = generarRandom(600, 300);
        float diferencialX = x2 - x1;
        float diferencialY = y2- y1;
        float diferencialZ = -5 - 50;
        pendienteX = diferencialX/velocidad;
        pendienteY = diferencialY/velocidad;
        pendienteZ = diferencialZ/velocidad;
    }

    public void aplicarColision(){
        pendienteX = - pendienteX;
        mover();
    }

    @Override
    public void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        mover();
        verificarLimite();

        batch.render(instance,environment);
        batch.end();
    }

    @Override
    protected void act() {

    }
    private float generarRandom(float max, float min) {
        return (float) (Math.random()* (max - min) + min);
    }
    private void mover(){
        instance.transform.trn(pendienteX,pendienteY,pendienteZ);
        asteroideObject.setWorldTransform(instance.transform);
        instance.transform.rotate(1, 0, 0, 3f);
    }
    private void verificarLimite() {
        Vector3 posicion = new Vector3();
        instance.transform.getTranslation(posicion);
        if (posicion.z < -5)
            marcar = true;
    }
    @Override
    protected void dispose() {
        asteroideObject.dispose();
        asteroideShape.dispose();
        modelAsteroide.dispose();
    }
}
