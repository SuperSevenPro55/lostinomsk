package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import ru.pe.lostinomsk.Main;

public class MainMenuScreen implements Screen {
    private final Main game;
    private BitmapFont font;

    public MainMenuScreen(Main game, BitmapFont font) {
        this.game = game;
    }

    @Override
    public void show() {
        // Шрифт

    }

    @Override
    public void render(float delta) {

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
        font.dispose();
    }
}
