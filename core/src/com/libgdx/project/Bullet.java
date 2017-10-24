package com.libgdx.project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dotre on 24.10.2017.
 */
public class Bullet {
    public Vector2 bulletLocation;
    private Vector2 bulletVelocity;
    private static Texture bulletTexture;
    private static Sprite bulletSprite;


    public Bullet(Vector2 sentLocation, Vector2 sentVelocity) {

        bulletTexture = new Texture("bullet.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletLocation = new Vector2(sentLocation.x, sentLocation.y);
        bulletVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
    }

    public void update() {
        bulletLocation.x += bulletVelocity.x;
        bulletLocation.y += bulletVelocity.y;
    }


    public Vector2 getBulletVelocity() {
        return bulletVelocity;
    }

    public static Texture getBulletTexture() {
        return bulletTexture;
    }

    public static Sprite getBulletSprite() {
        return bulletSprite;
    }
}
