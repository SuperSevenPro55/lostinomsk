package ru.pe.lostinomsk.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import ru.pe.lostinomsk.Main;
import ru.pe.lostinomsk.utils.Signal;

public class RadioGroup extends Group {
    private int currentFreq = 0;
    private int currentLong = 0;

    private Signal targetSignal;
    private Runnable onSignalCaught; // Код, который мы выполним при победе
    private boolean isCaught = false;

    private Image tumb1;
    private Image switchUp;

    private Image tumb2;
    private Image switchLow;

    private Image redLight1;
    private Image redLight2;
    private Image greenLight1;
    private Image greenLight2;
    private Music scrl;
    private Music rightAnswer;

    public RadioGroup(Main game) {
        this.onSignalCaught = onSignalCaught;
        scrl= Gdx.audio.newMusic(Gdx.files.internal("sound/turn-on.mp3"));
        scrl.setLooping(false);
        scrl.setVolume(0.5f);
        rightAnswer= Gdx.audio.newMusic(Gdx.files.internal("sound/right-answer.mp3"));
        rightAnswer.setLooping(false);
        rightAnswer.setVolume(0.5f);
        targetSignal = new Signal();
        targetSignal.newSignal();

        Texture tumbTexture = game.assets.manager.get("mainScene/tumb.png", Texture.class);
        Texture switchUpTexture = game.assets.manager.get("mainScene/switchUp.png", Texture.class);
        Texture switchLowTexture = game.assets.manager.get("mainScene/switchLow.png", Texture.class);
        Texture redLightTexture = game.assets.manager.get("mainScene/redSignal.png", Texture.class);
        Texture greenLightTexture = game.assets.manager.get("mainScene/greenSignal.png", Texture.class);

        tumb1 = new Image(tumbTexture);
        switchUp = new Image(switchUpTexture);
        tumb2 = new Image(tumbTexture);
        switchLow = new Image(switchLowTexture);

        redLight1 = new Image(redLightTexture);
        redLight2 = new Image(redLightTexture);
        greenLight1 = new Image(greenLightTexture);
        greenLight2 = new Image(greenLightTexture);

        tumb1.setOrigin(Align.center);
        tumb2.setOrigin(Align.center);

        tumb1.setPosition(0, 0);
        switchUp.setPosition(0, 18);
        tumb2.setPosition(10, 0);
        switchLow.setPosition(0, 10);

        redLight1.setPosition(60, 0);
        greenLight1.setPosition(60, 0);
        redLight2.setPosition(70, 0);
        greenLight2.setPosition(70, 0);

        greenLight1.setVisible(false);
        greenLight2.setVisible(false);

        this.addActor(switchUp);
        this.addActor(tumb1);
        this.addActor(switchLow);
        this.addActor(tumb2);
        this.addActor(redLight1);
        this.addActor(greenLight1);
        this.addActor(redLight2);
        this.addActor(greenLight2);

        tumb1.addListener(new InputListener() {
            @Override
            public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
                scrl.play();
                currentFreq -= (int) Math.signum(amountY);
                currentFreq = MathUtils.clamp(currentFreq, 0, 82); // Используем MathUtils из GDX
                updateVisuals();
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                 if (event.getStage() != null) event.getStage().setScrollFocus(tumb1);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Забираем фокус, когда мышь ушла
                if (event.getStage() != null) {
                    event.getStage().setScrollFocus(null);
                }
            }
        });

        tumb2.addListener(new InputListener() {
            @Override
            public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
                scrl.play();
                currentLong -= (int) Math.signum(amountY);
                currentLong = MathUtils.clamp(currentLong, 0, 82); // Ограничиваем от 0 до 82
                updateVisuals();
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Передаем фокус второй крутилке!
                if (event.getStage() != null) event.getStage().setScrollFocus(tumb2);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (event.getStage() != null) event.getStage().setScrollFocus(null);
            }
        });
    }

    private void updateVisuals() {
        if (isCaught) return;

        boolean isFreqCorrect = (currentFreq == targetSignal.getFrequencySignal());
        boolean isLongCorrect = (currentLong == targetSignal.getLongitudeSignal());

        greenLight1.setVisible(isFreqCorrect);
        redLight1.setVisible(!isFreqCorrect);
        greenLight2.setVisible(isLongCorrect);
        redLight2.setVisible(!isLongCorrect);

        tumb1.setRotation(currentFreq * -4.39f);
        switchUp.setX(currentFreq);

        tumb2.setRotation(currentLong * -4.39f);
        switchLow.setX(currentLong);

        if (targetSignal.checkSignal(currentFreq, currentLong)) {
            isCaught = true;
            if (onSignalCaught != null) {
                rightAnswer.play();
                onSignalCaught.run();
            }
        }
    }

    public void resetRadio() {
        isCaught = false; // Снимаем блокировку

        targetSignal.newSignal();

        updateVisuals();
    }

    public void setOnSignalCaught(Runnable onSignalCaught) {
        this.onSignalCaught = onSignalCaught;
    }
}
