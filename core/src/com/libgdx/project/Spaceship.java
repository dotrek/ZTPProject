package com.libgdx.project;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by dotre on 04.11.2017.
 */
public class Spaceship extends Actor {
    Vector2 bulletVelocity = new Vector2(20f, 0f);
    Sprite spaceshipSprite;
    int health;
    float speed;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Spaceship() {
        this.spaceshipSprite = new Sprite();
        setBounds(0, 0, 64, 64);
        this.health = 10;
        speed = 10f;
        this.bullets = new ArrayList<Bullet>();
    }

    public Spaceship(Sprite spaceshipSprite, int health) {
        this.spaceshipSprite = spaceshipSprite;
        setBounds(0, 0, spaceshipSprite.getWidth(), spaceshipSprite.getHeight());
        this.health = health;
        this.bullets = new ArrayList<Bullet>();
    }


    private void update() {
        this.spaceshipSprite.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        batch.draw(spaceshipSprite, this.getX(), this.getY(), getWidth(), getHeight());
    }
}
