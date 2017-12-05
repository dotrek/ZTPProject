package com.libgdx.project.actors;

import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.VisProgressBar;

/**
 * Created by dotre on 23.11.2017.
 */
public class BigBlueEnemy extends BlueEnemy {

    public BigBlueEnemy(Texture texture) {
        super(texture);
        setSize(getWidth() * 3, getHeight() * 3);
        spaceshipSprite.setSize(getWidth(), getHeight());
        health = 30;
        healthBar = new VisProgressBar(0, health, 1f, false);
    }
}
