package com.libgdx.project.actors;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dotre on 29.11.2017.
 */
public class BlueEnemy extends Enemy {

    public BlueEnemy(Texture texture) {
        super();
        spaceshipSprite.setTexture(texture);
    }

    @Override
    public void update(float delta) {
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        distanceToPlayer = (float) Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
    }
}
