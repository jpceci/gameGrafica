package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends ApplicationAdapter {
	private Escenario escenario;

	@Override
	public void create () {
		escenario = new Escenario();

	}


	@Override
	public void render () {
		escenario.draw();
		escenario.act();
	}
	
	@Override
	public void dispose () {
	    escenario.dispose();
		super.dispose();
	}
}
