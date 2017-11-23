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
}
