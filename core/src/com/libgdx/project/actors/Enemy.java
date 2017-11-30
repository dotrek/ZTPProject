package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dotre on 04.11.2017.
 */
public abstract class Enemy extends Spaceship {

    protected static Texture texture = new Texture("enemy.png");
    PlayerSpaceship playerInstance;
    Vector2 moveVelocity;
    float distanceToPlayer;

    public Enemy() {
        super();
        spaceshipSprite = new Sprite(texture);
        spaceshipSprite.setSize(Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        speed = 30f;
        moveVelocity = new Vector2();
        playerInstance = PlayerSpaceship.getInstance();
    }

    Enemy(float positionX, float positionY) {
        super();
        spaceshipSprite = new Sprite(texture);
        spaceshipSprite.setSize(Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        speed = 30f;
        moveVelocity = new Vector2();
        setPosition(positionX, positionY);
    }

    public abstract void update(float delta);

    @Override
    public void act(float delta) {
        update(delta);
        spaceshipSprite.getBoundingRectangle().set(getX(), getY(), getWidth() / 2f, getHeight() / 2f);
        super.act(delta);
    }
}
