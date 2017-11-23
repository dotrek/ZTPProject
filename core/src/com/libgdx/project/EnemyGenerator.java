package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.libgdx.project.actors.BigBlueEnemy;
import com.libgdx.project.actors.Enemy;
import com.libgdx.project.actors.PurpleEnemy;
import com.libgdx.project.actors.GreenEnemy;

/**
 * Created by dotre on 17.11.2017.
 */
public class EnemyGenerator extends Actor {

    private int enemiesAmount;
    private float frequency;
    float deltaTimer;
    private List<Enemy> enemies;

    public EnemyGenerator(int enemiesAmount, float frequency, ArrayList<Enemy> enemies) {
        this.enemiesAmount = enemiesAmount;
        this.frequency = frequency;
        this.enemies = enemies;
        deltaTimer = 0f;
    }

    public int getEnemiesAmount() {
        return enemiesAmount;
    }

    public void setEnemiesAmount(int enemiesAmount) {
        this.enemiesAmount = enemiesAmount;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    @Override
    public void act(float delta) {
        if (deltaTimer >= frequency) {
            com.libgdx.project.actors.Enemy e = getRandomSpawnLocation();
            enemies.add(e);
            getStage().addActor(enemies.get(enemies.indexOf(e)));
            deltaTimer = 0;
        } else deltaTimer += delta;

    }

    private Enemy getRandomSpawnLocation() {
        Random r = new Random();
        Enemy enemy = getRandomEnemy(r.nextInt(4));
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

    private Enemy getRandomEnemy(int x) {
        switch (x) {
            case 0:
                return new Enemy();
            case 1:
                return new PurpleEnemy();
            case 2:
                return new GreenEnemy();
            case 3:
                return new BigBlueEnemy();
        }
        return new Enemy();
    }
}
