package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisProgressBar;

import java.util.ArrayList;

/**
 * Created by dotre on 04.11.2017.
 */
public class Spaceship extends Actor {

    public Sprite spaceshipSprite;
    public int health;

    VisProgressBar healthBar;
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

    private void update() {
        this.spaceshipSprite.setPosition(getX(), getY());
        this.setOrigin(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
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
