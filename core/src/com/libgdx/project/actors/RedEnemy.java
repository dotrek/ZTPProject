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
    }
}
