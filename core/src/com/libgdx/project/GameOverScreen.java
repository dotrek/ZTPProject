package com.libgdx.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.building.utilities.Alignment;

/**
 * Created by dotre on 19.11.2017.
 */
public class GameOverScreen implements Screen {
    Stage stage;
    GameClass game;
    Label visLabel;
    TextButton menu, again;
    Skin skin;
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    public GameOverScreen(final GameClass game) {
        this.game = game;
        Gdx.app.log("startGameOver", "");

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        visLabel = new Label("GAME OVER!", skin);
        menu = new TextButton("Back to menu", skin);
        again = new TextButton("Try again", skin);

        menu.setPosition(0, 0);
        again.setPosition(width - again.getWidth(), 0);

        visLabel.setSize(width, height / 3f);
        visLabel.setPosition(0, height - visLabel.getHeight());
        visLabel.setFontScale(5f);
        visLabel.setAlignment(Alignment.CENTER.getAlignment());

        stage = new Stage();
        stage.addActor(menu);
        stage.addActor(again);
        stage.addActor(visLabel);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                System.out.println("clicked");
            }
        });
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();
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
