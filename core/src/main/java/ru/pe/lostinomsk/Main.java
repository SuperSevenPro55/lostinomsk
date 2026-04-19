package ru.pe.lostinomsk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import ru.pe.lostinomsk.screens.MainMenuScreen;
import ru.pe.lostinomsk.utils.GameAssets;

public class Main extends Game {
    private static final String PATH_TO_FONT = "fonts/KONSTRUKT.otf";

    public SpriteBatch batch;
    public BitmapFont font;
    public GameAssets assets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = generateFont(PATH_TO_FONT);
        assets = new GameAssets();

        assets.loadAll();
        assets.manager.finishLoading();

        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        if (batch != null) batch.dispose();
        if (font != null) font.dispose();
        if (assets != null) assets.dispose();
    }

    private BitmapFont generateFont(String pathToFont) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(pathToFont));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        BitmapFont generatedFont = generator.generateFont(parameter);
        generator.dispose();

        return generatedFont;
    }
}
