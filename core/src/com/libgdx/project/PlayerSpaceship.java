package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dotre on 24.10.2017.
 */

public class PlayerSpaceship extends Spaceship {
    private static PlayerSpaceship instance;
    static final float ACCELERATION_X = 50f;
    static final float ACCELERATION_Y = 50f;
    private static final float MAX_VELOCITY = 300f;
    private static final Texture texture = new Texture("spaceship.png");

    private Vector2 velocity;
    Vector2 acceleration;
    Vector2 position;

    Sound sound;

    public Vector2 getPosition() {
        return position;
    }

    public enum MoveStateX {
        LEFT, RIGHT, STOP
    }

    public enum MoveStateY {
        UP, DOWN, STOP
    }

    MoveStateX moveStateX = MoveStateX.STOP;
    MoveStateY moveStateY = MoveStateY.STOP;

    private PlayerSpaceship() {
        this.spaceshipSprite = new Sprite(texture);
        this.spaceshipSprite.setSize(64f, 64f);
        health = 10;
        sound = Gdx.audio.newSound(new FileHandle("ciu.mp3"));

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        position = new Vector2(spaceshipSprite.getX(), spaceshipSprite.getY());

    }

    public static PlayerSpaceship getInstance() {
        if (instance == null) {
            instance = new PlayerSpaceship();
        }
        return instance;
    }

    private void checkBorders() {
        if (position.x > Gdx.graphics.getWidth() - spaceshipSprite.getWidth()) {
            position.x = Gdx.graphics.getWidth() - spaceshipSprite.getWidth();
            velocity.x = 0f;
        }
        if (position.x < 0) {
            position.x = 0;
            velocity.x = 0f;
        }
        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0;
        }
        if (position.y > Gdx.graphics.getHeight() - spaceshipSprite.getHeight()) {
            position.y = Gdx.graphics.getHeight() - spaceshipSprite.getHeight();
            velocity.y = 0;
        }
    }

    public void update(float delta) {
        checkBorders();

        if ((moveStateX == MoveStateX.LEFT || moveStateX == MoveStateX.RIGHT) && velocity.x < MAX_VELOCITY) {
            velocity.add(acceleration.cpy().scl(delta));
        }
        if ((moveStateY == MoveStateY.UP || moveStateY == MoveStateY.DOWN) && velocity.y < MAX_VELOCITY) {
            velocity.add(acceleration.cpy().scl(delta));
        }
        if ((moveStateY == MoveStateY.STOP && velocity.y > 0) && velocity.y > -MAX_VELOCITY) {
            velocity.y += -ACCELERATION_Y * delta;
        }
        if ((moveStateY == MoveStateY.STOP && velocity.y < 0) && velocity.y < MAX_VELOCITY) {
            velocity.y += ACCELERATION_Y * delta;
        }
        if ((moveStateX == MoveStateX.STOP && velocity.x > 0) && velocity.x > -MAX_VELOCITY) {
            velocity.x += -ACCELERATION_X * delta;
        }
        if ((moveStateX == MoveStateX.STOP && velocity.x < 0) && velocity.x < MAX_VELOCITY) {
            velocity.x += ACCELERATION_X * delta;
        }
        position.add(velocity.cpy().scl(delta));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update(Gdx.graphics.getDeltaTime());
        batch.draw(spaceshipSprite, position.x, position.y, spaceshipSprite.getWidth(), spaceshipSprite.getHeight());
    }

}
