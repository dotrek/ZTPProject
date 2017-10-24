package com.libgdx.project;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img, background;
    PlayerSpaceship playerSpaceship;
    Sprite backgroundSprite;
    Vector2 shipLocation;


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("spaceship.png");

        background = new Texture("background.jpg");
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerSpaceship = new PlayerSpaceship(new Sprite(img));
        shipLocation = new Vector2(playerSpaceship.getSpriteXPosition(), playerSpaceship.getSpriteYPosition());
    }

    private void drawBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void render() {
        System.out.println(playerSpaceship.getSpriteXPosition() + "\t" + playerSpaceship.getSpriteYPosition());
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawBackground();
        playerSpaceship.moveController();
        batch.draw(playerSpaceship.playerSprite, playerSpaceship.getSpriteXPosition(), playerSpaceship.getSpriteYPosition(), 64f, 64f);
        int counter = 0;
        while(counter < playerSpaceship.bullets.size())
        {
            Bullet currentBullet = playerSpaceship.bullets.get(counter);
            currentBullet.update();
            if(currentBullet.bulletLocation.x > -50 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() + 50 && currentBullet.bulletLocation.y > -50 && currentBullet.bulletLocation.y < Gdx.graphics.getHeight() + 50)
            {
                batch.draw(currentBullet.getBulletSprite(), currentBullet.bulletLocation.x, currentBullet.bulletLocation.y, 32,16);
            }
            else
            {
                playerSpaceship.bullets.remove(counter);
                if(playerSpaceship.bullets.size() > 0)
                {
                    counter--;
                }
            }

            counter++;
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
