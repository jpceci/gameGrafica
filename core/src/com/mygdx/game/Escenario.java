package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Escenario extends Stage {
    SpriteBatch batch;
    private Stage stage;
    private Texture img;

    public Escenario() {

        stage = new Stage();
        batch = new SpriteBatch();
        Actors actor = new Actors();
        actor.setPosition(10,10);
        stage.addActor(actor);
        img = new Texture(Gdx.files.internal("space.png"));
    }

    public void act(){
        stage.act();
    }

    public void draw(){
        batch.begin();
        batch.draw(img, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }

    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
