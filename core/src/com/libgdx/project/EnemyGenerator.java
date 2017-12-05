package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.libgdx.project.actors.*;

/**
 * Created by dotre on 17.11.2017.
 */
public class EnemyGenerator extends Actor {

    private static final Map<EnemyType, Texture> enemyTextureMap = new EnumMap<EnemyType, Texture>(EnemyType.class);

    private float spawnFrequency;
    private float difficultyChangeTime;
    float spawnTimer;
    float difficultyTimer;
    VisDialog label;

    private List<Enemy> enemies;
    private int difficulty;

    public EnemyGenerator(float frequency, ArrayList<Enemy> enemies) {
        this.spawnFrequency = frequency;
        this.enemies = enemies;
        difficulty = 0;
        spawnTimer = 0f;
        difficultyTimer = 0f;
        difficultyChangeTime = frequency * 3;
    }

    @Override
    public void act(float delta) {
        System.out.println(spawnTimer + "\t" + spawnFrequency);
        if (spawnTimer >= spawnFrequency) {
            Enemy e = getRandomSpawnLocation();
            enemies.add(e);
            getStage().addActor(enemies.get(enemies.indexOf(e)));
            spawnTimer = 0;
        } else spawnTimer += delta;
        if (difficultyTimer >= difficultyChangeTime) {
            if (difficulty < 4) {
                difficulty++;
                createWarningLabel(difficulty);
                difficultyTimer = 0;
            } else if (spawnFrequency > 1) spawnFrequency -= delta;
            else {
                label = new VisDialog("DIEEEEEEE!!!!");
                label.show(getStage());
                label.getTitleLabel().addAction(
                        Actions.sequence(Actions.delay(0.5f), Actions.color(Color.RED, 1f), Actions.rotateBy(180),
                                Actions.removeActor(label)));
            }
        } else difficultyTimer += delta;
    }

    private void createWarningLabel(int difficulty) {
        Color color;
        switch (difficulty) {
            case 0:
                label = new VisDialog("You want to play? Ok, lets do it!");
                color = Color.ROYAL;
                break;
            case 1:
                label = new VisDialog("What about making it a little unexpected");
                color = Color.GREEN;
                break;
            case 2:
                label = new VisDialog("FAAASTER!");
                color = Color.RED;
                break;
            case 3:
                label = new VisDialog("You're shooting! It's unfair!");
                color = Color.PURPLE;
                break;
            case 4:
                label = new VisDialog("Ok, lets make it BIGGER!");
                color = Color.BLUE;
                break;
            default:
                label = new VisDialog("Nice one!");
                color = Color.YELLOW;
        }
        label.show(getStage());
        label.getTitleLabel().addAction(
                Actions.sequence(Actions.delay(0.5f), Actions.color(color, 1f), Actions.rotateBy(180),
                        Actions.removeActor(label)));
    }

    private Enemy getRandomSpawnLocation() {
        Random r = new Random();
        Enemy enemy = getRandomEnemy(difficulty);
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        switch (r.nextInt(4)) {
            case 0:
                enemy.setPosition(width, r.nextFloat() * height);
                break;
            case 1:
                enemy.setPosition(0, r.nextFloat() * height);
                break;
            case 2:
                enemy.setPosition(r.nextFloat() * width, height);
                break;
            case 3:
                enemy.setPosition(r.nextFloat() * width, 0f);
                break;
        }
        return enemy;
    }

    private Texture getTexture(EnemyType type) {
        switch (type) {
            case BIG_BLUE:
                enemyTextureMap.put(type, new Texture(Gdx.files.internal("enemy.png")));
                break;
            case BLUE:
                enemyTextureMap.put(type, new Texture(Gdx.files.internal("enemy.png")));
                break;
            case GREEN:
                enemyTextureMap.put(type, new Texture(Gdx.files.internal("spaceshipgreen.png")));
                break;
            case PURPLE:
                enemyTextureMap.put(type, new Texture(Gdx.files.internal("spaceshippurple.png")));
                break;
            case RED:
                enemyTextureMap.put(type, new Texture(Gdx.files.internal("spaceshipred.png")));
                break;
        }
        return enemyTextureMap.get(type);
    }

    private Enemy getRandomEnemy(int x) {
        switch (x) {
            case 0:
                return new BlueEnemy(getTexture(EnemyType.BLUE));
            case 1:
                return new GreenEnemy(getTexture(EnemyType.GREEN));
            case 2:
                return new RedEnemy(getTexture(EnemyType.RED));
            case 3:
                return new PurpleEnemy(getTexture(EnemyType.PURPLE));
            case 4:
                return new BigBlueEnemy(getTexture(EnemyType.BIG_BLUE));
        }
        return new BlueEnemy(getTexture(EnemyType.BLUE));
    }
}
