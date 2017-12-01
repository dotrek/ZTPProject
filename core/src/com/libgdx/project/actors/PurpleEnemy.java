package com.libgdx.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dotre on 23.11.2017.
 */
public class PurpleEnemy extends Enemy {
    Texture texture = new Texture(Gdx.files.internal("spaceshippurple.png"));
    private float timer;
    private static int frequency = 3;

    public PurpleEnemy() {
        super();
        timer = 0;
        spaceshipSprite.setTexture(texture);
    }

    @Override
    public void update(float delta) {
//        System.out.println(playerInstance.getX() + "\t" + playerInstance.getY() + "\t \t " + getX() + "  " + getY());
        movement(delta);
        shooting(delta);
    }

    private void movement(float delta) {
        moveVelocity.x = (playerInstance.getX() - this.getX());
        moveVelocity.y = (playerInstance.getY() - this.getY());
        distanceToPlayer = (float) Math.sqrt(moveVelocity.x * moveVelocity.x + moveVelocity.y * moveVelocity.y);
        moveVelocity.x /= distanceToPlayer;
        moveVelocity.y /= distanceToPlayer;
        this.setPosition(getX() + moveVelocity.x * speed * delta, getY() + moveVelocity.y * speed * delta);
    }

    private void shooting(float delta) {
        for (Bullet b : bullets) {
            b.update(delta);
        }
        if (timer >= frequency) {
            Vector2 playerPosition = PlayerSpaceship.getInstance().getPosition();
            EnemyBullet bullet = new EnemyBullet(new Vector2(getX(), getY()), playerPosition);
            sound.play();
            float rot = MathUtils.radiansToDegrees * MathUtils
                    .atan2(playerPosition.y - getY(), playerPosition.x - getX());
            bullet.setRotation(rot);
            System.out.println("Bullet added");
            bullets.add(bullet);
            getStage().addActor(bullet);
            timer = 0;
        } else timer += delta;
        System.out.println(timer);
    }

}
