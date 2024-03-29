package com.goeey.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayerUtils {
    // Creating an arc and designating points by setting the arc parameters
    // The start angle of the arc in degrees
    static float startAngle = -30; 
    // The sweep angle of the arc in degrees
    static float sweepAngle = -120; 
    private static float scrWidth = Gdx.graphics.getWidth();
    private static float scrHeight= Gdx.graphics.getHeight();

    public static float calcXPos(float slotNum, int numPlayers){
        float radius = Math.min(scrWidth, scrHeight) / 1.4f; 
        // The x coordinate of the arc's center
        float centerX = scrWidth / 2f; 
        float angle = MathUtils.lerpAngleDeg(startAngle, startAngle + sweepAngle,
                slotNum / (float) (numPlayers-1));
        // Use cosine and sine to calculate diagonal offset from center
        return centerX + MathUtils.cosDeg(angle) * radius;
    }
    public static float calcYPos(float slotNum, int numPlayers){
        float radius = Math.min(scrWidth, scrHeight) / 1.4f; 
        // The y coordinate of the arc's center
        float centerY = scrHeight / 1.1f; 
        float angle = MathUtils.lerpAngleDeg(startAngle, startAngle + sweepAngle,
                slotNum / (float) (numPlayers-1));
        // Use cosine and sine to calculate diagonal offset from center
        return centerY + MathUtils.sinDeg(angle) * radius;
    }
    public static Table createButtonLabel(Skin skin, int posX , int posY , int playerNum){
        //Create table
        Table buttonContainer = new Table(skin);
        buttonContainer.setTransform(true);

        //Player Name
        String lbString = "Player "+ playerNum;
        Label lb = new Label(lbString, skin);
        lb.setFontScale(1);

        //Order
        buttonContainer.add(lb);
        buttonContainer.row().pad(10);
        buttonContainer.setOrigin(50, 25);
        buttonContainer.setPosition(posX, posY);
        return buttonContainer;
    }
}
