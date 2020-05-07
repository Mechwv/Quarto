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

public class GameManager {
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

    private Texture wooden_field;
    private Texture board;
    private Music SFXClick;
    private GameRoot game;
    private Music menuMusic;
    private Music gameplayMusic;

    public GameManager(final GameRoot game){
        this.game = game;
        setupButtons();
        setupMisc();
    }

    public void setupButtons(){
        TextureAtlas atlas = game.assets.manager.get(game.assets.figure_pack);
        Skin skin = new Skin(atlas);

        FigureHRHB = new ImageButton(skin.getDrawable("FigureHRHB"));
        FigureHRHB.setPosition(100,100);
        FigureHRHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRHB.setDisabled(true);
            }
        });
        FigureHRNB = new ImageButton(skin.getDrawable("FigureHRNB"));
        FigureHRNB.setPosition(300,100);
        FigureHRNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRNB.setDisabled(true);
            }
        });
        FigureHSHB = new ImageButton(skin.getDrawable("FigureHSHB"));
        FigureHSHB.setPosition(500,100);
        FigureHSHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSHB.setDisabled(true);
            }
        });
        FigureHSNB = new ImageButton(skin.getDrawable("FigureHSNB"));
        FigureHSNB.setPosition(700,100);
        FigureHSNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSNB.setDisabled(true);
            }
        });
        FigureLRHB = new ImageButton(skin.getDrawable("FigureLRHB"));
        FigureLRHB.setPosition(100,500);
        FigureLRHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRHB.setDisabled(true);
            }
        });
        FigureLRNB = new ImageButton(skin.getDrawable("FigureLRNB"));
        FigureLRNB.setPosition(300,500);
        FigureLRNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRNB.setDisabled(true);
            }
        });
        FigureLSHB = new ImageButton(skin.getDrawable("FigureLSHB"));
        FigureLSHB.setPosition(500,500);
        FigureLSHB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSHB.setDisabled(true);
            }
        });
        FigureLSNB = new ImageButton(skin.getDrawable("FigureLSNB"));
        FigureLSNB.setPosition(700,500);
        FigureLSNB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSNB.setDisabled(true);
            }
        });
        FigureHRHW = new ImageButton(skin.getDrawable("FigureHRHW"));
        FigureHRHW.setPosition(100,100);
        FigureHRHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRHW.setDisabled(true);
            }
        });
        FigureHRNW = new ImageButton(skin.getDrawable("FigureHRNW"));
        FigureHRNW.setPosition(300,100);
        FigureHRNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHRNW.setDisabled(true);
            }
        });
        FigureHSHW = new ImageButton(skin.getDrawable("FigureHSHW"));
        FigureHSHW.setPosition(500,100);
        FigureHSHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSHW.setDisabled(true);
            }
        });
        FigureHSNW = new ImageButton(skin.getDrawable("FigureHSNW"));
        FigureHSNW.setPosition(700,100);
        FigureHSNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureHSNW.setDisabled(true);
            }
        });
        FigureLRHW = new ImageButton(skin.getDrawable("FigureLRHW"));
        FigureLRHW.setPosition(100,500);
        FigureLRHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRHW.setDisabled(true);
            }
        });
        FigureLRNW = new ImageButton(skin.getDrawable("FigureLRNW"));
        FigureLRNW.setPosition(300,500);
        FigureLRNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLRNW.setDisabled(true);
            }
        });
        FigureLSHW = new ImageButton(skin.getDrawable("FigureLSHW"));
        FigureLSHW.setPosition(500,500);
        FigureLSHW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSHW.setDisabled(true);
            }
        });
        FigureLSNW = new ImageButton(skin.getDrawable("FigureLSNW"));
        FigureLSNW.setPosition(700,500);
        FigureLSNW.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SFXClick.play();
                FigureLSNW.setDisabled(true);
            }
        });
        Texture texture = game.assets.manager.get(game.assets.black);
        TextureRegion myTextureRegion = new TextureRegion(texture);
        Drawable drawable = new TextureRegionDrawable(myTextureRegion);
        black = new ImageButton(drawable);
        black.setPosition(900,550);
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
        myTextureRegion = new TextureRegion(texture);
        drawable = new TextureRegionDrawable(myTextureRegion);
        white = new ImageButton(drawable);
        white.setPosition(900,150);
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

    public ImageButton getBlack() {
        return black;
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

    public void setupMisc(){
        wooden_field = game.assets.manager.get(game.assets.wooden_field);
        board = game.assets.manager.get(game.assets.board);
        SFXClick = game.assets.manager.get(game.assets.SFXclick);
        menuMusic = game.assets.manager.get(game.assets.menuMusic);
        gameplayMusic = game.assets.manager.get(game.assets.gameplayMusic);
    }

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
}
