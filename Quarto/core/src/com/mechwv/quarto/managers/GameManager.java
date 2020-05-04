package com.mechwv.quarto.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.objects.Figures;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Texture wooden_field;
    private Texture board;
    public Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();

    private Figures figures = new Figures();

    public GameManager(final GameRoot game){
        figures.figuresSetup(this,game.assets);
        wooden_field = game.assets.manager.get(game.assets.wooden_field);
    }
}
