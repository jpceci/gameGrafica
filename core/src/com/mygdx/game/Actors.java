package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Input;

public class Actors extends Actor implements InputProcessor {
Texture image;
private boolean up, down, left, right;
private long tiempoActual, tiempoAnterior;

    public Actors() {
        this.image = (new Texture(Gdx.files.internal("plane.png")));
        Gdx.input.setInputProcessor(this);
        up = false;
        down = false;
        left = false;
        right = false;
        tiempoActual = System.currentTimeMillis();
        tiempoAnterior = System.currentTimeMillis();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),100,80);
    }

    @Override
    public void act(float delta) {
        tiempoActual = System.currentTimeMillis();
        if (tiempoActual-tiempoAnterior >= 25) {
            if (up)
                setY(getY() + 5);
            if (down)
                setY(getY() - 5);
            if (left)
                setX(getX() - 5);
            if (right)
                setX(getX() + 5);

            tiempoAnterior = tiempoActual;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                up = true;
                break;
            case Input.Keys.DOWN:
                down = true;
                break;
            case Input.Keys.LEFT:
                left = true;
                break;
            case Input.Keys.RIGHT:
                right = true;
                break;
            default: break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                up = false;
                break;
            case Input.Keys.DOWN:
                down = false;
                break;
            case Input.Keys.LEFT:
                left = false;
                break;
            case Input.Keys.RIGHT:
                right = false;
                break;
            default: break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


}
