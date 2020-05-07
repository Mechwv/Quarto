package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.MyGestureListener;
import com.mechwv.quarto.managers.GameManager;

public class SinglePlayerScreen implements Screen{
    private GameRoot game;
    private final Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private Texture board;

    public boolean swiped = false;
    private GestureDetector det;
    private ImageButton FigureHRHB;
    private ImageButton FigureHRNB;
    private ImageButton FigureHSHB;
    private ImageButton FigureHSNB;
    private ImageButton FigureLRHB;
    private ImageButton FigureLRNB;
    private ImageButton FigureLSHB;
    private ImageButton FigureLSNB;
    private ImageButton FigureHRHW;
    private ImageButton FigureHRNW;
    private ImageButton FigureHSHW;
    private ImageButton FigureHSNW;
    private ImageButton FigureLRHW;
    private ImageButton FigureLRNW;
    private ImageButton FigureLSHW;
    private ImageButton FigureLSNW;
    private ImageButton black;
    private ImageButton white;
    private Music SFXClick;
    private GameManager gm;
    private Music gameplayMusic;

    public SinglePlayerScreen(final GameRoot game){
        this.game = game;
        gm = new GameManager(game);
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        prepare();
        gameplayMusic.play();
        gameplayMusic.setLooping(true);
    }

    public void prepare(){
        wooden_field = gm.getWooden_field();
        board = gm.getBoard();
        SFXClick = gm.getSFXClick();

        FigureHRHB = gm.getFigureHRHB();
        FigureHRNB = gm.getFigureHRNB();
        FigureHSHB = gm.getFigureHSHB();
        FigureHSNB = gm.getFigureHSNB();
        FigureLRHB = gm.getFigureLRHB();
        FigureLRNB = gm.getFigureLRNB();
        FigureLSHB = gm.getFigureLSHB();
        FigureLSNB = gm.getFigureLSNB();
        FigureHRHW = gm.getFigureHRHW();
        FigureHRNW = gm.getFigureHRNW();
        FigureHSHW = gm.getFigureHSHW();
        FigureHSNW = gm.getFigureHSNW();
        FigureLRHW = gm.getFigureLRHW();
        FigureLRNW = gm.getFigureLRNW();
        FigureLSHW = gm.getFigureLSHW();
        FigureLSNW = gm.getFigureLSNW();
        black = gm.getBlack();
        white = gm.getWhite();

        FigureHRHW.setDisabled(true);
        FigureHRHW.setVisible(false);
        FigureHRNW.setDisabled(true);
        FigureHRNW.setVisible(false);
        FigureHSHW.setDisabled(true);
        FigureHSHW.setVisible(false);
        FigureHSNW.setDisabled(true);
        FigureHSNW.setVisible(false);
        FigureLRHW.setDisabled(true);
        FigureLRHW.setVisible(false);
        FigureLRNW.setDisabled(true);
        FigureLRNW.setVisible(false);
        FigureLSHW.setDisabled(true);
        FigureLSHW.setVisible(false);
        FigureLSNW.setDisabled(true);
        FigureLSNW.setVisible(false);

        stage.addActor(FigureHRHB);
        stage.addActor(FigureHRNB);
        stage.addActor(FigureHSHB);
        stage.addActor(FigureHSNB);
        stage.addActor(FigureLRHB);
        stage.addActor(FigureLRNB);
        stage.addActor(FigureLSHB);
        stage.addActor(FigureLSNB);

        stage.addActor(FigureHRHW);
        stage.addActor(FigureHRNW);
        stage.addActor(FigureHSHW);
        stage.addActor(FigureHSNW);
        stage.addActor(FigureLRHW);
        stage.addActor(FigureLRNW);
        stage.addActor(FigureLSHW);
        stage.addActor(FigureLSNW);
        stage.addActor(black);
        stage.addActor(white);

        gameplayMusic = gm.getGameplayMusic();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isTouched()){
            game.Screenx = Gdx.input.getX()-464;
            game.Screeny = Gdx.graphics.getHeight() - (Gdx.input.getY()-13) ;
        }

        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        game.spriteBatch.draw(board,0,800,1100,1100);
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
        stage.dispose();
        gameplayMusic.dispose();
    }
}
