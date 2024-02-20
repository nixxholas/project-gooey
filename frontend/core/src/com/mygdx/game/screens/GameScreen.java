package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Boot;
import com.mygdx.game.objects.*;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class GameScreen extends ScreenAdapter {
    final Boot game;
    private Texture backImage;
    private Texture frontImage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;

    private int cWidth;
    private int cHeight;

    private BitmapFont font;

    public GameScreen(Boot game) {
        this.game = game;
        this.camera = game.camera;
    }

    public void dealVertCards(float delay, int xPos, int yPos, int offset, int rotation){
        Card newC = new Card(backImage);
        newC.setPosition(900, 900);
        stage.addActor(newC);
        SequenceAction sa = newC.cardAnimation(delay, xPos, yPos + offset, rotation, 0.5f, frontImage);
        newC.addAction(sa);
    }

    public void dealHorizCards(float delay, int xPos, int yPos, int offset, int rotation){
        Card newC = new Card(backImage);
        newC.setPosition(900, 900);
        stage.addActor(newC);
        SequenceAction sa = newC.cardAnimation(delay, xPos+ offset, yPos , rotation, 0.5f, frontImage);
        newC.addAction(sa);
    }

    public void createLabel(){
        
    }

    public void createButton(){

    }

    public void createPlayerSet(){
        dealVertCards(cHeight, cHeight, cHeight, cWidth, cHeight);
        createLabel();
        createButton();
    }

    @Override
    public void show() {
        backImage = new Texture("back_card_150.png");
        frontImage = new Texture("2_of_clubs.png");

        cWidth = frontImage.getWidth() + 5;
        cHeight = frontImage.getHeight() + 5;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        final Card cardBACK = new Card(backImage);
        cardBACK.setPosition(900, 900);
        stage.addActor(cardBACK);

        // PLAYER 1

        //deal 6 cards, with starting 0 delay, coord of 1900,1100 offset by cdwidth*2 and rotated 90 degrees
        for(int i=0; i<=5; i++){
            dealVertCards(2.0f * i, 1900, 1000 - (cWidth*2), (cWidth * 1 / 5) * i, 90);
        }
        
        // Card card1 = new Card(backImage);
        // card1.setPosition(900, 900);
        // stage.addActor(card1);
        // SequenceAction sa = card1.cardAnimation(0, 1900, 1100 - (cWidth * 2), 90, 0.5f, frontImage);
        // card1.addAction(sa);

        // Card card2 = new Card(backImage);
        // card2.setPosition(900, 900);
        // stage.addActor(card2);
        // SequenceAction sa2 = card2.cardAnimation(2.0f, 1900, 1100 - (cWidth * 2) + (cWidth * 1 / 5), 90, 0.5f,
        //         frontImage);
        // card2.addAction(sa2);

        // Card card3 = new Card(backImage);
        // card3.setPosition(900, 900);
        // stage.addActor(card3);
        // SequenceAction sa3 = card3.cardAnimation(4.0f, 1900, 1100 - (cWidth * 2) + (cWidth * 2 / 5), 90, 0.5f,
        //         frontImage);
        // card3.addAction(sa3);

        // Card card4 = new Card(backImage);
        // card4.setPosition(900, 900);
        // stage.addActor(card4);
        // SequenceAction sa4 = card4.cardAnimation(6.0f, 1900, 1100 - (cWidth * 2) + (cWidth * 3 / 5), 90, 0.5f,
        //         frontImage);
        // card4.addAction(sa4);

        // Card card5 = new Card(backImage);
        // card5.setPosition(900, 900);
        // stage.addActor(card5);
        // SequenceAction sa5 = card5.cardAnimation(8.0f, 1900, 1100 - (cWidth * 2) + (cWidth * 4 / 5), 90, 0.5f,
        //         frontImage);
        // card5.addAction(sa5);

        // Card card6 = new Card(backImage);
        // card6.setPosition(900, 900);
        // stage.addActor(card6);
        // SequenceAction sa6 = card6.cardAnimation(10.0f, 1900, 1100 - (cWidth * 2) + (cWidth * 5 / 5), 90, 0.5f,
        //         frontImage);
        // card6.addAction(sa6);

        // PLAYER 1 END

        // PLAYER 1 Labels & TextButton


        Table buttonContainer = new Table(skin);
        buttonContainer.setTransform(true);
        Label lb = new Label("Player 1", skin);
        lb.setFontScale(1);
        buttonContainer.add(lb);
        buttonContainer.row().pad(10);
        TextButton tb = new TextButton("Hit", skin);
        tb.setDisabled(true);
        buttonContainer.add(tb).size(100, 50);
        Table rotatingActor = buttonContainer;
        rotatingActor.setRotation(90);
        rotatingActor.setPosition(1600, 1000 - cWidth);
        stage.addActor(rotatingActor);

        // PLAYER 2
        //deal 6 cards, with starting 12.0f delay, coord of 1900,1100 offset by cdwidth*1/5 and rotated 90 degrees
        for(int i=0; i<=5; i++){
            dealVertCards(12.0f + 2.0f * i, 1900, 1100 - (cWidth*5), (cWidth * 1 / 5) * i, 90);
        }

        // PLAYER 2 Labels & TextButton
        Table buttonContainer2 = new Table(skin);
        buttonContainer2.setTransform(true);
        Label lb2 = new Label("Player 2", skin);
        lb2.setFontScale(1);
        buttonContainer2.add(lb2);
        buttonContainer2.row().pad(10);
        TextButton tb2 = new TextButton("Hit", skin);
        tb2.setDisabled(true);
        buttonContainer2.add(tb2).size(100, 50);
        Table rotatingActor2 = buttonContainer2;
        rotatingActor2.setRotation(90);
        rotatingActor2.setPosition(1600, 1100 - (cWidth * 4));
        stage.addActor(rotatingActor2);

        // PLAYER 3
        //deal 6 cards, with starting 24.0f delay, coord of 1100,50 offset horizontally by cdwidth*1/5 and rotated 0 degrees
        for(int i=0; i<=5; i++){
            dealHorizCards(24.0f + 2.0f * i, 1100, 50, (cWidth * 1 / 5) * i, 0);
        }

        // PLAYER 3 Labels & TextButton
        Table buttonContainer3 = new Table(skin);
        buttonContainer3.setTransform(true);
        Label lb3 = new Label("Player 3", skin);
        lb3.setFontScale(1);
        buttonContainer3.add(lb3);
        buttonContainer3.row().pad(10);
        TextButton tb3 = new TextButton("Hit", skin);
        tb3.setDisabled(true);
        buttonContainer3.add(tb3).size(100, 50);
        Table rotatingActor3 = buttonContainer3;
        rotatingActor3.setRotation(0);
        rotatingActor3.setPosition(1250, 310);
        stage.addActor(rotatingActor3);

        // Player 4
        //deal 6 cards, with starting 36.0f delay, coord of 500,50 offset horizontally by cdwidth*1/5 and rotated 0 degrees
        for(int i=0; i<=5; i++){
            dealHorizCards(36.0f + 2.0f * i, 500, 50, (cWidth * 1 / 5) * i, 0);
        }

        // PLAYER 4 Labels & TextButton
        Table buttonContainer4 = new Table(skin);
        buttonContainer4.setTransform(true);
        Label lb4 = new Label("Player 4", skin);
        lb4.setFontScale(1);
        buttonContainer4.add(lb4);
        buttonContainer4.row().pad(10);
        TextButton tb4 = new TextButton("Hit", skin);
        tb4.setDisabled(true);
        buttonContainer4.add(tb4).size(100, 50);
        Table rotatingActor4 = buttonContainer4;
        rotatingActor4.setRotation(0);
        rotatingActor4.setPosition(650, 310);
        stage.addActor(rotatingActor4);

        // Player 5
        //deal 6 cards, with starting 48.0f delay, coord of 50,600 offset by -cdwidth*1/5 and rotated -90 degrees
        for(int i=0; i<=5; i++){
            dealVertCards(48.0f + 2.0f * i, 50, 600, -(cWidth * 1 / 5) * i, -90);
        }

        // PLAYER 5 Labels & TextButton
        Table buttonContainer5 = new Table(skin);
        buttonContainer5.setTransform(true);
        Label lb5 = new Label("Player 5", skin);
        lb5.setFontScale(1);
        buttonContainer5.add(lb5);
        buttonContainer5.row().pad(10);
        TextButton tb5 = new TextButton("Hit", skin);
        tb5.setDisabled(true);
        buttonContainer5.add(tb5).size(100, 50);
        Table rotatingActor5 = buttonContainer5;
        rotatingActor5.setRotation(-90);
        rotatingActor5.setPosition(330, 1100 - (cWidth * 4));
        stage.addActor(rotatingActor5);

        // Player 6
        //deal 6 cards, with starting 48.0f delay, coord of 50,600 offset by -cdwidth*1/5 and rotated -90 degrees
        for(int i=0; i<=5; i++){
            dealVertCards(60.0f + 2.0f * i, 50, 1000, -(cWidth * 1 / 5) * i, -90);
        }

        // PLAYER 6 Labels & TextButton
        Table buttonContainer6 = new Table(skin);
        buttonContainer6.setTransform(true);
        Label lb6 = new Label("Player 6", skin);
        lb6.setFontScale(1);
        buttonContainer6.add(lb6);
        buttonContainer6.row().pad(10);
        TextButton tb6 = new TextButton("Hit", skin);
        tb6.setDisabled(true);
        buttonContainer6.add(tb6).size(100, 50);
        Table rotatingActor6 = buttonContainer6;
        rotatingActor6.setRotation(-90);
        rotatingActor6.setPosition(330, 1000 - cWidth);
        stage.addActor(rotatingActor6);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.3f, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        backImage.dispose();
        frontImage.dispose();
        stage.dispose();
    }

}