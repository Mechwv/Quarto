package com.mechwv.quarto.objects;

import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Board {
public String[] board;
public Vector<Vector2> coords = new Vector<Vector2>();

public Board(){
    board = new String[]
            {
                    "-",
                "-",    "-",
            "-",    "-",    "-",
        "-",    "-",    "-",    "-",
            "-",    "-",    "-",
                "-",    "-",
                    "-"
            };

    }
public char getattribute(int atr, int num){
    return board[num].charAt(atr);
}
public boolean checkrow(int a ,int b, int c, int d){
    if (!board[a].equals("-"))
        if (!board[b].equals("-"))
            if (!board[c].equals("-"))
                if (!board[d].equals("-"))
                    return true;

    return false;
}
}