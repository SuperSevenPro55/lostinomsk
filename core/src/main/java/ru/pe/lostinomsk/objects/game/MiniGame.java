package ru.pe.lostinomsk.objects.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface MiniGame {
    void start(Rectangle bounds);
    void update(float delta, Rectangle bounds);
    void render(SpriteBatch batch, Texture pixel, BitmapFont font, Rectangle bounds);
    void touchDown(float x, float y, Rectangle bounds);
    boolean isFinished();
}
