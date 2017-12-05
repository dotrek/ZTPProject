package com.libgdx.project.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by dotre on 23.11.2017.
 */
public class GreenEnemy extends Enemy {
    double alfa;

    public GreenEnemy(Texture texture) {
        super();
        spaceshipSprite.setTexture(texture);
        alfa = 0;
    }

    @Override
    public void update(float delta) {
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
