package com.libgdx.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    public static float delta;
    SpriteBatch batch;
    World world;
    Texture img;
    Texture background;
    PlayerSpaceship playerSpaceship;
    ArrayList<Enemy> enemies;
    Sprite backgroundSprite;
    Stage stage;
    Sound hitSound;
    EnemyGenerator enemyGenerator;

    @Override
    public void create() {
        delta = Gdx.graphics.getDeltaTime();
        batch = new SpriteBatch();
        stage = new Stage();
        world = new World(new Vector2(0, 0), false);
        Gdx.input.setInputProcessor(stage);
        loadAssets();
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerSpaceship = PlayerSpaceship.getInstance();
        enemies = new ArrayList<Enemy>();
        enemyGenerator = new EnemyGenerator(10, 5f, enemies);

        stage.addActor(playerSpaceship);
        stage.addActor(enemyGenerator);
        System.out.println(world.getBodyCount());
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
        checkIfEnemyDead();
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
            stage.addActor(currentBullet);
            currentBullet.update();
            if (currentBullet.bulletLocation.x > -50 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() + 50
                    && currentBullet.bulletLocation.y > -50 && currentBullet.bulletLocation.y < Gdx.graphics
                    .getHeight() + 50) {
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
                if (playerSpaceship.bullets.contains(bullet))
                    playerSpaceship.bullets.remove(bullet);
            }
        }
    }

    private void checkIfEnemyDead() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).health < 0) {
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
