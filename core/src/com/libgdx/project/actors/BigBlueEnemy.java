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
}
