package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.gameplay.FigurePlace;
import com.mechwv.quarto.gameplay.WinFinder;
import com.mechwv.quarto.managers.InputManager;
import com.mechwv.quarto.objects.Board;

public class MultiPlayerScreen implements Screen{
    private GameRoot game;
    private final Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private Texture board;
    private Texture turn_texture;

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


    private Music gameplayMusic;

    private String figure_chosen = "0";

    private Board playboard;

    private boolean choosing = true;
    private boolean placed = false;
    private boolean switched = false;
    private boolean prepared = false;
    private boolean player_1_won = false;

    FigurePlace fp = new FigurePlace();
    WinFinder wf = new WinFinder();



    public MultiPlayerScreen(final GameRoot game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        prepare();
        gameplayMusic = game.gm.getGameplayMusic();
        gameplayMusic.play();
        gameplayMusic.setLooping(true);
        playboard = new Board();
        game.im = new InputManager(camera);
        boardInit();
    }

    private void prepare(){
        wooden_field = game.gm.getWooden_field();
        board = game.gm.getBoard();
        SFXClick = game.gm.getSFXClick();
        turn_texture = game.gm.getPlayer_1_choosing();

        FigureHRHB = game.gm.getFigureHRHB();
        FigureHRNB = game.gm.getFigureHRNB();
        FigureHSHB = game.gm.getFigureHSHB();
        FigureHSNB = game.gm.getFigureHSNB();
        FigureLRHB = game.gm.getFigureLRHB();
        FigureLRNB = game.gm.getFigureLRNB();
        FigureLSHB = game.gm.getFigureLSHB();
        FigureLSNB = game.gm.getFigureLSNB();
        FigureHRHW = game.gm.getFigureHRHW();
        FigureHRNW = game.gm.getFigureHRNW();
        FigureHSHW = game.gm.getFigureHSHW();
        FigureHSNW = game.gm.getFigureHSNW();
        FigureLRHW = game.gm.getFigureLRHW();
        FigureLRNW = game.gm.getFigureLRNW();
        FigureLSHW = game.gm.getFigureLSHW();
        FigureLSNW = game.gm.getFigureLSNW();
        black = game.gm.getBlack();
        white = game.gm.getWhite();

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

    }

    private void boardInit(){
        for (int i = 0; i < 16; i++){
            playboard.coords.add(fp.getCellCoord(i));
            Gdx.app.log("pppp","has added"+fp.getCellCoord(i));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        check();
        turndraw();
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        game.spriteBatch.draw(board,0,800,1100,1100);
        game.spriteBatch.draw(turn_texture,0,720);
        //game.font.draw(game.spriteBatch, "X =" +  game.screenx, 10, 1800);
        //game.font.draw(game.spriteBatch, "Y =" + game.screeny, 10, 1700);
        //game.font.draw(game.spriteBatch, "Turn = " + game.turn, 10, 1600);
        drawBoard();
        game.spriteBatch.end();
        stage.draw();

    }

    private void check(){
        figure_chosen = game.gm.getFigureChosen();
        if (!figure_chosen.equals("0")) choosing = false;
        if (!choosing) {
            switchturn();
            Gdx.input.setInputProcessor(game.im);
            if (Gdx.input.isTouched()) {
                Gdx.app.log("RRRRR", "placing figure");
                game.screenx = game.im.getCoordX();
                game.screeny = game.im.getCoordY();
            }
            if (fp.place_check(playboard,game.screenx,game.screeny)) {
                Gdx.app.log("RRRRR", "place checked");
                fp = new FigurePlace(figure_chosen, playboard, game.screenx, game.screeny);
                if(wf.checkBoard(playboard)) {
                    if (game.turn == 4) player_1_won = true;
                    gameplayMusic.stop();
                    gameplayMusic.setLooping(false);
                    game.setScreen(new EndingScreen(game,player_1_won));
                    dispose();
                }
                game.gm.setFigureChosen("0");
                checkturn();
                choosing = true;
                game.screenx = 0;
                game.screeny = 0;
            }
        }
        else
         {
            Gdx.app.log("RRRRR", "choosing figure");
            Gdx.input.setInputProcessor(stage);
         }
    }

    private void drawBoard(){
        float scale = 1.0f;
        for (int i = 0; i < 16; i++){
            if(!playboard.board[i].equals("-")){
                scale = check_scale(i);
                Sprite sprite = new Sprite(game.gm.atlas.createSprite(playboard.board[i]));
                game.spriteBatch.draw(sprite,playboard.coords.get(i).x,playboard.coords.get(i).y,sprite.getWidth()*scale,sprite.getHeight()*scale);
            }
        }
    }

    private float check_scale(int i){
        if ((i<=15)&&(i>=10)) return 0.9f;
        if ((i<=9)&&(i>=3)) return 0.8f;
        return 0.7f;
    }

    private void turndraw(){
        switch (game.turn)
        {
            case 1:{
                turn_texture = game.gm.getPlayer_1_choosing();
                break;
            }
            case 2:{
                turn_texture = game.gm.getPlayer_2_placing();
                break;
            }
            case 3:{
                turn_texture = game.gm.getPlayer_2_choosing();
                break;
            }
            case 4:{
                turn_texture = game.gm.getPlayer_1_placing();
                break;
            }
        }
    }

    private void checkturn(){
        if (game.turn < 4){
            game.turn++;
        }
        else game.turn = 1;
    }

    private void switchturn(){
        if (game.turn == 1) game.turn = 2;
        if (game.turn == 3) game.turn = 4;

    }

    private void gameending(){

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
    }
}
