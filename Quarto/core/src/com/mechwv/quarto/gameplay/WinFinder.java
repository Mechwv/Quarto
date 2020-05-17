package com.mechwv.quarto.gameplay;

import com.mechwv.quarto.objects.Board;

public class WinFinder {
    public int checkBoard(Board board){
        for (int i = 6; i < 10; i++){
            if (board.checkrow(0,4,11,15)) {
                if ((board.getattribute(i, 0) == board.getattribute(i, 4)) && (board.getattribute(i, 4) == board.getattribute(i, 11)) && (board.getattribute(i, 11) == board.getattribute(i, 15))) {
                    return 1;
                }
            }
            if (board.checkrow(6,7,8,9))
                if ((board.getattribute(i,6) == board.getattribute(i,7))&&(board.getattribute(i,7) == board.getattribute(i,8))&&(board.getattribute(i,8) == board.getattribute(i,9))) {
                    return 1;
                }
            if (board.checkrow(0,1,3,6))
                if ((board.getattribute(i,0) == board.getattribute(i,1))&&(board.getattribute(i,1) == board.getattribute(i,3))&&(board.getattribute(i,3) == board.getattribute(i,6))) {
                    return 1;
                }
            if (board.checkrow(2,4,7,10))
                if ((board.getattribute(i,2) == board.getattribute(i,4))&&(board.getattribute(i,4) == board.getattribute(i,7))&&(board.getattribute(i,7) == board.getattribute(i,10))) {
                    return 1;
                }
            if (board.checkrow(5,8,11,13))
                if ((board.getattribute(i,5) == board.getattribute(i,8))&&(board.getattribute(i,8) == board.getattribute(i,11))&&(board.getattribute(i,11) == board.getattribute(i,13))) {
                    return 1;
                }
            if (board.checkrow(9,12,14,15))
                if ((board.getattribute(i,9) == board.getattribute(i,12))&&(board.getattribute(i,12) == board.getattribute(i,14))&&(board.getattribute(i,14) == board.getattribute(i,15))) {
                    return 1;
                }
            if (board.checkrow(0,2,5,9))
                if ((board.getattribute(i,0) == board.getattribute(i,2))&&(board.getattribute(i,2) == board.getattribute(i,5))&&(board.getattribute(i,5) == board.getattribute(i,9))) {
                    return 1;
                }
            if (board.checkrow(1,4,8,12))
                if ((board.getattribute(i,1) == board.getattribute(i,4))&&(board.getattribute(i,4) == board.getattribute(i,8))&&(board.getattribute(i,8) == board.getattribute(i,12))) {
                    return 1;
                }
            if (board.checkrow(3,7,11,14))
                if ((board.getattribute(i,3) == board.getattribute(i,7))&&(board.getattribute(i,7) == board.getattribute(i,11))&&(board.getattribute(i,11) == board.getattribute(i,14))) {
                    return 1;
                }
            if (board.checkrow(6,10,13,15))
                if ((board.getattribute(i,6) == board.getattribute(i,10))&&(board.getattribute(i,10) == board.getattribute(i,13))&&(board.getattribute(i,13) == board.getattribute(i,15))) {
                    return 1;
                }
        }
        int k = 0;
        int i = 0;
        for (i = 0; i < 16; i++){
            if (!board.board[i].equals("-"))
                k++;
        }
        if (i == k) return 2;
        return 0;
    }
}

