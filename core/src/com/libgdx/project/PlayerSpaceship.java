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
    static final float ACCELERATION_X = 50f;
    static final float ACCELERATION_Y = 50f;
    private static final float MAX_VELOCITY = 100f;
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

    MoveStateX movestate = MoveStateX.STOP;
    MoveStateY moveStateY = MoveStateY.STOP;

    PlayerSpaceship() {
        this.spaceshipSprite = new Sprite(texture);
        this.spaceshipSprite.setSize(64f, 64f);
        health = 10;
        sound = Gdx.audio.newSound(new FileHandle("ciu.mp3"));

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        position = new Vector2(spaceshipSprite.getX(), spaceshipSprite.getY());

    }


    private void checkBorders() {
        if (position.x > Gdx.graphics.getWidth() - spaceshipSprite.getWidth()) {
            position.x = Gdx.graphics.getWidth() - spaceshipSprite.getWidth();
        }
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > Gdx.graphics.getHeight() - spaceshipSprite.getHeight()) {
            position.y = Gdx.graphics.getHeight() - spaceshipSprite.getHeight();
        }
    }

    public void update(float delta) {
        checkBorders();
        if (movestate == MoveStateX.LEFT || movestate == MoveStateX.RIGHT) {
            if (velocity.x < MAX_VELOCITY)
                velocity.add(acceleration.cpy().scl(delta));
        }
        if (moveStateY == MoveStateY.UP || moveStateY == MoveStateY.DOWN) {
            if (velocity.y < MAX_VELOCITY)
                velocity.add(acceleration.cpy().scl(delta));
        }
        if (moveStateY == MoveStateY.STOP && velocity.y > 0) {
            if (velocity.y > -MAX_VELOCITY)
                velocity.y += -ACCELERATION_Y * delta;
        }
        if (moveStateY == MoveStateY.STOP && velocity.y < 0) {
            if (velocity.y < MAX_VELOCITY)
                velocity.y += ACCELERATION_Y * delta;
        }
        if (movestate == MoveStateX.STOP && velocity.x > 0) {
            if (velocity.x > -MAX_VELOCITY)
                velocity.x += -ACCELERATION_X * delta;
        }
        if (movestate == MoveStateX.STOP && velocity.x < 0) {
            if (velocity.x < MAX_VELOCITY)
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
