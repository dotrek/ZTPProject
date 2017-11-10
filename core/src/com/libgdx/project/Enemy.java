package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(spaceshipSprite, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2);
    }
}
