package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import com.libgdx.project.actors.PlayerSpaceship;
import com.libgdx.project.actors.Bullet;

import static com.libgdx.project.actors.PlayerSpaceship.ACCELERATION_X;
import static com.libgdx.project.actors.PlayerSpaceship.ACCELERATION_Y;

/**
 * Created by dotre on 03.11.2017.
 */
public class OURInputProcessor implements InputProcessor {

    private PlayerSpaceship spaceship;

    public OURInputProcessor(PlayerSpaceship spaceship) {
        this.spaceship = spaceship;
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            spaceship.moveStateY = PlayerSpaceship.MoveStateY.UP;
            spaceship.acceleration.set(0, ACCELERATION_Y);

        }
        if (keycode == Input.Keys.S) {
            spaceship.moveStateY = PlayerSpaceship.MoveStateY.DOWN;
            spaceship.acceleration.set(0, -ACCELERATION_Y);

        }
        if (keycode == Input.Keys.A) {
            spaceship.moveStateX = PlayerSpaceship.MoveStateX.LEFT;
            spaceship.acceleration.set(-ACCELERATION_X, 0);

        }
        if (keycode == Input.Keys.D) {
            spaceship.moveStateX = PlayerSpaceship.MoveStateX.RIGHT;
            spaceship.acceleration.set(ACCELERATION_X, 0);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.S) spaceship.moveStateY = PlayerSpaceship.MoveStateY.STOP;
        if (keycode == Input.Keys.A || keycode == Input.Keys.D) spaceship.moveStateX = PlayerSpaceship.MoveStateX.STOP;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Bullet bullet = new Bullet(new Vector2(spaceship.getX(), spaceship.getY() + spaceship.spaceshipSprite
                .getHeight() / 2f), new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
        float rot = MathUtils.radiansToDegrees * MathUtils
                .atan2(-screenY + spaceship.getY(), screenX - spaceship.getX());
        spaceship.setRotation(rot);
        bullet.setRotation(spaceship.getRotation());
        spaceship.bullets.add(bullet);
        spaceship.sound.play();
        Gdx.input.vibrate(100);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float rot = MathUtils.radiansToDegrees * MathUtils.atan2(mouseY - spaceship.getY(), mouseX - spaceship.getX());
        spaceship.setRotation(rot);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
