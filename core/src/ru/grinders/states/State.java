package ru.grinders.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.grinders.controller.GameStateManager;

/**
 * State
 *
 * @author Grigory Dyakonov
 */

public abstract class State {
    protected Vector2 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        mouse = new Vector2();
    }

    protected abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
