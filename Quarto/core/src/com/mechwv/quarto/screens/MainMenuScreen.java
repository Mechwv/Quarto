package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.managers.AssetsLoader;

public class MainMenuScreen implements Screen {

    private GameRoot game;
    private final Stage stage;

    //Parameter for drawing the buttons
    private OrthographicCamera camera;
    private Viewport viewport;
    private final TextureAtlas buttons;
    private final Button SinglePButton;
    private final ImageButton MultiPButton;
    private final Skin skin;

    //Parameter for Sound
    private final Sound SFXClick;

    public MainMenuScreen(final GameRoot game){

        //Set up our assets
        this.game = game;

        game.Screenx = Gdx.graphics.getWidth();
        game.Screeny = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);

        Gdx.input.setInputProcessor(stage);

        buttons = game.assets.manager.get(game.assets.buttons);
        skin = new Skin(buttons);
        skin.addRegions(buttons);

        SinglePButton = new ImageButton(skin.getDrawable("button_singleplayer"));
        SinglePButton.setPosition(( game.Screenx/2-SinglePButton.getWidth()/2),( game.Screeny/2-SinglePButton.getHeight()/2));
        SinglePButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                    Gdx.app.log("RRRRR", "click");
                    SFXClick.play();
                    game.setScreen(new SinglePlayerScreen(game));
                    dispose();
            }
        });

        MultiPButton = new ImageButton(skin.getDrawable("button_multiplayer"));
        MultiPButton.setPosition(( game.Screenx/2-SinglePButton.getWidth()/2),( game.Screeny/2-SinglePButton.getHeight()/2-MultiPButton.getHeight()-50));
        MultiPButton.addListener(new ClickListener() {
             public void clicked(InputEvent event, float x, float y) {
                     SFXClick.play();
                     Gdx.app.log("RRRRR", "click1");
                     //game.setScreen(new MultiPlayerScreen(game));
                    dispose();
                 }
         });
        stage.addActor(SinglePButton);
        stage.addActor(MultiPButton);

        SFXClick = game.assets.manager.get(game.assets.SFXclick);

    }

    @Override
    public void show() {
        render(0);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(141/255f, 97/255f, 66/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        if (Gdx.input.isTouched()){
            //Screenx = Gdx.input.getX();
            //Screeny = Gdx.input.getY();
        }
        game.spriteBatch.begin();
        game.font.draw(game.spriteBatch, "X =" +  game.Screenx, 10, 100);
        game.font.draw(game.spriteBatch, "Y =" + game.Screeny, 10, 200);
        game.spriteBatch.end();

        stage.draw();
        

    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        stage.clear();
    }
}
