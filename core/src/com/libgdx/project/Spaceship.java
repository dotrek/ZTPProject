package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by dotre on 04.11.2017.
 */
public class Spaceship extends Actor {
    static final Vector2 bulletVelocity = new Vector2(20f, 0f);
    public Sprite spaceshipSprite;
    public int health;
    public float speed;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Spaceship() {
        this.spaceshipSprite = new Sprite();
        this.health = 10;
        speed = 10f;
        this.bullets = new ArrayList<Bullet>();
    }

    public Spaceship(Sprite spaceshipSprite, int health) {
        this.spaceshipSprite = spaceshipSprite;
        this.health = health;
        this.bullets = new ArrayList<Bullet>();
    }


    private void update() {
        this.spaceshipSprite.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        batch.draw(spaceshipSprite, this.getX(), this.getY(), spaceshipSprite.getWidth(),
                spaceshipSprite.getHeight());
    }
}
