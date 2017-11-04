package com.libgdx.project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by dotre on 04.11.2017.
 */
public class Enemy extends Spaceship {
    private static final Texture texture = new Texture("spaceship.png");

    Enemy() {
        super();
        spaceshipSprite = new Sprite(texture);
    }
}
