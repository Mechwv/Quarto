package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.mechwv.quarto.objects.Board;

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
    private Music gameplayMusic;
    private Music waitingMusic;

    private boolean match_status;
    private io.socket.client.Socket socket;

    public Lobby(final GameRoot game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        wooden_field = game.gm.getWooden_field();
        waitingMusic = game.assets.manager.get(game.assets.menuMusic);
        waitingMusic.play();
        waitingMusic.setLooping(true);

        connectSocket();
        configSocketEvents();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(wooden_field,0,0);
        if (!match_status)
            game.font.draw(game.spriteBatch, "Waiting for player 2", 300, 1000);
        else {
            game.setScreen(new MultiplayerScreen(game, socket));
            dispose();
        }
        game.spriteBatch.end();

    }


    public void connectSocket(){
        try{
            Gdx.app.log("SocketIO"," trying to connect");
            socket = IO.socket("https://js-quarto-server.herokuapp.com/");
            socket.connect();
        }catch (Exception e){
            System.out.println(e);
        }
    }



    public void configSocketEvents() {
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
                    Gdx.app.log("SocketIO", "Current Match Status: " + match_status);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New Player Connected");
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



   /* public void updateServer(){
        JSONObject  data = new JSONObject();
        try {
            data.put("turn", game.turn);
            data.put("board", playboard);
            data.put("chosen_figure", chosen_figure);
        }catch (JSONException e){
            Gdx.app.log("Socket.IO", "Error sending upd data");
        }
    }*/

}
