package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
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

public class EndingScreen implements Screen {

    private GameRoot game;
    private Texture board;
    private Texture winning;
    private ImageButton retry;
    private final Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private int winner;
    private Music SFXclick;

    public EndingScreen(final GameRoot game, int winner){
        this.winner = winner;
        game.screenx = Gdx.graphics.getWidth();
        game.screeny = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
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
                SFXclick.play();
                game.gm.update();
                game.setScreen(new MainMenuScreen(game,false));
            }
        });
        board = game.gm.getEnd_board();
        if (winner == 1)
            winning = game.gm.getPlayer_1_won();
        if (winner == 2)
            winning = game.gm.getPlayer_2_won();
        if (winner == 3)
            winning = game.gm.getDraw();
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
        if (winner != 3)
            game.spriteBatch.draw(winning,200,game.virtual_screen_height/2+300);
        else
            game.spriteBatch.draw(winning,40,game.virtual_screen_height/2+300);
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
