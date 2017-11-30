package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.project.screen.GameScreen;

/**
 * Created by dotre on 24.10.2017.
 */
public abstract class Bullet extends Actor {

    private Vector2 target;
    private static Texture bulletTexture = new Texture("bullet.png");
    public Sprite bulletSprite;
    private int damage;
    float velx, vely;
    float speed = 200f;

    public Bullet(Vector2 sentLocation, Vector2 destination) {

        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(32, 16);
        bulletSprite.getBoundingRectangle().setSize(32, 16);
        setBounds(sentLocation.x + bulletSprite.getWidth() / 2f, sentLocation.y + bulletSprite.getHeight() / 2f,
                bulletSprite.getWidth(), bulletSprite.getHeight());
        bulletSprite.setOrigin(bulletSprite.getWidth() / 2f, bulletSprite.getHeight() / 2f);
        this.setPosition(sentLocation.x, sentLocation.y);
        target = new Vector2(destination.x, destination.y);

//        bulletVelocity = new Vector2();
        damage = 2;
        setBulletDestination();
    }

    public void update(float delta) {
//        bulletVelocity.set(target.x - getX(), target.y - getY()).nor().scl(Math.min(new Vector2
//                (getX(), getY()).dst(target), speed));
        System.out.println(bulletSprite.getBoundingRectangle().getWidth() + "\t" + bulletSprite.getBoundingRectangle()
                                                                                               .getHeight());
        setPosition(getX() + velx * speed * delta, getY() + vely * speed * delta);
    }


    private void setBulletDestination() {
        velx = target.x - getX();
        vely = target.y - getY();
        float length = (float) Math.sqrt(velx * velx + vely * vely);
        if (length != 0) {
            velx /= length;
            vely /= length;
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        update(Gdx.graphics.getDeltaTime());
        bulletSprite.setPosition(getX(), getY());
        bulletSprite.setRotation(getRotation());
        bulletSprite.draw(batch, parentAlpha);
        checkCollision();
    }

    public abstract void checkCollision();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
