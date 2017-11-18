package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;
import java.util.Random;

/**
 * Created by dotre on 17.11.2017.
 */
public class EnemyGenerator extends Actor {
    private int enemiesAmount;
    private float frequency;
    float deltaTimer;
    private List<Enemy> enemies;

    public EnemyGenerator(int enemiesAmount, float frequency, List<Enemy> enemies) {
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
        Random r = new Random();
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        if (deltaTimer >= frequency) {
            Enemy e = new Enemy(width, r.nextFloat() * height);
            enemies.add(e);
            getStage().addActor(enemies.get(enemies.indexOf(e)));
            deltaTimer = 0;
        } else deltaTimer += delta;

    }
}
