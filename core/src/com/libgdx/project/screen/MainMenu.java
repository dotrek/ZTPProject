package com.libgdx.project.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.libgdx.project.GameClass;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by dotre on 19.11.2017.
 */
public class MainMenu implements Screen {

    final GameClass game;
    TextButton start, info, exit;
    Skin skin;
    Stage stage;
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    public MainMenu(GameClass game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();
        start = new TextButton("Start", skin);
        info = new TextButton("Info", skin);
        exit = new TextButton("Exit", skin);
        stage.addActor(start);
        stage.addActor(info);
        stage.addActor(exit);
        stage.addActor(game.pointsLabel);
        Gdx.input.setInputProcessor(stage);
        setButtonBounds();
        setButtonClickListeners();
    }

    private void setButtonBounds() {
        start.setSize(width, height / 12);
        start.getLabel().setSize(start.getWidth(), start.getHeight());
        start.getLabel().setFontScale(width / start.getLabel().getFontScaleX() / 800, height / start.getLabel()
                                                                                                    .getFontScaleY() / 600);

        info.setSize(width, height / 12);
        info.getLabel().setSize(info.getWidth(), info.getHeight());
        info.getLabel().setFontScale(width / info.getLabel().getFontScaleX() / 800, height / info.getLabel()
                                                                                                 .getFontScaleY() / 600);


        exit.setSize(width, height / 12);
        exit.getLabel().setSize(exit.getWidth(), exit.getHeight());
        exit.getLabel().setFontScale(width / exit.getLabel().getFontScaleX() / 800, height / exit.getLabel()
                                                                                                 .getFontScaleY() / 600);


        start.setPosition(0, height / 2f);
        info.setPosition(0, start.getY() - start.getHeight());
        exit.setPosition(0, info.getY() - info.getHeight());
    }

    private void setButtonClickListeners() {
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreenWithFade(new GameScreen(game), 3f);
            }
        });
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        info.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VisDialog dialog = new VisDialog("Game for ztp project");
                dialog.setSize(width / 3f, height / 6f);
                dialog.addCloseButton();
                dialog.show(stage);
            }
        });
    }

    @Override
    public void show() {
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(1f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
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
