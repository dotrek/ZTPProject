package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by dotre on 19.11.2017.
 */
public class MainMenu implements Screen {

    final GameClass game;

    MainMenu(GameClass game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to OUR Game!!!! ", Gdx.graphics.getWidth() / 2f, Gdx.graphics
                .getHeight() / 2f);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        if (Gdx.input.isTouched()) {
            game.setScreen(new MyGdxGame(game));
            dispose();
        }
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

    }
}
