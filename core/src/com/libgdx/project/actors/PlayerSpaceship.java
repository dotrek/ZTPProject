package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

/**
 * Created by dotre on 24.10.2017.
 */

public class PlayerSpaceship extends com.libgdx.project.actors.Spaceship {

    private static PlayerSpaceship instance;
    public static final float ACCELERATION_X = 50f;
    public static final float ACCELERATION_Y = 50f;
    private static final float MAX_VELOCITY = 300f;
    private static final Texture texture = new Texture("spaceship.png");

    private Vector2 velocity;
    public Vector2 acceleration;
    Vector2 temp;

    public Sound sound;

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        spaceshipSprite.setRotation(getRotation());
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    public enum MoveStateX {
        LEFT, RIGHT, STOP
    }

    public enum MoveStateY {
        UP, DOWN, STOP
    }

    public MoveStateX moveStateX = MoveStateX.STOP;
    public MoveStateY moveStateY = MoveStateY.STOP;

    private PlayerSpaceship() {
        super();
        this.spaceshipSprite = new Sprite(texture);
        this.spaceshipSprite.setSize(Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        setPosition(Gdx.graphics.getWidth() / 2f - spaceshipSprite.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - spaceshipSprite.getHeight() / 2f);
        spaceshipSprite.setOrigin(spaceshipSprite.getWidth() / 2f, spaceshipSprite.getHeight() / 2f);
        health = 10;
        sound = Gdx.audio.newSound(Gdx.files.internal("ciu.mp3"));

        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        temp = new Vector2();

    }

    public static PlayerSpaceship getInstance() {
        if (instance == null) {
            instance = new PlayerSpaceship();
        }
        return instance;
    }

    private void checkBorders() {
        if (getX() > Gdx.graphics.getWidth() - spaceshipSprite.getWidth()) {
            setX(Gdx.graphics.getWidth() - spaceshipSprite.getWidth());
            velocity.x = 0f;
        }
        if (getX() < 0) {
            setX(0);
            velocity.x = 0f;
        }
        if (getY() < 0) {
            setY(0);
            velocity.y = 0;
        }
        if (getY() > Gdx.graphics.getHeight() - spaceshipSprite.getHeight()) {
            setY(Gdx.graphics.getHeight() - spaceshipSprite.getHeight());
            velocity.y = 0;
        }
    }

    public void desktopMovement(float delta) {
        checkBorders();
        spaceshipSprite.setPosition(getX(), getY());
        spaceshipSprite.setRotation(getRotation());

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
        temp = getPosition();
        temp.add(velocity.cpy().scl(delta));
        setPosition(temp.x, temp.y);
    }

    private void androidMovement() {
        checkBorders();
        float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();
        setPosition(getX() + accelY, getY() - accelX);
    }

    private void shooting() {
        for (int i = 0; i < getBullets().size(); i++) {
            Bullet b = getBullets().get(i);
            getStage().addActor(b);
            if (b.getX() <= -50 || b.getX() >= Gdx.graphics.getWidth() + 50 || b.getY() <= -50 || b
                    .getY() >= Gdx.graphics.getHeight() + 50) {
                b.remove();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        desktopMovement(Gdx.graphics.getDeltaTime());
        androidMovement();
        setOrigin(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
        Gdx.app.log("spaceship origin", String.valueOf(getY()));
        shooting();
        spaceshipSprite.draw(batch, parentAlpha);
    }

    public ArrayList<Bullet> getBullets() {
        return super.bullets;
    }

}
