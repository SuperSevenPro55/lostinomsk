package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import ru.pe.lostinomsk.Main;
import ru.pe.lostinomsk.objects.RadioGroup;
import ru.pe.lostinomsk.objects.game.*;

public class GameScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Music music;
    private Rectangle monitorBounds;
    private final Vector2 touch = new Vector2();

    private Texture pixel;
    private BitmapFont font;

    private int currentStoryStage = 1;
    private MiniGame currentMiniGame;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        music=Gdx.audio.newMusic(Gdx.files.internal("music/main.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        stage = new Stage(new FitViewport(240, 135), game.batch);
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();

        font = new BitmapFont();

        Texture bgTower = game.assets.manager.get("mainScene/tower1.png", Texture.class);
        Texture bgWall = game.assets.manager.get("mainScene/wall.png", Texture.class);
        Texture bgTables = game.assets.manager.get("mainScene/tables.png", Texture.class);
        Texture bgDevices = game.assets.manager.get("mainScene/devices.png", Texture.class);

        Image bg = new Image(bgTower);
        Image wall = new Image(bgWall);
        Image tables = new Image(bgTables);
        Image devices = new Image(bgDevices);

        ComputerMonitorActor monitor = new ComputerMonitorActor(pixel, font);
        monitor.setBounds(15, 14, 89, 55);

        RadioGroup radio = new RadioGroup(game);
        radio.setPosition(142, 14);

        radio.setOnSignalCaught(new Runnable() {
            @Override
            public void run() {
                if (currentStoryStage == 1) {
                    monitor.setMiniGame(new CodeDecryptMiniGame(), () -> {
                        monitor.showMessage("Сигнал расшифрован:\nКто-нибудь...", () -> {
                            currentStoryStage = 2;
                            radio.resetRadio();
                        });
                    });
                }
                else if (currentStoryStage == 2) {
                    monitor.setMiniGame(new CatchDotMiniGame(), () -> {
                        monitor.showMessage("Сигнал расшифрован:\nЛокдаун...", () -> {
                            currentStoryStage = 3;
                            radio.resetRadio();
                        });
                    });
                }
                else if (currentStoryStage == 3) {

                    monitor.setMiniGame(new ReactionMiniGame(), () -> {
                        monitor.showMessage("ТЕБЕ НЕ ПОКИНУТЬ ОМСК", () -> {
                            music.stop();
                            game.setScreen(new FinalScreen(game));
                        });
                    });
                }
            }
        });

        radio.setPosition(142, 14);

        stage.addActor(bg);
        stage.addActor(wall);
        stage.addActor(tables);
        stage.addActor(devices);
        stage.addActor(radio);
        stage.addActor(monitor);

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
        pixel.dispose();
        font.dispose();
    }
}
