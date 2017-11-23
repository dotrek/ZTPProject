package com.libgdx.project.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisProgressBar;

import com.libgdx.project.Actors.Bullet;

import java.util.ArrayList;

/**
 * Created by dotre on 04.11.2017.
 */
public class Spaceship extends Actor {

    public Sprite spaceshipSprite;
    public int health;

    private VisProgressBar healthBar;
    protected float speed;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public Spaceship() {
        this.spaceshipSprite = new Sprite();
        setBounds(0, 0, Gdx.graphics.getWidth() / 12.5f, Gdx.graphics.getHeight() / 9f);
        this.health = 10;
        healthBar = new VisProgressBar(0, health, 1f, false);
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
        this.healthBar.setBounds(getX(), getY() - healthBar.getHeight() * 2, spaceshipSprite.getWidth(), 1f);
        healthBar.setValue(health);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        batch.draw(spaceshipSprite, this.getX(), this.getY(), getWidth(), getHeight());
        healthBar.draw(batch, parentAlpha);
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
