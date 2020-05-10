package com.mechwv.quarto.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mechwv.quarto.objects.Board;

public class FigurePlace {

    private final Vector2 y1 = new Vector2(1700,1600);
    private final Vector2 y2 = new Vector2(1605,1535);
    private final Vector2 y3 = new Vector2(1525,1440);
    private final Vector2 y4 = new Vector2(1430,1355);
    private final Vector2 y5 = new Vector2(1340,1250);
    private final Vector2 y6 = new Vector2(1230,1115);
    private final Vector2 y7 = new Vector2(1100,984);

    private final Vector2 x1 = new Vector2(107,228);
    private final Vector2 x2 = new Vector2(250,355);
    private final Vector2 x3 = new Vector2(380,480);
    private final Vector2 x4 = new Vector2(490,603);
    private final Vector2 x5 = new Vector2(610,710);
    private final Vector2 x6 = new Vector2(730,860);
    private final Vector2 x7 = new Vector2(870,994);

    private Board board;

    public FigurePlace(){

    }
    public FigurePlace(String name, Board board, float x, float y){
        this.board = board;
        byte c = getBoardCell(x,y);
        placeOnBoard(c,name,board);
    }


    private void placeOnBoard(byte c, String name, Board board){
        if (c != -1) {
            if (board.board[c].equals("-"))
                board.board[c] = (name);
        }
    }

    private byte checkY(float y) {
        if ((y < y1.x) && (y > y1.y)) {
            return 1;
        }
        if ((y < y2.x) && (y > y2.y)) {
            return 2;
        }
        if ((y < y3.x) && (y > y3.y)) {
            return 3;
        }
        if ((y < y4.x) && (y > y4.y)) {
            return 4;
        }
        if ((y < y5.x) && (y > y5.y)) {
            return 5;
        }
        if ((y < y6.x) && (y > y6.y)) {
            return 6;
        }
        if ((y < y7.x) && (y > y7.y)) {
            return 7;
        }
        return 0;
    }

    private byte checkX(float x) {
            if ((x > x1.x) && (x < x1.y)) {
                return 1;
            }
            if ((x > x2.x) && (x < x2.y)) {
                return 2;
            }
            if ((x > x3.x) && (x < x3.y)) {
                return 3;
            }
            if ((x > x4.x) && (x < x4.y)) {
                return 4;
            }
            if ((x > x5.x) && (x < x5.y)) {
                return 5;
            }
            if ((x > x6.x) && (x < x6.y)) {
                return 6;
            }
            if ((x > x7.x) && (x < x7.y)) {

                return 7;
            }
            return 0;
        }

    private byte getBoardCell(float x, float y){
        byte a = checkY(y);
        byte b = checkX(x);
        switch (a){
            case 1:{
                if (b == 4){
                    Gdx.app.log("RRRRR", "1");
                    return 0;
                }
            }
            case 2:{
                if (b == 3){
                    Gdx.app.log("RRRRR", "2");
                    return 1;
                }
                if (b == 5){
                    Gdx.app.log("RRRRR", "3");
                    return 2;
                }
            }
            case 3:{
                if (b == 2){
                    Gdx.app.log("RRRRR", "4");
                    return 3;
                }
                if (b == 4){
                    Gdx.app.log("RRRRR", "5");
                    return 4;
                }
                if (b == 6){
                    Gdx.app.log("RRRRR", "6");
                    return 5;
                }
            }
            case 4:{
                if (b == 1){
                    Gdx.app.log("RRRRR", "7");
                    return 6;
                }
                if (b == 3){
                    Gdx.app.log("RRRRR", "8");
                    return 7;
                }
                if (b == 5){
                    Gdx.app.log("RRRRR", "9");
                    return 8;
                }
                if (b == 7){
                    Gdx.app.log("RRRRR", "10");
                    return 9;
                }
            }
            case 5:{
                if (b == 2){
                    Gdx.app.log("RRRRR", "11");
                    return 10;
                }
                if (b == 4){
                    Gdx.app.log("RRRRR", "12");
                    return 11;
                }
                if (b == 6){
                    Gdx.app.log("RRRRR", "13");
                    return 12;
                }
            }
            case 6:{
                if (b == 3){
                    Gdx.app.log("RRRRR", "14");
                    return 13;
                }
                if (b == 5){
                    Gdx.app.log("RRRRR", "15");
                    return 14;
                }
            }
            case 7:{
                if (b == 4){
                    Gdx.app.log("RRRRR", "16");
                    return 15;
                }
            }
        }
        return -1;
    }

    public Vector2 getCellCoord(int a){
        Vector2 v = new Vector2(0,0);
        switch (a)
        {
            case 0:{
                v = new Vector2(((x4.x+x4.y)/2-60),((y1.x+y1.y)/2-30));
                return v;
            }
            case 1:{
                v = new Vector2(((x3.x+x3.y)/2-65),((y2.x+y2.y)/2-30));
                return v;
            }
            case 2:{
                v = new Vector2(((x5.x+x5.y)/2-65),((y2.x+y2.y)/2-30));
                return v;
            }
            case 3:{
                v = new Vector2(((x2.x+x2.y)/2-70),((y3.x+y3.y)/2-39));
                return v;
            }
            case 4:{
                v = new Vector2(((x4.x+x4.y)/2-65),((y3.x+y3.y)/2-30));
                return v;
            }
            case 5:{
                v = new Vector2(((x6.x+x6.y)/2-70),((y3.x+y3.y)/2-30));
                return v;
            }
            case 6:{
                v = new Vector2(((x1.x+x1.y)/2-75),((y4.x+y4.y)/2-30));
                return v;
            }
            case 7:{
                v = new Vector2(((x3.x+x3.y)/2-75),((y4.x+y4.y)/2-30));
                return v;
            }
            case 8:{
                v = new Vector2(((x5.x+x5.y)/2-60),((y4.x+y4.y)/2-30));
                return v;
            }
            case 9:{
                v = new Vector2(((x7.x+x7.y)/2-65),((y4.x+y4.y)/2-30));
                return v;
            }
            case 10:{
                v = new Vector2(((x2.x+x2.y)/2-85),((y5.x+y5.y)/2-30));
                return v;
            }
            case 11:{
                v = new Vector2(((x4.x+x4.y)/2-65),((y5.x+y5.y)/2-30));
                return v;
            }
            case 12:{
                v = new Vector2(((x6.x+x6.y)/2-60),((y5.x+y5.y)/2-30));
                return v;
            }
            case 13:{
                v = new Vector2(((x3.x+x3.y)/2-70),((y6.x+y6.y)/2-30));
                return v;
            }
            case 14:{
                v = new Vector2(((x5.x+x5.y)/2-65),((y6.x+y6.y)/2-30));
                return v;
            }
            case 15:{
                v = new Vector2(((x4.x+x4.y)/2-75),((y7.x+y7.y)/2-30));
                return v;
            }
        }
        return v;
    }

    public boolean place_check(Board brd, float x, float y){
        byte c = getBoardCell(x,y);
        Gdx.app.log("fffff", "board checked");
        if (c != -1) {
            return brd.board[c].equals("-");
        }
        return false;
    }
}
