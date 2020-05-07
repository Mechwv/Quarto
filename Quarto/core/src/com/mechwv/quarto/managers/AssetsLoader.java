package com.mechwv.quarto.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.Font;

public class AssetsLoader {
    public final AssetManager manager = new AssetManager();
    public final String  wooden_field = "images/volodya_hudojnik/wooden_bgr.jpg";
    public final String  board = "images/volodya_hudojnik/board_gradient_shadow.png";


    public final String  figure_pack = "images/volodya_hudojnik/advancedfigures.pack";
    public final String  Multiplayer = "images/buttons/multiplayer.png";
    public final String  Bgr = "images/volodya_hudojnik/background.jpg";
    public final String  Logo = "images/buttons/quarto!!!.png";
    public final String  Settings = "images/buttons/shesternya.png";
    public final String  Singleplayer = "images/buttons/single player.png";
    public final String  black = "images/black.png";
    public final String  white = "images/white.png";
    public final String  gameplayMusic = "sounds/GameplayMusic.wav";
    public final String  menuMusic = "sounds/MenuMusic.wav";


    public final String  SFXclick = "sounds/wooden-click.wav";

    public void load(){
        manager.load(wooden_field, Texture.class);
        manager.load(board, Texture.class);
        manager.load(Multiplayer, Texture.class);
        manager.load(Logo, Texture.class);
        manager.load(Singleplayer, Texture.class);
        manager.load(Settings, Texture.class);
        manager.load(Bgr, Texture.class);
        manager.load(black, Texture.class);
        manager.load(white, Texture.class);

        manager.load(figure_pack, TextureAtlas.class);

        manager.load(SFXclick, Music.class);
        manager.load(gameplayMusic, Music.class);
        manager.load(menuMusic, Music.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
