package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by dotre on 24.10.2017.
 */
public class Bullet extends Actor {
    public Vector2 bulletLocation;
    private Vector2 bulletVelocity;
    private Vector2 bulletDestination;
    private static Texture bulletTexture;
    public Sprite bulletSprite;
    private int damage;
    private static float speed = 20f;

    public Bullet(Vector2 sentLocation, Vector2 destination) {

        bulletTexture = new Texture("bullet.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(32, 16);
        setBounds(sentLocation.x + bulletSprite.getWidth() / 2f, sentLocation.y + bulletSprite.getHeight() / 2f,
                bulletSprite
                        .getWidth(),
                bulletSprite
                        .getHeight());
        bulletSprite.setOrigin(bulletSprite.getWidth() / 2f, bulletSprite.getHeight() / 2f);
        this.setPosition(sentLocation.x, sentLocation.y);
        bulletDestination = new Vector2(destination.x, destination.y);
        bulletVelocity = new Vector2();
        damage = 2;
    }

    public void update(float delta) {
        bulletVelocity.set(bulletDestination.x - getX(), bulletDestination.y - getY()).nor().scl(Math.min(new Vector2
                (getX(), getY()).dst(bulletDestination), speed));
        setPosition(getX() + bulletVelocity.x * speed * delta, getY() + bulletVelocity.y * speed * delta);
    }

    public Vector2 getBulletVelocity() {
        return bulletVelocity;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        update(Gdx.graphics.getDeltaTime());
        bulletSprite.setPosition(getX(), getY());
        bulletSprite.setRotation(getRotation());
        bulletSprite.draw(batch, parentAlpha);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
