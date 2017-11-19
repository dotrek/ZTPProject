package com.libgdx.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;

/**
 * Created by dotre on 19.11.2017.
 */
public class GameClass extends Game {

    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create() {
        VisUI.load();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new GameOverScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
