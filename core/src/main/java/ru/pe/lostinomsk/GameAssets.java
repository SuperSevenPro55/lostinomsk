package ru.pe.lostinomsk;

/**
 * Имена файлов для загрузки через {@link com.badlogic.gdx.Gdx#files} (internal).
 * <p>
 * Собираем пути в одном месте, чтобы при переименовании ресурсов править только этот класс.
 */
public final class GameAssets {

    private GameAssets() {
    }

    /** Фоновое изображение комнаты (PNG). */
    public static final String BACKGROUND_TEXTURE = "room.png";

    /**
     * Фоновая музыка в формате OGG Vorbis — рекомендуемый для LibGDX формат.
     * <p>
     * MP3 в LibGDX декодируется через JLayer; многие файлы с расширением .mp3 не читаются
     * (другой контейнер, нестандартный поток), что даёт {@code GdxRuntimeException: Empty MP3}.
     * Конвертация: {@code ffmpeg -i source.mp3 -c:a libvorbis background.ogg}
     */
    public static final String BACKGROUND_MUSIC = "background.ogg";
}
