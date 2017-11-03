package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by dotre on 24.10.2017.
 */

public class PlayerSpaceship extends Actor {
    private static final float ACCELERATION_X = 50f;
    private static final float ACCELERATION_Y = 50f;
    private static final float MAX_VELOCITY = 50f;
    private static float speed = 10;
    private Vector2 velocity, acceleration, position;
    private static final Vector2 bulletVelocity = new Vector2(20f, 0f);
    public Sprite playerSprite;

    private int health;
    Sound sound;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Vector2 getPosition() {
        return position;
    }

    private enum MoveStateX {
        LEFT, RIGHT, STOP
    }

    private enum MoveStateY {
        UP, DOWN, STOP
    }

    MoveStateX movestate = MoveStateX.STOP;
    MoveStateY moveStateY = MoveStateY.STOP;

    PlayerSpaceship(Sprite playerSprite) {
        this.playerSprite = playerSprite;
        health = 10;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        position = new Vector2(playerSprite.getX(), playerSprite.getY());

        this.playerSprite.setSize(64f, 64f);
        sound = Gdx.audio.newSound(new FileHandle("ciu.mp3"));
    }

    public void addHealth(int healthAdded) {
        this.health += healthAdded;
    }

    public int getHealth() {
        return health;
    }

    public static float getSpeed() {
        return speed;
    }

    //TODO: Change this method to input processor type
    public void moveController() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            movestate = MoveStateX.STOP;
            moveStateY = MoveStateY.STOP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveStateY = MoveStateY.UP;
            acceleration.set(0, ACCELERATION_Y);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveStateY = MoveStateY.DOWN;
            acceleration.set(0, -ACCELERATION_Y);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movestate = MoveStateX.LEFT;
            acceleration.set(-ACCELERATION_X, 0);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movestate = MoveStateX.RIGHT;
            acceleration.set(ACCELERATION_X, 0);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bullet bullet = new Bullet(new Vector2(position.x, position.y + playerSprite
                    .getHeight() / 2f),
                    bulletVelocity);
            bullets.add(bullet);
            sound.play();
        }
    }

    private void checkBorders() {
        if (position.x > Gdx.graphics.getWidth() - playerSprite.getWidth()) {
            position.x = Gdx.graphics.getWidth() - playerSprite.getWidth();
        }
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > Gdx.graphics.getHeight() - playerSprite.getHeight()) {
            position.y = Gdx.graphics.getHeight() - playerSprite.getHeight();
        }
    }

    public void update(float delta) {
        checkBorders();
        if (movestate == MoveStateX.LEFT || movestate == MoveStateX.RIGHT) {
            velocity.add(acceleration.cpy().scl(delta));
            position.add(velocity.cpy().scl(delta));
        }
        if (moveStateY == MoveStateY.UP || moveStateY == MoveStateY.DOWN) {
            velocity.add(acceleration.cpy().scl(delta));
            position.add(velocity.cpy().scl(delta));
        }
        if (moveStateY == MoveStateY.STOP && velocity.y > 0) {
            velocity.y += -ACCELERATION_Y * delta;
        }
        if (moveStateY == MoveStateY.STOP && velocity.y < 0) {
            velocity.y += ACCELERATION_Y * delta;
        }
        if (movestate == MoveStateX.STOP && velocity.x > 0) {
            velocity.x += -ACCELERATION_X * delta;
        }
        if (movestate == MoveStateX.STOP && velocity.x < 0) {
            velocity.x += ACCELERATION_X * delta;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        moveController();
        update(Gdx.graphics.getDeltaTime());
        batch.draw(playerSprite, position.x, position.y, playerSprite.getWidth(), playerSprite.getHeight());
    }

}
