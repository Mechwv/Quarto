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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;

import io.socket.client.Socket;

public class MultiplayerScreen implements Screen {



    private GameRoot game;
    private final Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private Music gameplayMusic;

    private boolean match_status;

    private io.socket.client.Socket socket;
    private final Button singlePButton;


    public MultiplayerScreen(final GameRoot game, final Socket socket){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        wooden_field = game.gm.getWooden_field();
        wooden_field = game.assets.manager.get(game.assets.TEST);

        Texture myTexture = game.assets.manager.get(game.assets.singleplayer);
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        Drawable drawable = new TextureRegionDrawable(myTextureRegion);
        singlePButton = new ImageButton(drawable);
        singlePButton.setPosition((100),(1060));
        singlePButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("SocketIO", "click");
                game.setScreen(new MainMenuScreen(game));
                socket.disconnect();
                dispose();
            }
        });
        stage.addActor(singlePButton);
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
        game.spriteBatch.draw(wooden_field,0,0);
        game.font.draw(game.spriteBatch, "KEKWI BL9DASDASDASDASD",300,1000);
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
