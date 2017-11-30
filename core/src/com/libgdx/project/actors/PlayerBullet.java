package com.libgdx.project.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.libgdx.project.screen.GameScreen;

/**
 * Created by dotre on 30.11.2017.
 */
public class PlayerBullet extends Bullet {
    public PlayerBullet(Vector2 sentLocation, Vector2 destination) {
        super(sentLocation, destination);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        checkCollision();
    }

    @Override
    public void checkCollision() {
        for (int i = 0; i < GameScreen.enemies.size(); i++) {
            if (GameScreen.enemies.get(i).spaceshipSprite.getBoundingRectangle()
                                                         .overlaps(this.bulletSprite.getBoundingRectangle())) {
                GameScreen.enemies.get(i).health -= this.getDamage();
                PlayerSpaceship.getInstance().getBullets().remove(this);
                getStage().addAction(Actions.removeActor(this));
            }
        }
    }
}
