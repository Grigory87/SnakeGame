package ru.grinders.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ru.grinders.Direction;
import ru.grinders.Hud;
import ru.grinders.PartSnake;
import ru.grinders.SnakeGame;
import ru.grinders.controller.GameStateManager;

/**
 * GameScreen
 *
 * @author Grigory Dyakonov
 */

public class GameScreen extends State {
    private Hud hud;
    ShapeRenderer shapeRenderer;
    Array<PartSnake> snake;
    Array<Rectangle> points;
    float second = 0;
    boolean hasCrossed;
    private Sound sound;
    private Music music;

    public GameScreen(GameStateManager gsm) {
        super(gsm);
        snake = new Array<>();
        points = new Array<>(1);
        shapeRenderer = new ShapeRenderer();
        initSnake();
        hud = new Hud(gsm.getBatch());
        hasCrossed = false;
        sound = Gdx.audio.newSound(Gdx.files.internal("music/sound_eat.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music_game.mp3"));
        music.setLooping(true);
        music.play();
    }

    private void initSnake() {
        snake.add(new PartSnake(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), new Rectangle(), Direction.RIGHT));
        snake.add(new PartSnake(new Vector2(Gdx.graphics.getWidth() / 2 - 5, Gdx.graphics.getHeight() / 2), new Rectangle(), Direction.NONE));
        snake.add(new PartSnake(new Vector2(Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 2), new Rectangle(), Direction.NONE));
        snake.add(new PartSnake(new Vector2(Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() / 2), new Rectangle(), Direction.NONE));
    }

    private void createPoints() {
        points.add(new Rectangle(MathUtils.random(0, 99) * 5, MathUtils.random(0,99) * 5, 5, 5));
    }

    private void renderSnake() {
        for(PartSnake part : snake) {
            Rectangle rect = part.getRectangle();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(part.getPosition().x, part.getPosition().y, rect.width, rect.height);
            shapeRenderer.end();
        }
    }

    private void renderPoints() {
        for(Rectangle point : points) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(point.x, point.y, point.width, point.height);
            shapeRenderer.end();
        }
    }

    @Override
    public void update(float deltaTime) {
        if (points.size == 0) {
            createPoints();
        }
        second += deltaTime;
        handleInput();
        if (second > GameStateManager.gameSpeed) {
            moveSnake();
            second = 0;
        }
    }

    private void moveSnake() {
        Vector2 headPosition = new Vector2();
        PartSnake head = snake.get(0);
        headPosition.set(head.getPosition());
        Vector2 savePositionBody = new Vector2();
        for (int i = 0; i < snake.size; i++) {
            PartSnake part = snake.get(i);
            Direction direction = part.getDirection();
            if (direction != Direction.NONE) {
                if (isPossible(part)) {
                    if (direction == Direction.RIGHT) {
                        part.setPosition(headPosition.x + 5, headPosition.y);
                        checkCrossedPoints(part);
                        if (checkCollision(part)) {
                            gsm.setState(new GameOver(gsm));
                        }
                    } else if (direction == Direction.LEFT) {
                        part.setPosition(headPosition.x - 5, headPosition.y);
                        checkCrossedPoints(part);
                        if (checkCollision(part)) {
                            gsm.setState(new GameOver(gsm));
                        }
                    } else if (direction == Direction.UP) {
                        part.setPosition(headPosition.x, headPosition.y + 5);
                        checkCrossedPoints(part);
                        if (checkCollision(part)) {
                            gsm.setState(new GameOver(gsm));
                        }
                    } else if (direction == Direction.DOWN) {
                        part.setPosition(headPosition.x, headPosition.y - 5);
                        checkCrossedPoints(part);
                        if (checkCollision(part)) {
                            gsm.setState(new GameOver(gsm));
                        }
                    }
                } else {
                    gsm.setState(new GameOver(gsm));
                }
            } else {
                savePositionBody.set(part.getPosition());
                part.setPosition(headPosition.x, headPosition.y);
                headPosition.set(savePositionBody);
            }
        }
        if (hasCrossed) {
            snake.add(new PartSnake(new Vector2(headPosition.x, headPosition.y), new Rectangle(), Direction.NONE));
            points.removeIndex(0);
            hasCrossed = false;
        }
    }

    private boolean checkCollision(PartSnake part) {
        for (int i = 1; i < snake.size; i++) {
            PartSnake partSnake = snake.get(i);
            if (part.getPosition().x == partSnake.getPosition().x && part.getPosition().y == partSnake.getPosition().y) {
                return true;
            }
        }
        return false;
    }

    private void checkCrossedPoints(PartSnake part) {
        if (part.getPosition().x == points.get(0).x && part.getPosition().y == points.get(0).y) {
            hasCrossed = true;
            Hud.incrementScore();
            sound.play();
        }
    }

    private boolean isPossible(PartSnake part) {
        Direction direction = part.getDirection();
        if (direction == Direction.RIGHT) {
            return part.getPosition().x < Gdx.graphics.getWidth() - 5;
        }
        if (direction == Direction.LEFT) {
            return part.getPosition().x > 5;
        }
        if (direction == Direction.UP) {
            return part.getPosition().y < Gdx.graphics.getHeight() - 5;
        }
        if (direction == Direction.DOWN) {
            return part.getPosition().y > 5;
        }
        return false;
    }

    @Override
    public void handleInput() {
        PartSnake head = snake.get(0);
        Direction headDirection = head.getDirection();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (headDirection != Direction.UP && headDirection != Direction.DOWN) {
                head.setDirection(Direction.UP);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (headDirection != Direction.DOWN && headDirection != Direction.UP) {
                head.setDirection(Direction.DOWN);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (headDirection != Direction.LEFT && headDirection != Direction.RIGHT) {
                head.setDirection(Direction.LEFT);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (headDirection != Direction.RIGHT && headDirection != Direction.LEFT) {
                head.setDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        renderSnake();
        renderPoints();
        batch.end();

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        sound.dispose();
        music.dispose();
    }
}
