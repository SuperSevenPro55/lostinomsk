package ru.pe.lostinomsk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import ru.pe.lostinomsk.screens.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private final String PATH_TO_FONT = "fonts/KONSTRUKT.otf";
    private BitmapFont font;

    @Override
    public void create() {
        font = setFont(PATH_TO_FONT);

        setScreen(new MainMenuScreen(this, font));

    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private BitmapFont setFont(String pathToFont) {
        // Шрифт
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(pathToFont));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }
}
