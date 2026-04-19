package ru.pe.lostinomsk.objects.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Locale;

public class CodeDecryptMiniGame implements MiniGame {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final String[] WORDS = {
        "кот", "дом", "окно", "мир", "игра",
        "лампа", "книга", "стол", "река", "город"
    };

    private String originalWord;
    private String encryptedWord;

    private final StringBuilder input = new StringBuilder();

    private boolean finished;
    private boolean wrongAnswer;

    @Override
    public void start(Rectangle bounds) {
        originalWord = WORDS[MathUtils.random(WORDS.length - 1)];
        encryptedWord = encryptWord(originalWord);

        input.setLength(0);
        finished = false;
        wrongAnswer = false;
    }

    @Override
    public void update(float delta, Rectangle bounds) {
    }

    @Override
    public void render(SpriteBatch batch, Texture pixel, BitmapFont font, Rectangle bounds) {
        // Заголовок
        font.getData().setScale(0.15f);
        font.draw(batch, "Расшифруйте слово:", bounds.x + 6, bounds.y + bounds.height - 6);

        // Зашифрованное слово
        font.getData().setScale(0.2f);
        font.draw(batch, encryptedWord, bounds.x + 6, bounds.y + bounds.height - 16);

        // Поле ввода
        float fieldX = bounds.x + 6;
        float fieldY = bounds.y + 10;
        float fieldW = bounds.width - 12;
        float fieldH = 12;

        batch.setColor(Color.LIGHT_GRAY);
        batch.draw(pixel, fieldX, fieldY, fieldW, fieldH);

        batch.setColor(Color.BLACK);
        batch.draw(pixel, fieldX + 1, fieldY + 1, fieldW - 2, fieldH - 2);

        batch.setColor(Color.WHITE);

        font.getData().setScale(0.15f);

        String shownText = input.length() == 0 ? "_" : input.toString();
        font.draw(batch, shownText, fieldX + 3, fieldY + 9);

        if (wrongAnswer && !finished) {
            font.getData().setScale(0.1f);
            font.draw(batch, "Неверный ответ", bounds.x + 6, bounds.y + 28);
        }

        if (finished) {
            font.getData().setScale(0.1f);
            font.draw(batch, "Правильно!", bounds.x + 6, bounds.y + 28);
        }

        font.getData().setScale(1f);
    }

    @Override
    public void touchDown(float x, float y, Rectangle bounds) {
        // Клик здесь не нужен
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    public boolean keyTyped(char character) {
        if (finished) return false;

        // backspace
        if (character == 8) {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
            }
            wrongAnswer = false;
            return true;
        }

        // enter
        if (character == '\r' || character == '\n') {
            submitCurrentInput();
            return true;
        }

        char lower = Character.toLowerCase(character);

        if (ALPHABET.indexOf(lower) != -1) {
            input.append(lower);
            wrongAnswer = false;
            return true;
        }

        return false;
    }

    public boolean submitCurrentInput() {
        String answer = input.toString().trim().toLowerCase(Locale.ROOT);

        if (answer.equals(originalWord)) {
            finished = true;
            wrongAnswer = false;
            return true;
        }

        wrongAnswer = true;
        return false;
    }

    public static String encryptWord(String word) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Слово пустое");
        }

        String normalized = word.trim().toLowerCase(Locale.ROOT);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < normalized.length(); i++) {
            char ch = normalized.charAt(i);
            int index = ALPHABET.indexOf(ch);

            if (index == -1) {
                throw new IllegalArgumentException("Недопустимый символ: " + ch);
            }

            if (i > 0) {
                result.append(".");
            }

            result.append(Integer.toBinaryString(index + 1));
        }

        return result.toString();
    }
}
