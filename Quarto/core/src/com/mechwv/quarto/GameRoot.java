package com.mechwv.quarto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mechwv.quarto.screens.MainMenuScreen;
import com.mechwv.quarto.managers.AssetsLoader;

import java.util.HashMap;
import java.util.Map;


public class GameRoot extends Game {
	public SpriteBatch spriteBatch;
	public BitmapFont font;
	public float Screenx;
	public float Screeny;
	public final float virtual_screen_width = 1080;
	public final float virtual_screen_height = 1920;
	public AssetsLoader assets = new AssetsLoader();

	public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщьыъэюяabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		assets.load();
		assets.manager.finishLoading();
        FileHandle fontFile = Gdx.files.internal("fonts/russoone.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
}
