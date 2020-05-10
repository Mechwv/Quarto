package com.mechwv.quarto.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mechwv.quarto.gameplay.FigurePlace;
import com.mechwv.quarto.objects.Board;

public class InputManager extends InputAdapter {
    OrthographicCamera camera;
    static Vector3 temp = new Vector3();
    private GameManager gm;
    private ImageButton btn;
    private String name;
    private Board board;
    private Vector2 coord = new Vector2();
    public InputManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0);
        Gdx.app.log("RRRRR", "apsodpaospdpasd");
        camera.unproject(temp);
        float touchX = temp.x;
        float touchY = temp.y;
        coords(touchX, touchY);
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        return false;
    }

    private void coords(float x, float y){
        coord.x = x;
        coord.y = y;
    }
    public float getCoordX(){
        return coord.x;
    }
    public float getCoordY(){
        return coord.y;
    }
}
