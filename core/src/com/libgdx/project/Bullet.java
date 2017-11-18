package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by dotre on 24.10.2017.
 */
public class Bullet extends Actor {
    public Vector2 bulletLocation;
    private Vector2 bulletVelocity;
    private static Texture bulletTexture;
    public Sprite bulletSprite;
    private int damage;
    private static float speed = 15f;

    public Bullet(Vector2 sentLocation, Vector2 sentVelocity) {

        bulletTexture = new Texture("bullet.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(32, 16);
        bulletLocation = new Vector2(sentLocation.x, sentLocation.y - bulletSprite.getHeight() / 2);
        bulletVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
        damage = 2;
    }

    public void update() {
        bulletLocation.x += getBulletVelocity().x * Gdx.graphics.getDeltaTime() * speed;
    }

    public Vector2 getBulletVelocity() {
        return bulletVelocity;
    }

    public Sprite getBulletSprite() {
        return bulletSprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        bulletSprite.setPosition(bulletLocation.x, bulletLocation.y);
        bulletSprite.draw(batch, parentAlpha);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
