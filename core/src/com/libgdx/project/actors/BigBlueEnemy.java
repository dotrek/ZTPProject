package com.libgdx.project.actors;

import com.kotcrab.vis.ui.widget.VisProgressBar;

/**
 * Created by dotre on 23.11.2017.
 */
public class BigBlueEnemy extends Enemy {
    public BigBlueEnemy() {
        super();
        setSize(getWidth() * 3, getHeight() * 3);
        spaceshipSprite.setSize(getWidth(), getHeight());
        health = 30;
        healthBar = new VisProgressBar(0, health, 1f, false);
    }

    @Override
    public void update(float delta) {
//        System.out.println(playerInstance.getX() + "\t" + playerInstance.getY() + "\t \t " + getX() + "  " + getY());
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        distanceToPlayer = (float) Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
    }
}
