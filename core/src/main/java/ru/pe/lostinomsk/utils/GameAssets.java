package ru.pe.lostinomsk.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

public class GameAssets implements Disposable {
    public final AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> DEVICES2 =
        new AssetDescriptor<>("mainScene/devices2.png", Texture.class);

    public static final AssetDescriptor<BitmapFont> MAIN_FONT =
        new AssetDescriptor<>("fonts/KONSTRIKT.otf", BitmapFont.class);

    public static final AssetDescriptor<Texture> MAIN_MENU_BACKGROUND =
        new AssetDescriptor<>("main_menu_background.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_TOWER =
        new AssetDescriptor<>("mainScene/tower1.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_TOWER_ON =
        new AssetDescriptor<>("mainScene/tower2.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_WALL =
        new AssetDescriptor<>("mainScene/wall.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_TABLES =
        new AssetDescriptor<>("mainScene/tables.png", Texture.class);

    public static final AssetDescriptor<Texture> DEVICES =
        new AssetDescriptor<>("mainScene/devices.png", Texture.class);

    public static final AssetDescriptor<Texture> SIGNAL_RED =
        new AssetDescriptor<>("mainScene/redSignal.png", Texture.class);

    public static final AssetDescriptor<Texture> SIGNAL_GREEN =
        new AssetDescriptor<>("mainScene/redSignal.png", Texture.class);

    public static final AssetDescriptor<Texture> SWITCH_LOWER =
        new AssetDescriptor<>("mainScene/switchLow.png", Texture.class);

    public static final AssetDescriptor<Texture> SWITCH_UPPER =
        new AssetDescriptor<>("mainScene/switchUp.png", Texture.class);

    public static final AssetDescriptor<Texture> THUMBLER =
        new AssetDescriptor<>("mainScene/tumb.png", Texture.class);


    public static final AssetDescriptor<Music> BACKGROUND_MUSIC =
        new AssetDescriptor<>("background.ogg", Music.class);

    public void loadAll() {
        TextureLoader.TextureParameter pixelAllParams = new TextureLoader.TextureParameter();

        pixelAllParams.minFilter = Texture.TextureFilter.Nearest;
        pixelAllParams.magFilter = Texture.TextureFilter.Nearest;

        manager.load("mainScene/devices.png", Texture.class, pixelAllParams);
        manager.load("mainScene/greenSignal.png", Texture.class, pixelAllParams);
        manager.load("mainScene/redSignal.png", Texture.class, pixelAllParams);
        manager.load("mainScene/switchLow.png", Texture.class, pixelAllParams);
        manager.load("mainScene/switchUp.png", Texture.class, pixelAllParams);
        manager.load("mainScene/tables.png", Texture.class, pixelAllParams);
        manager.load("mainScene/tower1.png", Texture.class, pixelAllParams);
        manager.load("mainScene/tumb.png", Texture.class, pixelAllParams);
        manager.load("mainScene/wall.png", Texture.class, pixelAllParams);
        manager.load("mainScene/tower2.png", Texture.class, pixelAllParams);

        manager.load(DEVICES2);
        manager.load(MAIN_MENU_BACKGROUND);
        manager.load(BACKGROUND_MUSIC);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
