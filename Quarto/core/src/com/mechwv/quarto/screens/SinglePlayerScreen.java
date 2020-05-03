package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mechwv.quarto.GameRoot;

import java.util.HashMap;
import java.util.Map;

public class SinglePlayerScreen implements Screen {
    private GameRoot game;
    private final Stage stage;
    private Texture wooden_field;
    private Texture board;
    private TextureAtlas figure_pack;
    private Skin skin;


    public  Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();

    public SinglePlayerScreen(final GameRoot game){
        this.game = game;
        stage = new Stage();
        wooden_field = new Texture(Gdx.files.internal("images/simple_sprites/wooden_bgr.jpg"));
        figure_pack = new TextureAtlas(Gdx.files.internal("images/simple_sprites/simple_figures.pack"));

        board = new Texture(Gdx.files.internal("images/simple_sprites/AdvancedBoard.png"));


        skin = new Skin();
        skin.addRegions(figure_pack);
        textureRegions.put("FigureHRHB",skin.get("FigureHRHB", TextureRegion.class));
        textureRegions.put("FigureHRHW",skin.get("FigureHRHW", TextureRegion.class));
        textureRegions.put("FigureHRNB",skin.get("FigureHRNB", TextureRegion.class));
        textureRegions.put("FigureHRNW",skin.get("FigureHRNW", TextureRegion.class));
        textureRegions.put("FigureHSHB",skin.get("FigureHSHB", TextureRegion.class));
        textureRegions.put("FigureHSHW",skin.get("FigureHSHW", TextureRegion.class));
        textureRegions.put("FigureHSNB",skin.get("FigureHSNB", TextureRegion.class));
        textureRegions.put("FigureHSNW",skin.get("FigureHSNW", TextureRegion.class));
        textureRegions.put("FigureLRHB",skin.get("FigureLRHB", TextureRegion.class));
        textureRegions.put("FigureLRHW",skin.get("FigureLRHW", TextureRegion.class));
        textureRegions.put("FigureLRNB",skin.get("FigureLRNB", TextureRegion.class));
        textureRegions.put("FigureLRNW",skin.get("FigureLRNW", TextureRegion.class));
        textureRegions.put("FigureLSHB",skin.get("FigureLSHB", TextureRegion.class));
        textureRegions.put("FigureLSHW",skin.get("FigureLSHW", TextureRegion.class));
        textureRegions.put("FigureLSNB",skin.get("FigureLSNB", TextureRegion.class));
        textureRegions.put("FigureLSNW",skin.get("FigureLSNW", TextureRegion.class));



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isTouched()){
            game.Screenx = Gdx.input.getX();
            game.Screeny = 1720 - Gdx.input.getY();
        }

        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        game.spriteBatch.draw(board,0,800,1100,1100);
        game.font.draw(game.spriteBatch, "X =" +  game.Screenx, 10, 100);
        game.font.draw(game.spriteBatch, "Y =" +  game.Screeny, 10, 200);
        game.spriteBatch.draw(textureRegions.get("FigureLRHB"),0,0,1500,1500);
        game.spriteBatch.draw(textureRegions.get("FigureHSNW"),600,100);
        game.spriteBatch.end();
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
