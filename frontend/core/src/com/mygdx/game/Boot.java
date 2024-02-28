package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.screens.MainMenuScreen;
import jdk.tools.jmod.Main;

public class Boot extends Game{
    private String playerName;
    private int screenWidth, screenHeight;
    public Skin skin;
    public SpriteBatch batch;

    /*
     *   FitViewport behaviour: The world is first scaled to fit within the viewport
     *
     *   It is used because we want to:
     *   1. Maintain the aspect ratio of everything (cards, players, ...)
     *   2. Allow larger screens to display larger cards, and smaller screens to display smaller cards
     *   3. Display the entire table at ALL TIMES (Do not cut off parts of the table when resizing)
     *
     *   Check out how different viewports work in libGDX here:
     *   https://raeleus.github.io/viewports-sample-project/
     *
     *  gameViewport is instantiated in the constructor of Boot
     *  gameViewport is passed into stages as a parameter
     *  gameViewport will create an Orthographic camera for itself
     *
     *  stage.getViewport().update resizes the viewport whenever resize() is called (aka a window resize happens).
     *  stage.getViewport().update() also ensures the UI stays in the center of the screen by setting centerCamera = true.
     */
    public FitViewport gameViewport;
    
    public void create(){
        this.batch = new SpriteBatch();
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        gameViewport = new FitViewport(1920, 1080);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        /*
         *  Sets screen to MainMenuScreen.java
         *  The screen will be displayed when render() is called
         */
        setScreen(new MainMenuScreen(this));
    }

    public void render() {
        /*
        *   super.render() is IMPORTANT because Game will call the render() function in screen if it exists.
        *
        *   Game class source code for render():
        *   @Override
        *   public void render () {
		*   if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
	    *   }
        */
		super.render();
	}

    public int getscreenWidth(){
        return screenWidth;
    }

    public int getscreenHeight(){
        return screenHeight;
    }

    public void setPlayerName(String playerName) {this.playerName = playerName;}
    public String getPlayerName() {return playerName;}

}
