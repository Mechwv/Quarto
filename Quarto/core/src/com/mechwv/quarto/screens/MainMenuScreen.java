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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;

public class MainMenuScreen implements Screen {

    private GameRoot game;
    private final Stage stage;

    //Parameter for drawing the buttons
    private OrthographicCamera camera;
    private Viewport viewport;
    private final Button singlePButton;
    private final ImageButton multiPButton;

    //Parameter for Sound
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private Texture background;
    private Music SFXClick;

    private boolean back = true;


    public MainMenuScreen(final GameRoot game, boolean backpressed){

        //Set up our assets
        this.game = game;

        Gdx.input.setCatchKey(Input.Keys.BACK,true);
        game.screenx = Gdx.graphics.getWidth();
        game.screeny = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);

        if (backpressed){
            back = false;
        }
        Gdx.input.setInputProcessor(stage);

        myTexture = game.assets.manager.get(game.assets.multiplayer);
        myTextureRegion = new TextureRegion(myTexture);
        Drawable drawable = new TextureRegionDrawable(myTextureRegion);
        singlePButton = new ImageButton(drawable);
        singlePButton.setDisabled(true);
        singlePButton.setPosition((160),(250));
        singlePButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("RRRRR", "click");
                SFXClick.play();
//                menuMusic.stop();
//                menuMusic.setLooping(false);
                game.setScreen(new Lobby(game));
                dispose();
            }
        });
        myTexture = game.assets.manager.get(game.assets.hotseat);
        myTextureRegion = new TextureRegion(myTexture);
        drawable = new TextureRegionDrawable(myTextureRegion);
        multiPButton = new ImageButton(drawable);
        multiPButton.setDisabled(true);
        multiPButton.setPosition((40),(560));
        multiPButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                Gdx.app.log("RRRRR", "click1");
                game.setScreen(new HotSeatScreen(game));
                dispose();
            }
         });
        background = game.assets.manager.get(game.assets.Bgr);
        stage.addActor(singlePButton);
        stage.addActor(multiPButton);

        SFXClick = game.assets.manager.get(game.assets.SFXclick);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        game.spriteBatch.draw(background, 0, 0,game.virtual_screen_width,game.virtual_screen_height);
        game.spriteBatch.end();
        singlePButton.setDisabled(false);
        multiPButton.setDisabled(false);
        exit();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }
    private void exit(){
        if((Gdx.input.isKeyJustPressed(Input.Keys.BACK)) && (back)){
            Gdx.app.exit();
            System.exit(0);
        }
        else back = true;
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.clear();
    }

}
