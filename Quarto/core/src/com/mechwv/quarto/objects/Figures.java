package com.mechwv.quarto.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mechwv.quarto.GameRoot;
import com.mechwv.quarto.managers.AssetsLoader;
import com.mechwv.quarto.managers.GameManager;

import java.util.HashMap;
import java.util.Map;

public class Figures {
    private TextureAtlas figure_pack;
    private Skin skin;

    public void figuresSetup(GameManager GM, AssetsLoader assets){
        figure_pack = assets.manager.get(assets.figure_pack);
        skin = new Skin();
        skin.addRegions(figure_pack);
        GM.textureRegions.put("FigureHRHB",skin.get("FigureHRHB", TextureRegion.class));
        GM.textureRegions.put("FigureHRHW",skin.get("FigureHRHW", TextureRegion.class));
        GM.textureRegions.put("FigureHRNB",skin.get("FigureHRNB", TextureRegion.class));
        GM.textureRegions.put("FigureHRNW",skin.get("FigureHRNW", TextureRegion.class));
        GM.textureRegions.put("FigureHSHB",skin.get("FigureHSHB", TextureRegion.class));
        GM.textureRegions.put("FigureHSHW",skin.get("FigureHSHW", TextureRegion.class));
        GM.textureRegions.put("FigureHSNB",skin.get("FigureHSNB", TextureRegion.class));
        GM.textureRegions.put("FigureHSNW",skin.get("FigureHSNW", TextureRegion.class));
        GM.textureRegions.put("FigureLRHB",skin.get("FigureLRHB", TextureRegion.class));
        GM.textureRegions.put("FigureLRHW",skin.get("FigureLRHW", TextureRegion.class));
        GM.textureRegions.put("FigureLRNB",skin.get("FigureLRNB", TextureRegion.class));
        GM.textureRegions.put("FigureLRNW",skin.get("FigureLRNW", TextureRegion.class));
        GM.textureRegions.put("FigureLSHB",skin.get("FigureLSHB", TextureRegion.class));
        GM.textureRegions.put("FigureLSHW",skin.get("FigureLSHW", TextureRegion.class));
        GM.textureRegions.put("FigureLSNB",skin.get("FigureLSNB", TextureRegion.class));
        GM.textureRegions.put("FigureLSNW",skin.get("FigureLSNW", TextureRegion.class));
    }

}
