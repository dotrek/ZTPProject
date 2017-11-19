package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kotcrab.vis.ui.VisUI;

import java.util.ArrayList;

public class MyGdxGame implements Screen {
    final GameClass game;
    public static float delta;
    Texture img;
    Texture background;
    PlayerSpaceship playerSpaceship;
    ArrayList<Enemy> enemies;
    Sprite backgroundSprite;
    Stage stage;
    Sound hitSound;
    EnemyGenerator enemyGenerator;

    MyGdxGame(GameClass game) {
        this.game = game;
        delta = Gdx.graphics.getDeltaTime();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        VisUI.load();
        loadAssets();
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerSpaceship = PlayerSpaceship.getInstance();
        enemies = new ArrayList<Enemy>();
        enemyGenerator = new EnemyGenerator(10, 5f, enemies);
        stage.addActor(playerSpaceship);
        stage.addActor(enemyGenerator);
    }

    private void drawBackground() {
        backgroundSprite.draw(game.batch);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(new OURInputProcessor(playerSpaceship));
        game.batch.begin();
        drawBackground();
        game.batch.end();
        stage.act();
        checkIfEnemyDead();
        shooting();

        playerSpaceship.update(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
    }

    private void shooting() {
        int counter = 0;
        while (counter < playerSpaceship.bullets.size()) {
            Bullet currentBullet = playerSpaceship.bullets.get(counter);
            stage.addActor(currentBullet);
            currentBullet.update(Gdx.graphics.getDeltaTime());
            if (currentBullet.getX() > -50 && currentBullet.getX() < Gdx.graphics.getWidth() + 50 && currentBullet
                    .getY() > -50 && currentBullet.getY() < Gdx.graphics.getHeight() + 50) {
                currentBullet.act(delta);
                checkCollision(currentBullet);

            } else {
                playerSpaceship.bullets.remove(counter);
                if (playerSpaceship.bullets.size() > 0) {
                    counter--;
                }
            }

            counter++;
        }
    }

    private void checkCollision(Bullet bullet) {

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).spaceshipSprite.getBoundingRectangle()
                                              .overlaps(bullet.bulletSprite.getBoundingRectangle())) {
                enemies.get(i).health -= bullet.getDamage();
                bullet.addAction(Actions.removeActor());
                if (playerSpaceship.bullets.contains(bullet)) playerSpaceship.bullets.remove(bullet);
            }
        }
    }

    private void checkIfEnemyDead() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).health <= 0) {
                enemies.get(i).addAction(Actions.removeActor());
                enemies.remove(i);
                hitSound.play();
            }
        }
    }


    private void loadAssets() {
        img = new Texture("spaceship.png");
        background = new Texture("background.jpg");
        hitSound = Gdx.audio.newSound(new FileHandle("pydsz.mp3"));
    }
}
