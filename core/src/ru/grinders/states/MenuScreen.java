package ru.grinders.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.grinders.controller.GameStateManager;

/**
 * MenuScreen
 *
 * @author Grigory Dyakonov
 */

public class MenuScreen extends State {
    private Texture texture;
    private Texture buttonPlay;
    private Texture buttonSettings;
    private Music music;

    public MenuScreen(GameStateManager gsm) {
        super(gsm);
        texture = new Texture("screensaver.jpg");
        buttonPlay = new Texture("play.png");
        buttonSettings = new Texture("setting.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music_screensaver.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > 30 && Gdx.input.getX() < 160
                    && Gdx.graphics.getHeight() - Gdx.input.getY() > 30
                    && Gdx.graphics.getHeight() - Gdx.input.getY() < 80) {
                gsm.setState(new GameScreen(gsm));
            }
            if (Gdx.input.getX() > 340 && Gdx.input.getX() < 470
                    && Gdx.graphics.getHeight() - Gdx.input.getY() > 30
                    && Gdx.graphics.getHeight() - Gdx.input.getY() < 80) {
                gsm.setState(new SettingsScreen(gsm));
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(buttonPlay, 30, 30, 130, 50);
        batch.draw(buttonSettings, 340, 30, 130, 50);
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
        music.dispose();
    }
}
