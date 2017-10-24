package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by dotre on 24.10.2017.
 */
public class PlayerSpaceship {


    private static float speed = 100f;
    private static final Vector2 bulletVelocity = new Vector2(20f, 0f);
    public Sprite playerSprite;
    private float spriteXPosition, spriteYPosition;
    private int health;
    Sound sound;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();


    PlayerSpaceship(Sprite playerSprite) {
        this.playerSprite = playerSprite;
        spriteXPosition = 0f;
        spriteYPosition = 0f;
        health = 10;
        sound=Gdx.audio.newSound(new FileHandle("ciu.mp3"));
    }

    public void addHealth(int healthAdded) {
        this.health += healthAdded;
    }

    public int getHealth() {
        return health;
    }

    public static float getSpeed() {
        return speed;
    }

    public float getSpriteXPosition() {
        return spriteXPosition;
    }

    public void setSpriteXPosition(float spriteXPosition) {
        this.spriteXPosition = spriteXPosition;
    }

    public float getSpriteYPosition() {
        return spriteYPosition;
    }

    public void setSpriteYPosition(float spriteYPosition) {
        this.spriteYPosition = spriteYPosition;
    }


    public void moveController() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            spriteYPosition += Gdx.graphics.getDeltaTime() * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            spriteYPosition -= Gdx.graphics.getDeltaTime() * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            spriteXPosition -= Gdx.graphics.getDeltaTime() * speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            spriteXPosition += Gdx.graphics.getDeltaTime() * speed;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bullets.add(new Bullet(new Vector2(spriteXPosition, spriteYPosition+playerSprite.getHeight()/2), bulletVelocity));
            sound.play();
        }
    }
}
