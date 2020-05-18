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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MultiplayerScreen implements Screen {

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

    private Map<String, ImageButton> buttons = new HashMap<String, ImageButton>();

    private ImageButton black;
    private ImageButton white;
    private ImageButton rules;
    private ImageButton musicPlay;
    private ImageButton musicNoplay;
    private ImageButton svitok;

    private Music SFXClick;

    private String figure_chosen = "0";
    private String figure_drawing = "0";

    private Board playboard;

    private boolean moving = false;

    FigurePlace fp = new FigurePlace();
    WinFinder wf = new WinFinder();

    private float chosen_scale = 0.9f;
    private Vector2 chosen_coords = new Vector2(900,600);
    private Vector2 moving_coords = new Vector2();
    private Vector2 current_coords = new Vector2();
    private Vector2 place = new Vector2();
    private double angle;

    private io.socket.client.Socket socket;
    private int player;
    private String room;


    public MultiplayerScreen(final GameRoot game, final Socket socket, int player, String room){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        this.socket = socket;
        this.player = player;
        this.room = room;

        prepare();
        game.music = game.gm.getGameplayMusic();
        game.music.play();
        game.music.setLooping(true);
        playboard = new Board();
        game.im = new InputManager(camera);
        boardInit();
        getInfoEvents();
    }

    private void update(){
         //org.json.simple.JSONObject data = new org.json.simple.JSONObject();
         ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(out).writeObject(playboard.board);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String list = new String(Hex.encodeHex(out.toByteArray()));
         socket.emit("update", list, room, figure_chosen);
    }

    private void turnUpdate(){
        Gdx.app.log("SocketIO", "Turn sent: " + game.turn);
        socket.emit("turnUpdate", room, game.turn);
    }



    private void prepare(){
        wooden_field = game.gm.getWooden_field();
        board = game.gm.getBoard();
        SFXClick = game.gm.getSFXClick();
        turn_texture = game.gm.getPlayer_1_choosing();

        musicPlay = game.gm.getMusicPlay();
        musicNoplay = game.gm.getMusic_noplay();
        svitok = game.gm.getSvitok();
        rules = game.gm.getRules();


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

        buttons.put("FigureHRHB", FigureHRHB);
        buttons.put("FigureHRNB", FigureHRNB);
        buttons.put("FigureHSHB", FigureHSHB);
        buttons.put("FigureHSNB", FigureHSNB);
        buttons.put("FigureLRHB", FigureLRHB);
        buttons.put("FigureLRNB", FigureLRNB);
        buttons.put("FigureLSHB", FigureLSHB);
        buttons.put("FigureLSNB", FigureLSNB);
        buttons.put("FigureHRHW", FigureHRHW);
        buttons.put("FigureHRNW", FigureHRNW);
        buttons.put("FigureHSHW", FigureHSHW);
        buttons.put("FigureHSNW", FigureHSNW);
        buttons.put("FigureLRHW", FigureLRHW);
        buttons.put("FigureLRNW", FigureLRNW);
        buttons.put("FigureLSHW", FigureLSHW);
        buttons.put("FigureLSNW", FigureLSNW);


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

        stage.addActor(musicPlay);
        stage.addActor(musicNoplay);
        stage.addActor(svitok);
        stage.addActor(rules);

    }

    private void boardInit(){
        for (int i = 0; i < 16; i++){
            playboard.coords.add(fp.getCellCoord(i));
            Gdx.app.log("pppp","has added"+fp.getCellCoord(i));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        checkButtons();
        try {
            check();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        turndraw();
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        game.spriteBatch.draw(board,0,800,1100,1100);
        game.spriteBatch.draw(turn_texture,0,720);
        drawBoard();
        draw_chosen(delta);
        game.font.draw(game.spriteBatch, "Player " + player, 400, 1840);
        game.spriteBatch.end();
        stage.draw();

    }


    private void check() throws JSONException {
        if (game.turn > 4) game.turn = 1;
        if (player == 1){
            if (game.turn == 1){
                enableButtons();
                if(choose_a_figure()){
                    disableButtons();
                    Gdx.app.log("SocketIO", "Player1 chose a figure");
                    game.turn++;
                    turnUpdate();
                    update();
                }
            }
            if (game.turn == 2){
                disableButtons();
            }
            if (game.turn == 3){
                disableButtons();
            }
            if (game.turn == 4){
                enableButtons();
                if(place_a_figure()){
                    disableButtons();
                    game.turn++;
                    turnUpdate();
                    update();
                }
            }
        }
        else {
            if (game.turn == 1){
                disableButtons();
            }
            if (game.turn == 2){
                enableButtons();
                if(place_a_figure()){
                    disableButtons();
                    game.turn++;
                    turnUpdate();
                    update();
                }
            }
            if (game.turn == 3){
                enableButtons();
                Gdx.app.log("Gameplay", "Third turn choosing");
                if(choose_a_figure()){
                    game.turn++;
                    turnUpdate();
                    update();
                }
            }
            if (game.turn == 4){
                disableButtons();
            }
        }
    }
    private boolean choose_a_figure(){
        Gdx.input.setInputProcessor(stage);
        figure_chosen = game.gm.getFigureChosen();
        if (!figure_chosen.equals("0")) {
            current_coords.x = chosen_coords.x;
            current_coords.y = chosen_coords.y;
            return true;
        }
        return false;
    }

    private boolean place_a_figure(){
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
            place.x = game.screenx;
            place.y = game.screeny;
            figure_drawing = figure_chosen;
            Gdx.app.log("RRRRR", "place checked");
            chosen_scale = check_scale(fp.getBoardCell(game.screenx,game.screeny));
            current_coords.x = chosen_coords.x;
            current_coords.y = chosen_coords.y;
            dist_calc(chosen_coords,fp.getCellCoord(fp.getBoardCell(game.screenx,game.screeny)));
            moving = true;
            game.gm.setFigureChosen("0");
            return true;
        }
        return false;
    }

    private void draw_chosen(float delta){
        if (!figure_chosen.equals("0")) {
            chosen_figure = game.gm.atlas.createSprite(figure_chosen);
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
                        socket.emit("gameEnd",room, 1);
                    }
                    else socket.emit("gameEnd",room, 2);
                    game.music.stop();
                    game.music.setLooping(false);
                } else if (wf.checkBoard(playboard) == 2){
                    socket.emit("gameEnd",room, 3);
                }
            }
            game.spriteBatch.draw(chosen_figure,current_coords.x,current_coords.y,chosen_figure.getRegionWidth()*chosen_scale,chosen_figure.getRegionHeight()*chosen_scale);
        }
    }

    private void getInfoEvents(){
        socket.on("turnUpdate", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                Gdx.app.log("SocketIO", "Switching turn");
                try {
                    game.turn = data.getInt("turn");
                    Gdx.app.log("SocketIO", "turn is"+ game.turn);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error switching turn");
                }
            }
        }).on("update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                Gdx.app.log("SocketIO", "Got info");
                try {
                    game.gm.setFigureChosen(data.getString("figure"));
                    figure_chosen = data.getString("figure");
                    ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(data.getString("list").toCharArray()));
                    String a = Arrays.toString((String[]) new ObjectInputStream(in).readObject());
                    a = a.replace("[","");
                    a = a.replace("]","");
                    a = a.replaceAll("[,.]", "");
                    playboard.board = a.split("\\s");
                    Gdx.app.log("SocketIO", "Array = " + Arrays.toString(playboard.board));

                } catch (JSONException | DecoderException | IOException | ClassNotFoundException e) {
                    Gdx.app.log("SocketIO", "Error getting info");
                }
            }
        }).on("winner", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                Gdx.app.log("SocketIO", "Got info");
                try {
                    int winner = data.getInt("winner");
                    Gdx.app.log("SocketIO", "winner is " + winner + " player is "+ player);
                    game.music.stop();
                    game.setScreen(new MultiplayerEndingScreen(game, winner, player));
                    socket.emit("disconnect");
                    dispose();
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting info");
                }
            }
        });
    }

    private void disableButtons(){
        FigureHRHB.setDisabled(true);
        FigureHRNB.setDisabled(true);
        FigureHSHB.setDisabled(true);
        FigureHSNB.setDisabled(true);
        FigureLRHB.setDisabled(true);
        FigureLRNB.setDisabled(true);
        FigureLSHB.setDisabled(true);
        FigureLSNB.setDisabled(true);
        FigureHRHW.setDisabled(true);
        FigureHRNW.setDisabled(true);
        FigureHSHW.setDisabled(true);
        FigureHSNW.setDisabled(true);
        FigureLRHW.setDisabled(true);
        FigureLRNW.setDisabled(true);
        FigureLSHW.setDisabled(true);
        FigureLSNW.setDisabled(true);
    }

    private void enableButtons(){
        FigureHRHB.setDisabled(false);
        FigureHRNB.setDisabled(false);
        FigureHSHB.setDisabled(false);
        FigureHSNB.setDisabled(false);
        FigureLRHB.setDisabled(false);
        FigureLRNB.setDisabled(false);
        FigureLSHB.setDisabled(false);
        FigureLSNB.setDisabled(false);
        FigureHRHW.setDisabled(false);
        FigureHRNW.setDisabled(false);
        FigureHSHW.setDisabled(false);
        FigureHSNW.setDisabled(false);
        FigureLRHW.setDisabled(false);
        FigureLRNW.setDisabled(false);
        FigureLSHW.setDisabled(false);
        FigureLSNW.setDisabled(false);
    }

    private void checkButtons() {
        for (int i = 0; i < 16; i++) {
            if (!playboard.board[i].equals("-")) {
                buttons.get(playboard.board[i]).setDisabled(true);
                buttons.get(playboard.board[i]).setVisible(false);
            }
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

    @Override
    public void show() {

    }
}
