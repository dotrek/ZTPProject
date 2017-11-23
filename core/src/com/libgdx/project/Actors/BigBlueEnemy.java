package com.libgdx.project.Actors;

/**
 * Created by dotre on 23.11.2017.
 */
public class BigBlueEnemy extends Enemy {
    public BigBlueEnemy() {
        super();
        setSize(getWidth() * 3, getHeight() * 3);
        spaceshipSprite.setSize(getWidth(), getHeight());
        health *= 3;
    }
}
