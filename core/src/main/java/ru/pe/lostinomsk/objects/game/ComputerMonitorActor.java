package ru.pe.lostinomsk.objects.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ComputerMonitorActor extends Actor {
    private MiniGame currentMiniGame;
    private final Texture pixel;
    private final BitmapFont font;
    private final Rectangle gameBounds; // Хитбокс для друга

    private Runnable onGameFinished;
    private boolean finishEventFired = false;

    private String messageToShow = null;
    private Runnable onMessageDismissed = null;
    private Music click;
    private Music keybord;
    public ComputerMonitorActor(Texture pixel, BitmapFont font) {
        this.pixel = pixel;
        this.font = font;
        this.gameBounds = new Rectangle();
        click=Gdx.audio.newMusic(Gdx.files.internal("sound/mouse.mp3"));
        click.setLooping(false);
        click.setVolume(0.5f);
        keybord=Gdx.audio.newMusic(Gdx.files.internal("sound/keybord.mp3"));
        keybord.setLooping(false);
        keybord.setVolume(0.5f);
        // Перехватываем клики и клавиши прямо в Scene2D
        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Захватываем фокус клавиатуры при клике на монитор (нужно для ввода текста)
                if (event.getStage() != null) {
                    event.getStage().setKeyboardFocus(ComputerMonitorActor.this);
                }
                click.play();
                if (currentMiniGame != null && !currentMiniGame.isFinished()) {
                    // Передаем локальные координаты клика другу
                    currentMiniGame.touchDown(x + getX(), y + getY(), gameBounds);
                }
                return true;
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                keybord.play();
                if (currentMiniGame instanceof ru.pe.lostinomsk.objects.game.CodeDecryptMiniGame codeGame) {
                    return codeGame.keyTyped(character);
                }
                return false;
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (messageToShow != null && keycode == Input.Keys.SPACE) {
                    // Очищаем экран
                    messageToShow = null;

                    // Вызываем код продолжения
                    if (onMessageDismissed != null) {
                        onMessageDismissed.run();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void setMiniGame(MiniGame game, Runnable onGameFinished) {
        this.currentMiniGame = game;
        this.onGameFinished = onGameFinished;
        this.finishEventFired = false;
        if (game != null) {
            game.start(gameBounds);
            if (getStage() != null) getStage().setKeyboardFocus(this);
        }
    }

    public void showMessage(String text, Runnable onDismiss) {
        this.currentMiniGame = null; // Выключаем игру
        this.messageToShow = text;
        this.onMessageDismissed = onDismiss;

        if (getStage() != null) getStage().setKeyboardFocus(this);
    }

    // Вызывается каждый кадр при stage.act(delta)
    @Override
    public void act(float delta) {
        super.act(delta);
        // Синхронизируем координаты Actor-a с прямоугольником друга
        gameBounds.set(getX(), getY(), getWidth(), getHeight());

        if (currentMiniGame != null && !currentMiniGame.isFinished()) {
            currentMiniGame.update(delta, gameBounds);
        } else if (!finishEventFired) {
            finishEventFired = true;
            if (onGameFinished != null) onGameFinished.run();
        }
    }

    // Вызывается каждый кадр при stage.draw()
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(0f, 0f, 0f, 0.88f);
        batch.draw(pixel, getX(), getY(), getWidth(), getHeight());
        batch.setColor(Color.WHITE);

        if (currentMiniGame != null) {
            currentMiniGame.render((SpriteBatch) batch, pixel, font, gameBounds);
        } else if (messageToShow != null) {
            // ЕСЛИ ЕСТЬ СООБЩЕНИЕ - РИСУЕМ ЕГО ПО ЦЕНТРУ МОНИТОРА
            font.getData().setScale(0.2f);
            // Координаты X и Y здесь заданы примерно для центра монитора 89x55
            font.draw(batch, "Сигнал расшифрован:\n", getX() + 5, getY() + getHeight() / 2f + 15);
            font.getData().setScale(0.12f);
            font.draw(batch, messageToShow, getX() + 5, getY() + getHeight() / 2f + 5);

            font.getData().setScale(0.2f);
            font.draw(batch, "Нажмите SPACE", getX() + 5, getY() + 10);
            font.getData().setScale(0.15f);
        }
    }
}
