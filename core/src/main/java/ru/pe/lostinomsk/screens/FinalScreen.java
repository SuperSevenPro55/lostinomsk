package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.pe.lostinomsk.Main;

public class FinalScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Music musicEnd;
    public FinalScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(240, 135), game.batch);
        musicEnd=Gdx.audio.newMusic(Gdx.files.internal("sound/end.mp3"));
        musicEnd.setLooping(false);
        musicEnd.setVolume(0.5f);
        musicEnd.play();
        // Создаем стиль текста. Сделаем его красным для жути!
        Label.LabelStyle mainStyle = new Label.LabelStyle(game.font, Color.RED);
        Label.LabelStyle hintStyle = new Label.LabelStyle(game.font, Color.DARK_GRAY);

        // Главный текст
        Label titleText = new Label("ТЕБЕ НЕ ПОКИНУТЬ ОМСК", mainStyle);
        titleText.setAlignment(Align.center);
        titleText.setFillParent(true); // Автоматически центрирует по всему экрану

        // В зависимости от размера твоего шрифта в Main, масштаб возможно придется покрутить
        titleText.setFontScale(0.7f);

        // Подсказка снизу
        Label hintText = new Label("Нажмите ENTER для выхода", hintStyle);
        hintText.setAlignment(Align.bottom);
        hintText.setFillParent(true);
        hintText.setFontScale(0.3f);
//        hintText.padBottom(15); // Отступ снизу

        stage.addActor(titleText);
        stage.addActor(hintText);

        game.font.getData().setScale(1f);
    }

    @Override
    public void render(float delta) {
        // Заливаем фон кромешной тьмой
        ScreenUtils.clear(Color.BLACK);

        // Если игрок нажал Пробел или Enter — возвращаем его в главное меню
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Судя по твоей структуре файлов, твое меню называется MainMenu
            game.setScreen(new MainMenuScreen(game));
        }

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
