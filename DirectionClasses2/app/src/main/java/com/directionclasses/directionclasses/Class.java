package com.directionclasses.directionclasses;

/**
 * Created by rizwan on 18-Mar-17.
 */

public class Class {

    private int class_id;
    private String class_name;
    private int board_id;

    public Class(int class_id, String class_name, int board_id) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.board_id = board_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }
}
