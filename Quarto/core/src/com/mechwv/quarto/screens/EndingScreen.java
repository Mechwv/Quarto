package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;

public class EndingScreen implements Screen {

    private GameRoot game;
    private Texture board;
    private Texture winning;
    private ImageButton retry;
    private final Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;


    public EndingScreen(final GameRoot game, boolean player_1_won){
        game.screenx = Gdx.graphics.getWidth();
        game.screeny = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        retry = game.gm.getRetry();
        board = game.gm.getEnd_board();
        if (player_1_won)
            winning = game.gm.getPlayer_1_won();
        else winning = game.gm.getPlayer_2_won();

            stage.addActor(retry);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(board,0,0);
        game.spriteBatch.draw(winning,200,game.virtual_screen_height/2+300);
        game.spriteBatch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}