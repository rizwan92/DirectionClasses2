package com.directionclasses.directionclasses;

/**
 * Created by rizwan on 15-Mar-17.
 */

public class Board {

    private int board_id;
    private String board_name;



    public Board(int board_id, String board_name) {
        this.board_id = board_id;
        this.board_name = board_name;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }
}