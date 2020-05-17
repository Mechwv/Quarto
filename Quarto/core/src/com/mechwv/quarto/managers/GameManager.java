package com.mechwv.quarto.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.screens.MainMenuScreen;


public class GameManager {
    private GameRoot game;

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
    private ImageButton retry;

    private Texture wooden_field;
    private Texture board;
    private Texture end_board;

    private Music SFXClick;
    private Music menuMusic;
    private Music gameplayMusic;


    private String name = "0";

    private Skin skin;

    public TextureAtlas atlas;

    private Texture player_1_choosing;
    private Texture player_1_placing;
    private Texture player_2_choosing;
    private Texture player_2_placing;
    private Texture player_1_won;
    private Texture player_2_won;

    private Texture hotseat;
    private Texture play_again;
    private Texture return_back;
    private Texture you_lose;
    private Texture you_win;
    private Texture draw;

    public GameManager(final GameRoot game){
        this.game = game;
        setupButtons();
        setupMisc();
    }

    public void update(){
        setupButtons();
        setupMisc();
    }

    private void setupButtons(){
        atlas = game.assets.manager.get(game.assets.figure_pack);
        skin = new Skin(atlas);


        FigureHRHB = new ImageButton(skin.getDrawable("FigureHRHB"));
        FigureHRHB.setPosition(100,50);
        FigureHRHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRHB.setDisabled(true);
                name = "FigureHRHB";
                FigureHRHB.remove();
            }
        });
        FigureHRNB = new ImageButton(skin.getDrawable("FigureHRNB"));
        FigureHRNB.setPosition(300,50);
        FigureHRNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRNB.setDisabled(true);
                name = "FigureHRNB";
                FigureHRNB.remove();
            }
        });
        FigureHSHB = new ImageButton(skin.getDrawable("FigureHSHB"));
        FigureHSHB.setPosition(500,50);
        FigureHSHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSHB.setDisabled(true);
                name = "FigureHSHB";
                FigureHSHB.remove();
            }
        });
        FigureHSNB = new ImageButton(skin.getDrawable("FigureHSNB"));
        FigureHSNB.setPosition(700,50);
        FigureHSNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSNB.setDisabled(true);
                name = "FigureHSNB";
                FigureHSNB.remove();
            }
        });
        FigureLRHB = new ImageButton(skin.getDrawable("FigureLRHB"));
        FigureLRHB.setPosition(100,400);
        FigureLRHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRHB.setDisabled(true);
                name = "FigureLRHB";
                FigureLRHB.remove();
            }
        });
        FigureLRNB = new ImageButton(skin.getDrawable("FigureLRNB"));
        FigureLRNB.setPosition(300,400);
        FigureLRNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRNB.setDisabled(true);
                name = "FigureLRNB";
                FigureLRNB.remove();
            }
        });
        FigureLSHB = new ImageButton(skin.getDrawable("FigureLSHB"));
        FigureLSHB.setPosition(500,400);
        FigureLSHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSHB.setDisabled(true);
                name = "FigureLSHB";
                FigureLSHB.remove();
            }
        });
        FigureLSNB = new ImageButton(skin.getDrawable("FigureLSNB"));
        FigureLSNB.setPosition(700,400);
        FigureLSNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSNB.setDisabled(true);
                name = "FigureLSNB";
                FigureLSNB.remove();

            }
        });
        FigureHRHW = new ImageButton(skin.getDrawable("FigureHRHW"));
        FigureHRHW.setPosition(100,50);
        FigureHRHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRHW.setDisabled(true);
                name = "FigureHRHW";
                FigureHRHW.remove();
            }
        });
        FigureHRNW = new ImageButton(skin.getDrawable("FigureHRNW"));
        FigureHRNW.setPosition(300,50);
        FigureHRNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRNW.setDisabled(true);
                name = "FigureHRNW";
                FigureHRNW.remove();
            }
        });
        FigureHSHW = new ImageButton(skin.getDrawable("FigureHSHW"));
        FigureHSHW.setPosition(500,50);
        FigureHSHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSHW.setDisabled(true);
                name = "FigureHSHW";
                FigureHSHW.remove();
            }
        });
        FigureHSNW = new ImageButton(skin.getDrawable("FigureHSNW"));
        FigureHSNW.setPosition(700,50);
        FigureHSNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSNW.setDisabled(true);
                name = "FigureHSNW";
                FigureHSNW.remove();
            }
        });
        FigureLRHW = new ImageButton(skin.getDrawable("FigureLRHW"));
        FigureLRHW.setPosition(100,400);
        FigureLRHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRHW.setDisabled(true);
                name = "FigureLRHW";
                FigureLRHW.remove();
            }
        });
        FigureLRNW = new ImageButton(skin.getDrawable("FigureLRNW"));
        FigureLRNW.setPosition(300,400);
        FigureLRNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRNW.setDisabled(true);
                name = "FigureLRNW";
                FigureLRNW.remove();
            }
        });
        FigureLSHW = new ImageButton(skin.getDrawable("FigureLSHW"));
        FigureLSHW.setPosition(500,400);
        FigureLSHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSHW.setDisabled(true);
                name = "FigureLSHW";
                FigureLSHW.remove();
            }
        });
        FigureLSNW = new ImageButton(skin.getDrawable("FigureLSNW"));
        FigureLSNW.setPosition(700,400);
        FigureLSNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSNW.setDisabled(true);
                name = "FigureLSNW";
                FigureLSNW.remove();
            }
        });

        Texture texture = game.assets.manager.get(game.assets.play_again);
        TextureRegion textureRegion = new TextureRegion(texture);
        Drawable drawable = new TextureRegionDrawable(textureRegion);
        retry = new ImageButton(drawable);
        retry.setPosition(( game.virtual_screen_width/2- retry.getWidth()/2),( game.virtual_screen_width/2+200));
        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                retry.remove();
                game.setScreen(new MainMenuScreen(game));
                update();
            }
        });


        texture = game.assets.manager.get(game.assets.black);
        textureRegion = new TextureRegion(texture);
        drawable = new TextureRegionDrawable(textureRegion);
        black = new ImageButton(drawable);
        black.setPosition(900,400);
        black.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRHB.setDisabled(false);
                FigureHRNB.setDisabled(false);
                FigureHSHB.setDisabled(false);
                FigureHSNB.setDisabled(false);
                FigureLRHB.setDisabled(false);
                FigureLRNB.setDisabled(false);
                FigureLSHB.setDisabled(false);
                FigureLSNB.setDisabled(false);

                FigureHRHW.setVisible(false);
                FigureHRNW.setVisible(false);
                FigureHSHW.setVisible(false);
                FigureHSNW.setVisible(false);
                FigureLRHW.setVisible(false);
                FigureLRNW.setVisible(false);
                FigureLSHW.setVisible(false);
                FigureLSNW.setVisible(false);

                FigureHRHB.setVisible(true);
                FigureHRNB.setVisible(true);
                FigureHSHB.setVisible(true);
                FigureHSNB.setVisible(true);
                FigureLRHB.setVisible(true);
                FigureLRNB.setVisible(true);
                FigureLSHB.setVisible(true);
                FigureLSNB.setVisible(true);
            }
        });
        texture = game.assets.manager.get(game.assets.white);
        textureRegion = new TextureRegion(texture);
        drawable = new TextureRegionDrawable(textureRegion);
        white = new ImageButton(drawable);
        white.setPosition(900,50);
        white.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FigureHRHW.setDisabled(false);
                FigureHRNW.setDisabled(false);
                FigureHSHW.setDisabled(false);
                FigureHSNW.setDisabled(false);
                FigureLRHW.setDisabled(false);
                FigureLRNW.setDisabled(false);
                FigureLSHW.setDisabled(false);
                FigureLSNW.setDisabled(false);

                FigureHRHW.setVisible(true);
                FigureHRNW.setVisible(true);
                FigureHSHW.setVisible(true);
                FigureHSNW.setVisible(true);
                FigureLRHW.setVisible(true);
                FigureLRNW.setVisible(true);
                FigureLSHW.setVisible(true);
                FigureLSNW.setVisible(true);

                FigureHRHB.setVisible(false);
                FigureHRNB.setVisible(false);
                FigureHSHB.setVisible(false);
                FigureHSNB.setVisible(false);
                FigureLRHB.setVisible(false);
                FigureLRNB.setVisible(false);
                FigureLSHB.setVisible(false);
                FigureLSNB.setVisible(false);
            }
        });


    }

    private void setupMisc(){
        wooden_field = game.assets.manager.get(game.assets.wooden_field);
        board = game.assets.manager.get(game.assets.board);
        SFXClick = game.assets.manager.get(game.assets.SFXclick);
        menuMusic = game.assets.manager.get(game.assets.menuMusic);
        gameplayMusic = game.assets.manager.get(game.assets.gameplayMusic);
        player_1_choosing = game.assets.manager.get(game.assets.player_1_choosing);
        player_2_choosing = game.assets.manager.get(game.assets.player_2_choosing);
        player_1_placing = game.assets.manager.get(game.assets.player_1_placing);
        player_2_placing = game.assets.manager.get(game.assets.player_2_placing);
        end_board = game.assets.manager.get(game.assets.end_board);
        player_1_won = game.assets.manager.get(game.assets.player_1_won);
        player_2_won = game.assets.manager.get(game.assets.player_2_won);
        hotseat = game.assets.manager.get(game.assets.hotseat);
        play_again = game.assets.manager.get(game.assets.play_again);
        return_back = game.assets.manager.get(game.assets.return_back);
        you_lose = game.assets.manager.get(game.assets.you_lose);
        you_win = game.assets.manager.get(game.assets.you_win);
        draw = game.assets.manager.get(game.assets.draw);

    }


    public ImageButton getBlack() {
        return black;
    }

    public ImageButton getRetry() {
        return retry;
    }

    public ImageButton getWhite() {
        return white;
    }

    public Music getGameplayMusic() {
        return gameplayMusic;
    }

    public Music getMenuMusic() {
        return menuMusic;
    }

    public Texture getPlayer_1_placing() {
        return player_1_placing;
    }

    public Texture getPlayer_2_choosing() {
        return player_2_choosing;
    }

    public Texture getPlayer_2_placing() {
        return player_2_placing;
    }

    public String getFigureChosen(){
        return name;
    }

    public void setFigureChosen(String name){  this.name = name;}

    public ImageButton getFigureHRHB() {
        return FigureHRHB;
    }

    public ImageButton getFigureHRNB() {
        return FigureHRNB;
    }

    public ImageButton getFigureHSHB() {
        return FigureHSHB;
    }

    public ImageButton getFigureHSNB() {
        return FigureHSNB;
    }

    public ImageButton getFigureLRHB() {
        return FigureLRHB;
    }

    public ImageButton getFigureLRNB() {
        return FigureLRNB;
    }

    public ImageButton getFigureLSHB() {
        return FigureLSHB;
    }

    public ImageButton getFigureLSNB() {
        return FigureLSNB;
    }

    public ImageButton getFigureHRHW() {
        return FigureHRHW;
    }

    public ImageButton getFigureHRNW() {
        return FigureHRNW;
    }

    public ImageButton getFigureHSHW() {
        return FigureHSHW;
    }

    public ImageButton getFigureHSNW() {
        return FigureHSNW;
    }

    public ImageButton getFigureLRHW() {
        return FigureLRHW;
    }

    public ImageButton getFigureLRNW() {
        return FigureLRNW;
    }

    public ImageButton getFigureLSHW() {
        return FigureLSHW;
    }

    public ImageButton getFigureLSNW() {
        return FigureLSNW;
    }

    public Texture getWooden_field() {
        return wooden_field;
    }

    public Texture getBoard() {
        return board;
    }

    public Music getSFXClick() {
        return SFXClick;
    }

    public Texture getPlayer_1_choosing() {
        return player_1_choosing;
    }

    public Texture getEnd_board() {
        return end_board;
    }

    public Texture getPlayer_1_won() {
        return player_1_won;
    }

    public Texture getPlayer_2_won() {
        return player_2_won;
    }

    public Texture getHotseat() {
        return hotseat;
    }

    public Texture getPlay_again() {
        return play_again;
    }

    public Texture getReturn_back() {
        return return_back;
    }

    public Texture getYou_lose() {
        return you_lose;
    }

    public Texture getYou_win() {
        return you_win;
    }

}
