package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dotre on 27.11.2017.
 */
public class RedEnemy extends Enemy {
    Texture texture = new Texture(Gdx.files.internal("spaceshipred.png"));

    public RedEnemy() {
        super();
        spaceshipSprite.setTexture(texture);
        speed *= 3f;
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
