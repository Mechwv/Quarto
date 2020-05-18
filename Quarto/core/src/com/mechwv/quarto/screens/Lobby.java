package com.mechwv.quarto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;

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
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private ImageButton returnMenu;
    private Music SFXClick;

    private boolean match_status;
    private io.socket.client.Socket socket;
    private String room;

    private int player=0;

    public Lobby(final GameRoot game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(game.virtual_screen_width,game.virtual_screen_height);
        viewport.setCamera(camera);
        stage = new Stage(viewport,game.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        SFXClick = game.assets.manager.get(game.assets.SFXclick);
        wooden_field = game.gm.getWooden_field();
        game.music = game.assets.manager.get(game.assets.menuMusic);
        game.music.setVolume(0.5f);
        game.music.play();
        game.music.setLooping(true);
        myTexture = game.assets.manager.get(game.assets.return_back);
        myTextureRegion = new TextureRegion(myTexture);
        Drawable drawable = new TextureRegionDrawable(myTextureRegion);
        returnMenu = new ImageButton(drawable);
        returnMenu.setPosition((40),(300));
        returnMenu.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("RRRRR", "click");
                SFXClick.play();
                Gdx.app.log("SocketIO", " disconnecting...");
                socket.emit("disconnect");
                socket.disconnect();
                game.music.stop();
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        stage.addActor(returnMenu);
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
            game.font.draw(game.spriteBatch, "Waiting for player 2", 150, 1000);
        else {
            game.music.stop();
            game.setScreen(new MultiplayerScreen(game, socket, player, room));
            dispose();
        }
        game.spriteBatch.end();

        stage.draw();
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
