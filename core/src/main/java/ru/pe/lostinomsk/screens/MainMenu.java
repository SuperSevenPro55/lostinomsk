package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.pe.lostinomsk.Main;
import ru.pe.lostinomsk.utils.GameAssets;

public class MainMenu implements Screen {
    private final Main game;
    private Texture background;
    private Stage stage;

    public MainMenu(Main game) {
        this.game = game;

        }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(1920, 1080), game.batch);
        background = game.assets.manager.get(GameAssets.MAIN_MENU_BACKGROUND);

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle titleStyle = new Label.LabelStyle(game.font, Color.WHITE);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.font;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.overFontColor = Color.LIGHT_GRAY;

        Label titleLabel = new Label("Lost in Omsk", titleStyle);
        titleLabel.setFontScale(3f);

        TextButton playButton = new TextButton("ИГРАТЬ", buttonStyle);
        playButton.setScale(2f);

        playButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
           }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(titleLabel).padBottom(50).row();
        table.add(playButton).padBottom(-100);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
