package ru.pe.lostinomsk.objects.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class CatchDotMiniGame implements MiniGame {
    private float dotX;
    private float dotY;
    private float speedX;
    private float speedY;
    private final float size = 8f;

    private int score;
    private boolean finished;

    @Override
    public void start(Rectangle bounds) {
        dotX = MathUtils.random(bounds.x + 6, bounds.x + bounds.width - size - 6);
        dotY = MathUtils.random(bounds.y + 6, bounds.y + bounds.height - size - 18);
        speedX = 55f;
        speedY = 43f;
        score = 0;
        finished = false;
    }

    @Override
    public void update(float delta, Rectangle bounds) {
        dotX += speedX * delta;
        dotY += speedY * delta;

        if (dotX <= bounds.x + 2 || dotX + size >= bounds.x + bounds.width - 2) {
            speedX = -speedX;
        }

        if (dotY <= bounds.y + 2 || dotY + size >= bounds.y + bounds.height - 18) {
            speedY = -speedY;
        }
    }

    @Override
    public void render(SpriteBatch batch, Texture pixel, BitmapFont font, Rectangle bounds) {
        font.getData().setScale(0.6f);
        font.draw(batch, "Catch red dot: " + score + "/5", bounds.x + 8, bounds.y + bounds.height - 8);
        font.getData().setScale(1f);

        batch.setColor(Color.RED);
        batch.draw(pixel, dotX, dotY, size, size);
        batch.setColor(Color.WHITE);
    }

    @Override
    public void touchDown(float x, float y, Rectangle bounds) {
        if (finished) return;

        if (x >= dotX && x <= dotX + size && y >= dotY && y <= dotY + size) {
            score++;

            dotX = MathUtils.random(bounds.x + 6, bounds.x + bounds.width - size - 6);
            dotY = MathUtils.random(bounds.y + 6, bounds.y + bounds.height - size - 18);

            if (score >= 5) {
                finished = true;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
