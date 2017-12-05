package com.libgdx.project.actors;

import com.badlogic.gdx.math.Vector2;
import com.libgdx.project.screen.GameScreen;

/**
 * Created by dotre on 30.11.2017.
 */
public class EnemyBullet extends Bullet {

    private EnemyBullet(Builder builder) {
        super(builder.sentLocation, builder.destination);
        this.speed = builder.speed;
    }

    @Override
    public void checkCollision() {
        if (this.bulletSprite.getBoundingRectangle()
                .overlaps(PlayerSpaceship.getInstance().spaceshipSprite.getBoundingRectangle())) {
            GameScreen.gameOver = true;
        }
    }

    public static class Builder {

        private Vector2 sentLocation;
        private Vector2 destination;
        private float speed;

        public Builder(Vector2 sentLocation, Vector2 destination) {
            this.sentLocation = sentLocation;
            this.destination = destination;
            this.speed = 50f;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public EnemyBullet build() {
            return new EnemyBullet(this);
        }
    }
}
