package com.libgdx.project.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dotre on 23.11.2017.
 */
public class GreenEnemy extends Enemy {
    Texture texture = new Texture(Gdx.files.internal("spaceshipgreen.png"));

    public GreenEnemy() {
        super();
        spaceshipSprite.setTexture(texture);
    }
}
