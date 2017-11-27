package com.libgdx.project.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.libgdx.project.GameClass;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by dotre on 19.11.2017.
 */
public class GameOverScreen implements Screen {
    Stage stage;
    GameClass game;
    Label gameOverLabel;
    VisLabel scoreLabel;
    TextButton menu, again;
    Texture sadAlienTexture;
    Skin skin;
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    public GameOverScreen(final GameClass game, int scored) {
        this.game = game;
        game.points += scored;
        Gdx.app.log("startGameOver", "");

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        sadAlienTexture = new Texture(Gdx.files.internal("sadalien.png"));

        gameOverLabel = new Label("GAME OVER!", skin);
        menu = new TextButton("Back to menu", skin);
        again = new TextButton("Try again", skin);
        scoreLabel = new VisLabel("You scored: " + scored);


        setActorsPositions();
        stage = new Stage();
        stage.addActor(menu);
        stage.addActor(again);
        stage.addActor(gameOverLabel);
        stage.addActor(game.pointsLabel);
        stage.addActor(scoreLabel);
        Gdx.input.setInputProcessor(stage);
    }

    private void setActorsPositions() {
        menu.setSize(width / 4f, height / 12f);
        menu.setPosition(0, 0);
        menu.getLabel().setFontScale(Math.abs(width / 800), Math.abs(height / 600));

        again.setSize(width / 4f, height / 12f);
        again.setPosition(width - again.getWidth(), 0);
        again.getLabel().setFontScale(Math.abs(width / 800), Math.abs(height / 600));
        scoreLabel.setFontScale(Math.abs(width / 800), Math.abs(height / 600));
        scoreLabel.setPosition(width - scoreLabel.getWidth() * 2f, height - scoreLabel.getHeight());
        gameOverLabel.setSize(width, height / 3f);
        gameOverLabel.setPosition(0, height - gameOverLabel.getHeight());
        gameOverLabel.setFontScale(5f);
        gameOverLabel.setAlignment(Alignment.CENTER.getAlignment());

    }

    @Override
    public void show() {
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.5f));
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                System.out.println("clicked");
            }
        });
        again.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(sadAlienTexture, Gdx.graphics.getWidth() / 2f - sadAlienTexture.getWidth() / 2f, 0f);
        game.batch.end();
        stage.act();
        stage.draw();

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
