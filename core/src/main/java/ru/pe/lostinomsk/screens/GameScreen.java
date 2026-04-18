package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.pe.lostinomsk.Main;

public class GameScreen implements Screen {
    private final Main game;
    private Stage stage;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(240, 135), game.batch);

        Texture bgTower = game.assets.manager.get("mainScene/tower1.png", Texture.class);
        Texture bgWall = game.assets.manager.get("mainScene/wall.png", Texture.class);
        Texture bgTables = game.assets.manager.get("mainScene/tables.png", Texture.class);
        Texture bgDevices = game.assets.manager.get("mainScene/devices.png", Texture.class);

        Image bg = new Image(bgTower);
        Image wall = new Image(bgWall);
        Image tables = new Image(bgTables);
        Image devices = new Image(bgDevices);

        stage.addActor(bg);
        stage.addActor(wall);
        stage.addActor(tables);
        stage.addActor(devices);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

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

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
