package ru.pe.lostinomsk.objects.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class ReactionMiniGame implements MiniGame {
    private float cursorX;
    private float speed;
    private float zoneX;

    private int successCount;
    private boolean finished;

    @Override
    public void start(Rectangle bounds) {
        cursorX = bounds.x + 14;
        speed = 70f;
        zoneX = randomZoneX(bounds);
        successCount = 0;
        finished = false;
    }

    @Override
    public void update(float delta, Rectangle bounds) {
        float barX = bounds.x + 12;
        float barW = bounds.width - 24;

        cursorX += speed * delta;

        if (cursorX <= barX) {
            cursorX = barX;
            speed = -speed;
        }

        if (cursorX >= barX + barW) {
            cursorX = barX + barW;
            speed = -speed;
        }
    }

    @Override
    public void render(SpriteBatch batch, Texture pixel, BitmapFont font, Rectangle bounds) {
        float barX = bounds.x + 12;
        float barY = bounds.y + bounds.height / 2f - 4;
        float barW = bounds.width - 24;
        float barH = 8;
        float zoneW = 10;

        font.getData().setScale(0.15f);
        font.draw(batch, "Click in green zone: " + successCount + "/3",
            bounds.x + 8, bounds.y + bounds.height - 8);
        font.getData().setScale(0.5f);

        batch.setColor(Color.DARK_GRAY);
        batch.draw(pixel, barX, barY, barW, barH);

        batch.setColor(Color.GREEN);
        batch.draw(pixel, zoneX, barY, zoneW, barH);

        batch.setColor(Color.WHITE);
        batch.draw(pixel, cursorX - 1, barY - 4, 2, barH + 8);

        batch.setColor(Color.WHITE);
    }

    @Override
    public void touchDown(float x, float y, Rectangle bounds) {
        if (finished) return;

        float zoneW = 28;

        if (cursorX >= zoneX && cursorX <= zoneX + zoneW) {
            successCount++;
            speed *= 1.12f;
        } else {
            successCount = 0;
            speed = speed > 0 ? 70f : -70f;
        }

        zoneX = randomZoneX(bounds);

        if (successCount >= 3) {
            finished = true;
        }
    }

    private float randomZoneX(Rectangle bounds) {
        float barX = bounds.x + 12;
        float barW = bounds.width - 24;
        float zoneW = 5;
        return MathUtils.random(barX, barX + barW - zoneW);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
