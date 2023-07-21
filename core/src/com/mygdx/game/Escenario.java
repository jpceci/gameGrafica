package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Escenario  {
    public  NaveEspacial nave;
    private final ModelBatch modelBatch;
    private final PerspectiveCamera cam;
    private final CameraInputController camController;
    public Obstaculos obstaculos;
    private SpriteBatch spriteBatch;
    public Texture texture;

    public Escenario() {

        obstaculos = new Obstaculos();
        nave = new NaveEspacial();
        modelBatch = new ModelBatch();
        cam = new PerspectiveCamera(40, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 5f, -10f);
        cam.lookAt(0, 2, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        camController = new CameraInputController(cam);
        spriteBatch = new SpriteBatch();
        texture = new Texture("over.png");


    }

    public void act(){
        nave.act();
    }

    public void draw(){

        if (NaveEspacial.vida == 0) {
            spriteBatch.begin();
            spriteBatch.draw(texture, 0, 0, 700, 500);
            spriteBatch.end();
        } else {
            camController.update();
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            nave.draw(modelBatch,cam);
            obstaculos.draw(modelBatch,cam);
            modelBatch.end();
        }

    }

    public void dispose () {
        modelBatch.dispose();
        nave.dispose();
        obstaculos.dispose();
        spriteBatch.dispose();
    }
}
