package edu.uoc.ds.adt.model;

import edu.uoc.ds.adt.Segment;
import edu.uoc.ds.adt.sequential.LinkedList;


public class Snake implements ISnake{
    private LinkedList<Segment> segments;

    public Snake() {
        this.segments = new LinkedList<>();
    }

    @Override
    public Segment head() {
        return this.segments.peekFirst();
    }

    @Override
    public Segment tail() {
        return this.segments.peekLast();
    }

    @Override
    public void addSegment(int x, int y) {
        this.segments.insertEnd(new Segment(x, y));
    }

    @Override
    public void addHead(Segment newHead) {
        this.segments.insertBeginning(newHead);
    }

    @Override
    public Segment deleteTail() {
        return this.segments.deleteLast();
    }

    @Override
    public int size() {
        return this.segments.size();
    }
}
