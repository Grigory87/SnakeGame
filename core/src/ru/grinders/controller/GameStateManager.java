package ru.grinders.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.grinders.states.State;

import java.util.Stack;

/**
 * GameScreenManager
 *
 * @author Grigory Dyakonov
 */

public class GameStateManager {
    private Stack<State> states;
    private final SpriteBatch batch;
    public static float gameSpeed = 0.3f;

    public GameStateManager() {
        states = new Stack<>();
        batch = new SpriteBatch();
    }

    public void pushState(State state) {
        states.push(state);
    }

    public void setState(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render() {
        states.peek().render(batch);
    }

    public void dispose() {
        batch.dispose();
        states.peek().dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
