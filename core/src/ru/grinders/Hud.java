package ru.grinders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Hud
 *
 * @author Grigory Dyakonov
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private static Integer score;
    private static Label scoreLabel;
    private Label counterLabel;

    public Hud(SpriteBatch batch) {
        score = 0;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
//        table.setDebug(true);

        counterLabel = new Label("Score: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(counterLabel);
        table.add(scoreLabel);
        stage.addActor(table);
    }

    public static void incrementScore() {
        score++;
        scoreLabel.setText(String.format("%04d", score));
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
