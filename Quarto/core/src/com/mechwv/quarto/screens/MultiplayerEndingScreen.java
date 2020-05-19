package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;

public class MultiplayerEndingScreen implements Screen {

    private GameRoot game;
    private Texture board;
    private Texture winning;
    private ImageButton retry;
    private final Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Music SFXclick;


    public MultiplayerEndingScreen(final GameRoot game, int winner, int player){
        game.screenx = Gdx.graphics.getWidth();
        game.screeny = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        this.game = game;

        SFXclick = game.gm.getSFXClick();
        Texture texture = game.assets.manager.get(game.assets.play_again);
        TextureRegion textureRegion = new TextureRegion(texture);
        Drawable drawable = new TextureRegionDrawable(textureRegion);
        retry = new ImageButton(drawable);
        retry.setPosition(( game.virtual_screen_width/2- retry.getWidth()/2),( game.virtual_screen_width/2+200));
        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("restart", "CLICKAAAAA");
                SFXclick.play();
                game.gm.update();
                game.setScreen(new MainMenuScreen(game,false));
            }
        });
        board = game.gm.getEnd_board();
        if (winner == 3) {
            winning = game.gm.getDraw();
        }
        else {
            if (winner == player) {
                winning = game.gm.getYou_win();
            } else winning = game.gm.getYou_lose();
        }
        Gdx.input.setInputProcessor(stage);
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
        game.spriteBatch.draw(winning,40,game.virtual_screen_height/2+300);
        game.spriteBatch.end();
        stage.draw();
        backTo();
    }

    private void backTo() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.gm.update();
            game.setScreen(new MainMenuScreen(game, true));
            dispose();
        }
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
        stage.dispose();
    }
}

