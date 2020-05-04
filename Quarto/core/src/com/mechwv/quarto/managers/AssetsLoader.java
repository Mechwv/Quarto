package com.mechwv.quarto.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.Font;

public class AssetsLoader {
    public final AssetManager manager = new AssetManager();
    public final String  wooden_field = "images/volodya_hudojnik/wooden_bgr.jpg";
    public final String  board = "images/volodya_hudojnik/board_gradient_shadow.png";


    public final String  figure_pack = "images/volodya_hudojnik/advancedfigures.pack";
    public final String  buttons = "images/buttons/buttons.pack";

    public final String  SFXclick = "sounds/wooden-click.wav";

    public void load(){
        manager.load(wooden_field, Texture.class);
        manager.load(board, Texture.class);
        manager.load(figure_pack, TextureAtlas.class);
        manager.load(buttons, TextureAtlas.class);
        manager.load(SFXclick, Sound.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
