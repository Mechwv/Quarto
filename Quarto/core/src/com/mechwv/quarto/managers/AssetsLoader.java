package com.mechwv.quarto.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetsLoader {
    public final AssetManager manager = new AssetManager();
    public final String  wooden_field = "images/volodya_hudojnik/wooden_bgr.jpg";
    public final String  board = "images/volodya_hudojnik/board_gradient_shadow.png";
    public final String  end_board = "images/turns&ending/background_5.jpg";


    public final String  figure_pack = "images/all_buttons/all_buttons.atlas";
    public final String  multiplayer = "images/buttons/multiplayer_2.png";
    public final String  Bgr = "images/volodya_hudojnik/background_final.jpg";
    public final String  settings = "images/buttons/shesternya.png";
    public final String  singleplayer = "images/buttons/single_player_2.png";
    public final String  retry = "images/turns&ending/play_again.png";
    public final String  black = "images/Circle_B.png";
    public final String  white = "images/Circle_W.png";
    public final String  gameplayMusic = "sounds/GameplayMusic.wav";
    public final String  menuMusic = "sounds/MenuMusic.wav";

    public final String hotseat = "images/Hotseat.png";
    public final String play_again = "images/play_again.png";
    public final String return_back = "images/return.png";
    public final String you_lose = "images/turns&ending/you lose2.png";
    public final String you_win = "images/turns&ending/you win2.png";
    public final String draw = "images/turns&ending/draw.png";

    public final String player_1_choosing = "images/turns&ending/player_1_choosing.png";
    public final String player_2_choosing = "images/turns&ending/player_2_choosing.png";
    public final String player_1_placing = "images/turns&ending/player_1_placing.png";
    public final String player_2_placing = "images/turns&ending/player_2_placing.png";
    public final String player_1_won = "images/turns&ending/player_1_won.png";
    public final String player_2_won = "images/turns&ending/player_2_won.png";


    public final String  SFXclick = "sounds/wooden-click.wav";

    public void load(){
        manager.load(wooden_field, Texture.class);
        manager.load(board, Texture.class);
        manager.load(multiplayer, Texture.class);
        manager.load(singleplayer, Texture.class);
        manager.load(settings, Texture.class);
        manager.load(Bgr, Texture.class);
        manager.load(black, Texture.class);
        manager.load(white, Texture.class);
        manager.load(player_1_choosing, Texture.class);
        manager.load(player_2_choosing, Texture.class);
        manager.load(player_1_placing, Texture.class);
        manager.load(player_2_placing, Texture.class);
        manager.load(end_board, Texture.class);
        manager.load(retry, Texture.class);
        manager.load(player_1_won, Texture.class);
        manager.load(player_2_won, Texture.class);

        manager.load(figure_pack, TextureAtlas.class);

        manager.load(SFXclick, Music.class);
        manager.load(gameplayMusic, Music.class);
        manager.load(menuMusic, Music.class);

        manager.load(hotseat, Texture.class);
        manager.load(return_back, Texture.class);
        manager.load(play_again, Texture.class);
        manager.load(you_lose, Texture.class);
        manager.load(you_win, Texture.class);
        manager.load(draw, Texture.class);

    }

    public void dispose(){
        manager.dispose();
    }
}
