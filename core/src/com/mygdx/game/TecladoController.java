package com.mygdx.game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

public class TecladoController implements InputProcessor {

    private final int[] teclas = {Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT, Input.Keys.SPACE};
    public static boolean[] estados = {false,false,false,false,false};
    private final Thread actualizar;
    private static Sound soundtrack;




    public TecladoController(){
        soundtrack = Gdx.audio.newSound(Gdx.files.internal(Sonidos.soundtrack));
        Gdx.input.setInputProcessor(this);
        actualizar = new Thread(new Actualizar());
        actualizar.start();
        soundtrack.play(0.1f);

    }

    void dispose(){
        soundtrack.stop();
       actualizar.stop();

    }

    @Override
    public boolean keyDown(int keycode) {
           for (int i = 0; i < teclas.length; i++){
               if (keycode == teclas[i]) {
                   estados[i] = true;
               }
           }
            return true;
        }

    @Override
    public boolean keyUp(int keycode) {
        for (int i = 0; i < teclas.length; i++){
            if (keycode == teclas[i]) {
                estados[i] = false;
            }
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
