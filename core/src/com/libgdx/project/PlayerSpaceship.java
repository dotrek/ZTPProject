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
    Vector2 temp;

    Sound sound;

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

    MoveStateX moveStateX = MoveStateX.STOP;
    MoveStateY moveStateY = MoveStateY.STOP;

    private PlayerSpaceship() {
        super();
        this.spaceshipSprite = new Sprite(texture);
        this.spaceshipSprite.setSize(64f, 64f);
        setPosition(Gdx.graphics.getWidth()/2f-spaceshipSprite.getWidth()/2f,Gdx.graphics.getHeight()
                /2f-spaceshipSprite.getHeight()/2f);
        spaceshipSprite.setOrigin(spaceshipSprite.getWidth() / 2f, spaceshipSprite.getHeight() / 2f);
        health = 10;
        sound = Gdx.audio.newSound(new FileHandle("ciu.mp3"));

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

    public void update(float delta) {
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update(Gdx.graphics.getDeltaTime());
//        System.out.println(getX() + "\t\t" + getY());
//        batch.draw(spaceshipSprite, getX(), getY(), spaceshipSprite.getWidth(), spaceshipSprite.getHeight());
        spaceshipSprite.draw(batch, parentAlpha);
    }

}
