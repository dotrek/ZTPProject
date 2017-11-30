package com.libgdx.project.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.libgdx.project.*;
import com.libgdx.project.actors.Bullet;
import com.libgdx.project.actors.Enemy;
import com.libgdx.project.actors.PlayerSpaceship;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class GameScreen implements Screen {

    final GameClass game;
    public static float delta;
    public int score;
    VisLabel scoreLabel;
    Texture background;
    PlayerSpaceship playerSpaceship;
    public static ArrayList<Enemy> enemies;
    Sprite backgroundSprite;
    Stage stage;
    Sound hitSound;
    EnemyGenerator enemyGenerator;
    public static boolean gameOver;


    GameScreen(GameClass game) {
        this.game = game;
        delta = Gdx.graphics.getDeltaTime();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        loadAssets();
        score = 0;

        scoreLabel = new VisLabel("Score: " + Integer.toString(score));
        scoreLabel.setFontScale(Math.abs(Gdx.graphics.getWidth() / 800), Math.abs(Gdx.graphics.getHeight() / 600));
        scoreLabel.setPosition(Gdx.graphics.getWidth() / 2f - scoreLabel.getWidth(),
                Gdx.graphics.getHeight() - scoreLabel.getHeight());

        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerSpaceship = PlayerSpaceship.getInstance();
        enemies = new ArrayList<Enemy>();
        enemyGenerator = new EnemyGenerator(10, 5f, enemies);
        stage.addActor(playerSpaceship);
        stage.addActor(enemyGenerator);
        stage.addActor(scoreLabel);
        gameOver = false;
    }

    private void drawBackground() {
        backgroundSprite.draw(game.batch);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(new OURInputProcessor(playerSpaceship));
        scoreLabel.setX(Gdx.graphics.getWidth() / 2f - scoreLabel.getWidth() / 2f);
        scoreLabel.setText("Score: " + Integer.toString(score));
        checkGameOver();
        game.batch.begin();
        drawBackground();
        game.batch.end();
        stage.act();
        checkIfPlayerDead();
        checkIfEnemyDead();
        stage.draw();
    }

    @Override
    public void show() {
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));
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


    private void checkIfEnemyDead() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).health <= 0) {
                enemies.get(i).addAction(Actions.removeActor());
                enemies.remove(i);
                score++;
                hitSound.play();
            }
        }
    }

    private void checkIfPlayerDead() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).spaceshipSprite.getBoundingRectangle()
                                              .overlaps(playerSpaceship.spaceshipSprite.getBoundingRectangle())) {
                gameOver = true;
            }
        }
    }

    public void checkGameOver() {
        if (gameOver) {
            game.setScreen(new GameOverScreen(game, score));
            Gdx.input.vibrate(2000);
        }
    }

    private void loadAssets() {
        background = new Texture("background.jpg");
        hitSound = Gdx.audio.newSound(Gdx.files.internal("pydsz.mp3"));
    }
}
