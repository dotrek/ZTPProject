package com.libgdx.project;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img, background;
    PlayerSpaceship playerSpaceship;
    Sprite backgroundSprite;
    Vector2 shipLocation;
    Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        loadAssets();
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerSpaceship = new PlayerSpaceship(new Sprite(img));
        shipLocation = playerSpaceship.getPosition();

        stage.addActor(playerSpaceship);

    }

    private void drawBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(new OURInputProcessor(playerSpaceship));
        batch.begin();
        drawBackground();
        batch.end();
        stage.act();

        shooting();
        playerSpaceship.update(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void shooting() {
        int counter = 0;
        while (counter < playerSpaceship.bullets.size()) {
            Bullet currentBullet = playerSpaceship.bullets.get(counter);
            currentBullet.update();
            if (currentBullet.bulletLocation.x > -50 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() + 50
                    && currentBullet.bulletLocation.y > -50 && currentBullet.bulletLocation.y < Gdx.graphics
                    .getHeight() + 50) {
                stage.addActor(currentBullet);
            } else {
                playerSpaceship.bullets.remove(counter);
                if (playerSpaceship.bullets.size() > 0) {
                    counter--;
                }
            }

            counter++;
        }

    }

    private void loadAssets() {
        img = new Texture("spaceship.png");
        background = new Texture("background.jpg");

    }
}
