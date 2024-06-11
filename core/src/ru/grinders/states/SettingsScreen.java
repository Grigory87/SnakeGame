package ru.grinders.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.grinders.controller.GameStateManager;

/**
 * SettingsScreen
 *
 * @author Grigory Dyakonov
 */

public class SettingsScreen extends State {
//    private Stage stage;
//    private Viewport viewport;
    private Texture texture;
    private Texture speedLabel;
    private Texture changeSpeedLabel;
    private Table table;
//    private Image image;

    public SettingsScreen(GameStateManager gsm) {
        super(gsm);
//        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
//        stage = new Stage(viewport, gsm.getBatch());
        texture = new Texture("screensaver.jpg");
        speedLabel = new Texture("speed_label.png");
        changeSpeedLabel = new Texture("label.png");
//        image = new Image(speedLabel);

//        table = new Table();
//        table.center();
//        table.setFillParent(true);
//
//        table.add(image);
//        stage.addActor(table);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(speedLabel, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 1.2f - speedLabel.getHeight() / 2, 100, 30);
        batch.draw(changeSpeedLabel, Gdx.graphics.getWidth() / 4f + 100, Gdx.graphics.getHeight() / 1.2f - changeSpeedLabel.getHeight() / 2, 200, 30);
        batch.end();
    }

    @Override
    public void dispose() {
//        stage.dispose();
        texture.dispose();
        speedLabel.dispose();
    }
}
