package com.mechwv.quarto;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mechwv.quarto.managers.AssetsLoader;
import com.mechwv.quarto.managers.GameManager;
import com.mechwv.quarto.managers.InputManager;
import com.mechwv.quarto.screens.MainMenuScreen;


public class GameRoot extends Game {
	public SpriteBatch spriteBatch;
	public BitmapFont font;
	public float screenx;
	public float screeny;
	public final float virtual_screen_width = 1080;
	public final float virtual_screen_height = 1920;
	public AssetsLoader assets = new AssetsLoader();
	public InputManager im;
	public int turn = 1;
	public GameManager gm;
	public Music music;

	public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщьыъэюяabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		assets.load();
		assets.manager.finishLoading();
		gm = new GameManager(this);
        FileHandle fontFile = Gdx.files.internal("fonts/russoone.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
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
