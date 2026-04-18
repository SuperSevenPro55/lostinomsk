package ru.pe.lostinomsk.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import ru.pe.lostinomsk.Main;

public class RadioGroup extends Group {
    private int currentFreq = 0;

    private Image tumb1;
    private Image switchUp;

    public RadioGroup(Main game) {
        Texture tumbTexture = game.assets.manager.get("mainScene/tumb.png", Texture.class);
        Texture switchUpTexture = game.assets.manager.get("mainScene/switchUp.png", Texture.class);

        tumb1 = new Image(tumbTexture);
        switchUp = new Image(switchUpTexture);

        tumb1.setOrigin(Align.center);

        tumb1.setPosition(10, 10);
        switchUp.setPosition(10, 40);

        this.addActor(switchUp);
        this.addActor(tumb1);

        tumb1.addListener(new InputListener() {
           @Override
           public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
               currentFreq -= (int) Math.signum(amountY);

               currentFreq = Math.clamp(currentFreq, 0, 82);

                updateVisuals();
                return true;
           }

           @Override
           public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
               System.out.println("Мышь на крутилке");
           }
        });


    }

    private void updateVisuals() {
        tumb1.setRotation(currentFreq * -4.39f);

        switchUp.setY(40 + currentFreq);
    }
}
