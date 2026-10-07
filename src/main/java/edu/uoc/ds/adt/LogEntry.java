package edu.uoc.ds.adt;

import edu.uoc.ds.adt.sequential.LinkedList;


public class LogEntry {
    private int x;
    private int y;
    private ISnakeGame.Direction direction;
    private String msg;

    public LogEntry(int x, int y, ISnakeGame.Direction direction, String msg) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.msg = msg;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ISnakeGame.Direction getDirection() {
        return this.direction;
    }

    public String getMsg() {
        return this.msg;
    }
}
