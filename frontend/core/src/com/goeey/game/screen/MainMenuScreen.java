package com.goeey.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.goeey.game.GameManager;
import com.goeey.game.socket.SocketHandler;
import org.java_websocket.enums.ReadyState;

import java.net.Socket;

public class MainMenuScreen extends ScreenAdapter {
    private final GameManager game;
    private Stage stage;

    public MainMenuScreen(GameManager game) {
        this.game = game;
    }

    public Table uiTableFactory() {
        // Logo
        Image logoImage = new Image(new Texture("images/blackjacklogo.png"));

        // TextButton
        TextButton startButton = new TextButton("Start Game", game.getSkin());
        TextButton exitButton = new TextButton("Exit to Desktop", game.getSkin());
        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                connectToServer();
                game.setScreen(new GameCreationScreen(game));
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                System.exit(-1);
            }
        });

        // Prepare Table
        Table uiTable = new Table();
        uiTable.setFillParent(true);
        uiTable.add(logoImage);
        uiTable.row();
        uiTable.add(startButton).width(500).height(100).padTop(50);
        uiTable.row();
        uiTable.add(exitButton).width(500).height(100).padTop(50);

        return uiTable;
    }

    public void show() {
        stage = new Stage();
        stage.setViewport(game.gameViewPort); // can i public static final this?
        Gdx.input.setInputProcessor(stage);

        stage.addActor(uiTableFactory());
    }

    public void connectToServer() {
        while(GameManager.socketHandler.getState() != ReadyState.OPEN) {
            ScreenUtils.clear(0, 0, 0, 0.7f);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.28f, 0.31f, 0.60f, 1);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
