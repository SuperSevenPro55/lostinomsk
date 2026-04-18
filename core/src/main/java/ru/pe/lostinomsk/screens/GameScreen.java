package ru.pe.lostinomsk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.pe.lostinomsk.Main;
import ru.pe.lostinomsk.objects.game.CatchDotMiniGame;
import ru.pe.lostinomsk.objects.game.CodeDecryptMiniGame;
import ru.pe.lostinomsk.objects.game.MiniGame;
import ru.pe.lostinomsk.objects.game.ReactionMiniGame;

public class GameScreen implements Screen {
    private final Main game;
    private Stage stage;

    private Rectangle monitorBounds;
    private final Vector2 touch = new Vector2();

    private Texture pixel;
    private BitmapFont font;

    private MiniGame currentMiniGame;

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

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();

        font = new BitmapFont();


        monitorBounds = new Rectangle(15, 14, 89, 55);
        InputAdapter clickProcessor = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touch.set(screenX, screenY);
                stage.screenToStageCoordinates(touch);
                startRandomMiniGame();
                if (!monitorBounds.contains(touch.x, touch.y)) {
                    return false;
                }

                if (currentMiniGame != null && !currentMiniGame.isFinished()) {
                    currentMiniGame.touchDown(touch.x, touch.y, monitorBounds);
                    return true;
                }

                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                if (currentMiniGame instanceof CodeDecryptMiniGame codeGame) {
                    return codeGame.keyTyped(character);
                }
                return false;
            }
        };

        Gdx.input.setInputProcessor(new InputMultiplexer(clickProcessor, stage));
    }

    public void startRandomMiniGame() {
        if (currentMiniGame != null && !currentMiniGame.isFinished()) {
            return;
        }

        int randomIndex = MathUtils.random(2);

        switch (randomIndex) {
            case 0:
                currentMiniGame = new CatchDotMiniGame();
                break;
            case 1:
                currentMiniGame = new ReactionMiniGame();
                break;
            default:
                currentMiniGame = new CodeDecryptMiniGame();
                break;
        }

        currentMiniGame.start(monitorBounds);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            currentMiniGame = null;
        }

        stage.act(delta);
        stage.draw();

        if (currentMiniGame != null && !currentMiniGame.isFinished()) {
            currentMiniGame.update(delta, monitorBounds);
        }

        game.batch.setProjectionMatrix(stage.getCamera().combined);
        game.batch.begin();

        game.batch.setColor(0f, 0f, 0f, 0.88f);
        game.batch.draw(pixel, monitorBounds.x, monitorBounds.y, monitorBounds.width, monitorBounds.height);
        game.batch.setColor(Color.WHITE);

        if (currentMiniGame != null) {
            currentMiniGame.render(game.batch, pixel, font, monitorBounds);
        }

        game.batch.end();
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
