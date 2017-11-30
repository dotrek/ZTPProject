package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dotre on 23.11.2017.
 */
public class PurpleEnemy extends Enemy {
    Texture texture = new Texture(Gdx.files.internal("spaceshippurple.png"));

    public PurpleEnemy() {
        super();
        spaceshipSprite.setTexture(texture);
    }

    @Override
    public void update(float delta) {
//        System.out.println(playerInstance.getX() + "\t" + playerInstance.getY() + "\t \t " + getX() + "  " + getY());
        movement(delta);
    }

    private void movement(float delta) {
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        distanceToPlayer = (float) Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
    }
    private void shooting(float delta){

    }
}
