package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.pe.lostinomsk.FirstScreen;
import ru.pe.lostinomsk.Main;

public class MainMenuScreen implements Screen {
    private final Main game;
    private final BitmapFont font;
    private SpriteBatch batch;
    private Texture background;

    private Rectangle playButtonBounds;
    private Vector3 touchPoint;


    public MainMenuScreen(Main game, BitmapFont font) {
        this.game = game;
        this.font = font;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        font.getData().setScale(3f);

        background = new Texture("main_menu_background.png");
        // Ставим прямоугольник пониже, например, на высоте 150 пикселей от низа
        playButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2f - 100, 150, 200, 100);
        touchPoint = new Vector3();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        // Фон
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Название
        font.setColor(Color.WHITE);
        font.draw(batch,
            "Lost in Omsk",
            Gdx.graphics.getWidth() / 2f - 225,
            Gdx.graphics.getHeight() / 2f + 80);

        // Кнопка "Играть"
        font.setColor(Color.WHITE);
        font.draw(batch, "ИГРАТЬ", playButtonBounds.x + 10, playButtonBounds.y + 60);

        batch.end();

        if (Gdx.input.justTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            float realTouchY = Gdx.graphics.getHeight() - touchPoint.y;

            if (playButtonBounds.contains(touchPoint.x, realTouchY)) {
                game.setScreen(new FirstScreen());
            }
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
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        font.dispose();
    }
}
