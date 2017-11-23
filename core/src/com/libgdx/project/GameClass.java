package com.libgdx.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kotcrab.vis.ui.VisUI;
import com.libgdx.project.screen.*;

/**
 * Created by dotre on 19.11.2017.
 */
public class GameClass extends Game {

    public SpriteBatch batch;
    Actor fadeActor = new Actor();
    ShapeRenderer fadeRenderer;
    AssetManager assets;

    @Override
    public void create() {
        VisUI.load();
        batch = new SpriteBatch();
        fadeRenderer = new ShapeRenderer();
        assets = new AssetManager();
        this.setScreen(new MainMenu(this));
    }

    public void setScreenWithFade(final Screen screen, float duration) {
        fadeActor.clearActions();
        fadeActor.setColor(Color.CLEAR);
        fadeActor.addAction(Actions
                .sequence(Actions.color(Color.BLACK, duration / 2f, Interpolation.fade), Actions.run(new Runnable() {
                    public void run() {
                        setScreen(screen);
                    }
                }), Actions.color(Color.CLEAR, duration / 2f, Interpolation.fade)));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        batch.begin();
        fadeActor.act(Gdx.graphics.getDeltaTime());
        batch.end();
        float alpha = fadeActor.getColor().a;
        if (alpha != 0) {
            fadeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            fadeRenderer.setColor(0, 0, 0, alpha);
            fadeRenderer.rect(-1, -1, 2, 2);
            fadeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
