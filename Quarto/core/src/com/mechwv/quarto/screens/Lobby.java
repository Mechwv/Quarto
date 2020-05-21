package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.managers.InputManager;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Lobby implements Screen {

    private GameRoot game;
    private final Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture wooden_field;
    private ImageButton rules;
    private ImageButton musicPlay;
    private ImageButton musicNoplay;
    private ImageButton svitok;

    private boolean match_status;
    private io.socket.client.Socket socket;
    private String room;

    private int player=0;
    private int touches = 0;

    public Lobby(final GameRoot game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        prepare();
        game.music = game.assets.manager.get(game.assets.menuMusic);
        Gdx.app.log("Music", "volume" + game.music.getVolume());
        if (!game.music_play) {
            musicNoplay.setVisible(true);
            musicNoplay.setDisabled(false);
            musicPlay.setVisible(false);
            musicPlay.setDisabled(true);
        } else {
            musicNoplay.setVisible(false);
            musicNoplay.setDisabled(true);
            musicPlay.setVisible(true);
            musicPlay.setDisabled(false);
            game.music.setVolume(0.5f);
            game.music.play();
        }
        connectSocket();
        configSocketEvents();
        game.im = new InputManager(camera);
        InputMultiplexer multiplexer = new InputMultiplexer(game.im,stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void prepare(){
        wooden_field = game.gm.getWooden_field();
        rules = game.gm.getRules();
        svitok = game.gm.getSvitok();
        musicPlay = game.gm.getMusicPlay();
        musicNoplay = game.gm.getMusic_noplay();
        stage.addActor(musicPlay);
        stage.addActor(musicNoplay);
        stage.addActor(rules);
        stage.addActor(svitok);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        if (!match_status) {
            game.font.draw(game.spriteBatch, "Waiting for player 2", 150, 1000);
            hint();
        }
        else {
            game.music.stop();
            game.setScreen(new MultiplayerScreen(game, socket, player, room));
            dispose();
        }
        game.spriteBatch.end();
        backTo();
        stage.draw();
    }

    private void hint(){
        game.font.draw(game.spriteBatch, "Hint: ", 450, 800);
        switch (game.im.getTouches()) {
            case 0:{
                game.small_font.draw(game.spriteBatch, "Tap to change hint", 280, 700);
                break;
            }
            case 1:{
                game.small_font.draw(game.spriteBatch, "If the search takes a long time", 100, 700);
                game.small_font.draw(game.spriteBatch, "restart it by pressing back", 140, 650);
                break;
            }
            case 2:{
                game.small_font.draw(game.spriteBatch, "Check out the rules", 200, 700);
                game.small_font.draw(game.spriteBatch, "in top right corner", 200, 650);
                break;
            }
            case 3:{
                game.small_font.draw(game.spriteBatch, "Turn off the music", 200, 700);
                game.small_font.draw(game.spriteBatch, "in top left corner", 200, 650);
                break;
            }
            default:{
                game.im.setTouches(0);
            }

        }
    }

    private void connectSocket(){
        try{
            Gdx.app.log("SocketIO"," trying to connect");
            socket = IO.socket("https://js-quarto-server.herokuapp.com/");
            socket.connect();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void backTo(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            Gdx.app.log("SocketIO", " disconnecting...");
            socket.emit("disconnect");
            socket.disconnect();
            game.music.stop();
            game.setScreen(new MainMenuScreen(game, true));
            dispose();
        }
    }


    private void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                Gdx.app.log("SocketIO", "Ok");
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "MyId: " + id);
                    id += (int) (Math.random()*1000);
                    socket.emit("create", id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("matchStart", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                Gdx.app.log("SocketIO", "match system functioning");
                try {
                    match_status = data.getBoolean("status");
                    room = data.getString("room");
                    if (match_status) player = data.getInt("player");
                    Gdx.app.log("SocketIO", "Current Match Status: " + match_status);
                    Gdx.app.log("SocketIO", "Current Match Room: " + room);
                    Gdx.app.log("SocketIO", "You are player: " + player);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Room hasn`t been created yet");
                }
            }
        });
    }

    @Override
    public void show() {

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
        stage.clear();
    }


}
