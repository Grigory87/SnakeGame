package ru.grinders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import ru.grinders.controller.GameStateManager;
import ru.grinders.states.MenuScreen;

public class SnakeGame extends ApplicationAdapter {
	private GameStateManager gsm;

	@Override
	public void create() {
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0,0,0,1);
		gsm.pushState(new MenuScreen(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}

	@Override
	public void dispose() {
		gsm.dispose();
	}
}
