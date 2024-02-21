package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Boot extends Game{
    
    public static Boot INSTANCE;
    private int screenWidth, screenHeight;
    public SpriteBatch batch;
    public OrthographicCamera camera;

    /*
     *  Viewports are used to change how the screen behaves when the window is resized.
     *  UI should be seen through ScreenViewport because we want the blackjack image, start button and Exit button
     *  to always be centered in the middle of the screen.
     *
     *  uiViewport is instantiated in the constructor of Boot
     *  uiViewport is passed into stages as a parameter
     *  uiViewport will create an Orthographic camera for itself
     *
     *  stage.getViewport().update resizes the viewport whenever resize() is called (aka a window resize happens).
     *  stage.getViewport().update() also ensures the UI stays in the center of the screen by setting centerCamera = true.
     *
     *   Check out how different viewports work in libGDX here:
     *   https://raeleus.github.io/viewports-sample-project/
     */
    public ScreenViewport uiViewport;

    /*
     *   ExtendViewPort behaviour: The world is first scaled to fit within the viewport,
     *   then the shorter dimension is lengthened to fill the viewport.
     *
     *   It is used for GameScreens because we want to:
     *   1. Maintain the aspect ratio of everything (cards, players, ...)
     *   2. Allow larger screens to display larger cards, and smaller screens to display smaller cards
     *   3. Display the entire table at ALL TIMES (Do not cut off parts of the table when resizing)
     *
     *   Check out how different viewports work in libGDX here:
     *   https://raeleus.github.io/viewports-sample-project/
     */
    public ExtendViewport gameViewport;

    public Boot(){
    }
    
    public void create(){
        this.batch = new SpriteBatch();
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, screenWidth, screenHeight);
        uiViewport = new ScreenViewport();
        gameViewport = new ExtendViewport(1920, 1080, 9999, 9999);
        // Sets screen to MainMenuScreen.java
        setScreen(new MainMenuScreen(this));
    }

    public void render() {
		super.render(); // important!
	}

    public int getscreenWidth(){
        return screenWidth;
    }

    public int getscreenHeight(){
        return screenHeight;
    }

}
