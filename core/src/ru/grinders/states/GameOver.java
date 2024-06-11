package ru.grinders.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.grinders.controller.GameStateManager;

/**
 * GameOver
 *
 * @author Grigory Dyakonov
 */

public class GameOver extends State {
    private Texture texture;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        texture = new Texture("game_over.jpg");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            gsm.setState(new MenuScreen(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, 0, Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
