package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.gameplay.FigurePlace;
import com.mechwv.quarto.gameplay.WinFinder;
import com.mechwv.quarto.managers.InputManager;
import com.mechwv.quarto.objects.Board;

public class HotSeatScreen implements Screen{
    private GameRoot game;
    private final Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private Texture board;
    private Texture turn_texture;
    private Sprite chosen_figure;

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
    private String figure_drawing = "0";

    private Board playboard;

    private boolean choosing = true;
    private boolean moving = false;

    FigurePlace fp = new FigurePlace();
    WinFinder wf = new WinFinder();

    private float chosen_scale = 0.9f;
    private Vector2 chosen_coords = new Vector2(900,600);
    private Vector2 moving_coords = new Vector2();
    private Vector2 current_coords = new Vector2();
    private Vector2 place = new Vector2();
    private double angle;



    public HotSeatScreen(final GameRoot game){
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
        drawBoard();
        if (!choosing) {
            game.spriteBatch.draw(chosen_figure,current_coords.x,current_coords.y);
        }
        if (moving) {
                Gdx.app.log("Moving", "true,  x= " + moving_coords.x + " y= " + moving_coords.y);
                boolean a = false, b = false;
                if (current_coords.x > moving_coords.x) {
                    current_coords.x -= 2500 * delta * Math.abs(Math.cos(angle)) ;
                }
                else
                    a = true;
                if (current_coords.y < moving_coords.y) {
                    current_coords.y += 2000 * delta * Math.sin(angle);
                }
                else
                    b = true;
                Gdx.app.log("Moving", " x=" + current_coords.x + " y=" + current_coords.y);
                if (a && b) {
                    moving = false;
                    fp = new FigurePlace(figure_drawing, playboard, place.x, place.y);
                    if(wf.checkBoard(playboard) == 1) {
                        if (game.turn == 1) {
                            Gdx.app.log("win","winner"+1);
                            gameplayMusic.stop();
                            gameplayMusic.setLooping(false);
                            game.setScreen(new EndingScreen(game,1));
                            dispose();
                        } else if (game.turn == 2) {
                            Gdx.app.log("win","winner"+2);
                            gameplayMusic.stop();
                            gameplayMusic.setLooping(false);
                            game.setScreen(new EndingScreen(game, 2));
                            dispose();
                        }
                    } else if (wf.checkBoard(playboard) == 2){
                        game.setScreen(new EndingScreen(game,3));
                        dispose();
                    }
                }
            game.spriteBatch.draw(chosen_figure,current_coords.x,current_coords.y,chosen_figure.getRegionWidth()*chosen_scale,chosen_figure.getRegionHeight()*chosen_scale);
        }
        game.font.draw(game.spriteBatch, "X =" +  game.screenx, 10, 1800);
        game.font.draw(game.spriteBatch, "Y =" + game.screeny, 10, 1700);
        game.font.draw(game.spriteBatch, "Turn =" + game.turn, 10, 1600);
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
    }


    private void check(){
        figure_chosen = game.gm.getFigureChosen();
        if (!figure_chosen.equals("0"))
            choosing = false;
        if (!choosing) {
            switchturn();
            chosen_figure = game.gm.atlas.createSprite(figure_chosen);
            Gdx.input.setInputProcessor(game.im);

            if (!moving) {
                current_coords.x = chosen_coords.x;
                current_coords.y = chosen_coords.y;
            }

            if (Gdx.input.isTouched()) {
                Gdx.app.log("RRRRR", "placing figure");
                game.screenx = game.im.getCoordX();
                game.screeny = game.im.getCoordY();
            }
            if (fp.place_check(playboard,game.screenx,game.screeny)) {
                checkturn();
                place.x = game.screenx;
                place.y = game.screeny;
                figure_drawing = figure_chosen;

                Gdx.app.log("RRRRR", "place checked");
                chosen_scale = check_scale(fp.getBoardCell(game.screenx,game.screeny));
                current_coords.x = chosen_coords.x;
                current_coords.y = chosen_coords.y;

                dist_calc(chosen_coords,fp.getCellCoord(fp.getBoardCell(game.screenx,game.screeny)));
                choosing = true;
                game.gm.setFigureChosen("0");
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

    private void dist_calc(Vector2 a, Vector2 b){
        b.x+=60;
        b.y+=30;
        angle = angleBetweenPoints(a,b);
        byte by = fp.getBoardCell(b.x,b.y);
        moving_coords = fp.getCellCoord(by);
        moving = true;
    }
    private double angleBetweenPoints(Vector2 a, Vector2 b)
    {
        Vector2 ab = new Vector2(b.x-a.x,b.y-a.y);
        Vector2 ac = new Vector2(a.x+Math.abs(ab.x),a.y);
        return (Math.PI - Math.acos((ab.x*ac.x+ab.y+ac.y)/(ab.len()*ac.len())));
    }
}
