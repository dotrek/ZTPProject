package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import java.awt.geom.Arc2D;
import java.util.Random;

/**
 * Created by dotre on 23.11.2017.
 */
public class GreenEnemy extends Enemy {
    Texture texture = new Texture(Gdx.files.internal("spaceshipgreen.png"));
    Random random = new Random();
    double alfa;

    public GreenEnemy() {
        super();
        spaceshipSprite.setTexture(texture);
        alfa = 0;
    }

    @Override
    public void update(float delta) {
//        System.out.println(playerInstance.getX() + "\t" + playerInstance.getY() + "\t \t " + getX() + "  " + getY());
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        alfa = MathUtils.radiansToDegrees * MathUtils.atan2(moveVelocity.y, moveVelocity.x);
        distanceToPlayer = (float) Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        if (distanceToPlayer <= getWidth() * 5f) {
            this.setPosition(getX() + moveVelocity.x * 2 * speed * delta, getY() + moveVelocity.y * 2 * speed * delta);
        } else {
            this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
        }
    }
}
