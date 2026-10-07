package edu.uoc.ds.adt;


public class Segment {
    private int x;
    private int y;

    public Segment(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }

        if (obj == null || getClass() != obj.getClass()) { return false; }

        Segment segment = (Segment) obj;

        return this.x == segment.x && this.y == segment.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
