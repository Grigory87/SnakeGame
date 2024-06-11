package ru.grinders;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Snake
 *
 * @author Grigory Dyakonov
 */

public class PartSnake {
    private Vector2 position;
    private Rectangle rectangle;
    private Direction direction;

    public PartSnake(Vector2 position, Rectangle rectangle, Direction direction) {
        this.position = position;
        this.rectangle = rectangle;
        this.rectangle.setPosition(position.x, position.y);
        this.rectangle.setSize(5, 5);
        this.direction = direction;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
}
