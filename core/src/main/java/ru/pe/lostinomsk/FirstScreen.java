package ru.pe.lostinomsk;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

// Главная сцена
public class FirstScreen implements Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Music backgroundMusic;

    private Texture bgTower;
    private Texture bgTowerBlink;
    private Texture bgWall;
    private Texture bgTables;
    private Texture bgDevices;
    private Texture switcherUpper;
    private Texture switcherLower;

    private ShapeRenderer shapeRenderer;
    private Rectangle radioBounds;
    private Rectangle compBound;

    private Vector3 touchPoint;

    private final float WORLD_WIDTH = 240;
    private final float WORLD_HEIGHT = 135;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(GameAssets.BACKGROUND_MUSIC));

        touchPoint = new Vector3();

        // Текстуры
        bgTower = new Texture("mainScene/tower1.png");
        bgTowerBlink = new Texture("mainScene/tower2.png");
        bgWall = new Texture("mainScene/wall.png");
        bgTables = new Texture("mainScene/tables.png");
        bgDevices = new Texture("mainScene/devices.png");
        switcherUpper = new Texture("mainScene/switchUpper.png");
        switcherLower = new Texture("mainScene/switchLower.png");

        bgTower.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bgTowerBlink.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bgWall.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bgTables.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bgDevices.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        switcherUpper.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        switcherLower.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        radioBounds = new Rectangle();
        shapeRenderer = new ShapeRenderer();

        // Музыка
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.05f);
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        // organize code into three methods
        // input();
        logic();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height, true); // true centers the camera

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Освобождаем нативные и видеопамять: текстуры, OpenAL Music, SpriteBatch.
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
            backgroundMusic = null;
        }
        if (spriteBatch != null) {
            spriteBatch.dispose();
            spriteBatch = null;
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
            shapeRenderer = null;
        }
    }

    private void logic() {
        // Игровая логика (ввод, столкновения и т.д.).
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        // store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(bgTower, 0, 0, worldWidth, worldHeight); // draw the background
        spriteBatch.draw(bgWall, 0, 0, worldWidth, worldHeight);
        spriteBatch.draw(bgTables, 0, 0, worldWidth, worldHeight);
        spriteBatch.draw(bgDevices, 0, 0, worldWidth, worldHeight);
        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(radioBounds.x, radioBounds.y, radioBounds.width, radioBounds.height);

        shapeRenderer.end();
    }
}
