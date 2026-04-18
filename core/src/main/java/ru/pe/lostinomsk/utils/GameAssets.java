package ru.pe.lostinomsk.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class GameAssets implements Disposable {
    public final AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> MAIN_MENU_BACKGROUND =
        new AssetDescriptor<>("main_menu_background.png", Texture.class);

    public static final AssetDescriptor<Music> BACKGROUND_MUSIC =
        new AssetDescriptor<>("background.ogg", Music.class);

    public void loadAll() {
        manager.load(MAIN_MENU_BACKGROUND);
        manager.load(BACKGROUND_MUSIC);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
