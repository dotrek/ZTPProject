package com.libgdx.project.actors;

import com.badlogic.gdx.math.Vector2;
import com.libgdx.project.screen.GameScreen;

/**
 * Created by dotre on 30.11.2017.
 */
public class EnemyBullet extends Bullet {
    public EnemyBullet(Vector2 sentLocation, Vector2 destination) {
        super(sentLocation, destination);
        speed = 50f;
    }

    @Override
    public void checkCollision() {
        if (this.bulletSprite.getBoundingRectangle()
                             .overlaps(PlayerSpaceship.getInstance().spaceshipSprite.getBoundingRectangle())) {
            GameScreen.gameOver = true;
        }
    }
}
