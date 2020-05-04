package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.managers.AssetsLoader;
import com.mechwv.quarto.managers.GameManager;
import com.mechwv.quarto.objects.Figures;

import java.util.HashMap;
import java.util.Map;

public class SinglePlayerScreen implements Screen {
    private GameRoot game;
    private final Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private Texture board;
    private GameManager GM;

    public SinglePlayerScreen(final GameRoot game){
        this.game = game;

        GM = new GameManager(game);
        wooden_field = game.assets.manager.get(game.assets.wooden_field);
        board = game.assets.manager.get(game.assets.board);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isTouched()){
            game.Screenx = Gdx.input.getX()-464;
            game.Screeny = Gdx.graphics.getHeight() - (Gdx.input.getY()-13) ;
        }

        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        game.spriteBatch.draw(board,0,800,1100,1100);
        game.font.draw(game.spriteBatch, "X =" +  game.Screenx, 10, 100);
        game.font.draw(game.spriteBatch, "Y =" +  game.Screeny, 10, 200);
        //game.spriteBatch.draw(texture1,100,200);
        game.spriteBatch.draw(GM.textureRegions.get("FigureLRHB"),200,200);
        //game.spriteBatch.draw(textureRegions.get("FigureHSNW"),600,100);
        game.spriteBatch.end();
    }

    void gameplay(){

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

    }
}
