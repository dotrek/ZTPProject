package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dotre on 04.11.2017.
 */
public class Enemy extends Spaceship {

    protected static Texture texture = new Texture("enemy.png");
    PlayerSpaceship playerInstance;
    private Vector2 moveVelocity;
    private double distanceToPlayer;

    public Enemy() {
        super();
        spaceshipSprite = new Sprite(texture);
        spaceshipSprite.setSize(Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        speed = 30f;
        moveVelocity = new Vector2();
    }

    Enemy(float positionX, float positionY) {
        super();
        spaceshipSprite = new Sprite(texture);
        spaceshipSprite.setSize(Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        speed = 30f;
        moveVelocity = new Vector2();
        setPosition(positionX, positionY);
    }

    private void update(float delta) {
        playerInstance = PlayerSpaceship.getInstance();
//        System.out.println(playerInstance.getX() + "\t" + playerInstance.getY() + "\t \t " + getX() + "  " + getY());
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        distanceToPlayer = Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
    }

    @Override
    public void act(float delta) {
        update(delta);
        super.act(delta);
    }
}
