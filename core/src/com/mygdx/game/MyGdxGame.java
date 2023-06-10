package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private Escenario stage;

	@Override
	public void create () {
		stage = new Escenario();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		stage.act();
		stage.draw();

	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
