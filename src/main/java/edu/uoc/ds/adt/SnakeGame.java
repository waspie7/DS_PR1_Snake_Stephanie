package edu.uoc.ds.adt;

import edu.uoc.ds.adt.model.Snake;
import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.adt.exceptions.SnakeOutOfBoundsException;


public class SnakeGame implements ISnakeGame {
    private char[][] board;
    private Snake snake;
    private Queue<LogEntry> log;

    public SnakeGame() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.snake = new Snake();
        this.log = new QueueArrayImpl<>(MAX_LOG_SIZE);

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                this.board[y][x] = '.';
            }
        }
    }

    @Override
    public void addSegment(int x, int y) {
        this.snake.addSegment(x, y);
        this.board[y][x] = 'S';
    }

    @Override
    public void addSegments(int[][] segments) {
        for (int i = 0; i < segments.length; i++) {
            this.addSegment(segments[i][0], segments[i][1]);
        }
    }

    @Override
    public int size() {
        return this.snake.size();
    }

    @Override
    public Segment head() {
        return this.snake.head();
    }

    @Override
    public Segment tail() {
        return this.snake.tail();
    }

    @Override
    public void addFood(int x, int y) {
        this.board[y][x] = '@';
    }

    @Override
    public boolean hasFoodAt(int x, int y) {
        return this.board[y][x] == '@';
    }

    @Override
    public char getSegment(int x, int y) {
        return this.board[y][x];
    }

    @Override
    public Iterator<LogEntry> logEntries() {
        return this.log.values();
    }

    @Override
    public void move(Direction direction) throws SnakeOutOfBoundsException {
        Segment currentHead = this.head();

        int newX = currentHead.getX() + direction.getDx();
        int newY = currentHead.getY() + direction.getDy();

        // Compruebo si hay colision
        if (newX < 0 || newX >= this.board[0].length ||
                newY < 0 || newY >= this.board.length) {

            this.log.add(new LogEntry(
                    currentHead.getX(),
                    currentHead.getY(),
                    direction,
                    "COLLISION"
            ));

            throw new SnakeOutOfBoundsException();
        }

        boolean food = this.hasFoodAt(newX, newY);

        if (!food) {
            this.snake.addHead(new Segment(newX, newY));

            Segment oldTail = this.snake.deleteTail();

            this.board[oldTail.getY()][oldTail.getX()] = '.';
            this.board[newY][newX] = 'S';

            this.log.add(new LogEntry(
                    currentHead.getX(),
                    currentHead.getY(),
                    direction,
                    "MOVE"
            ));
        } else {
            this.snake.addHead(new Segment(newX, newY));

            this.board[newY][newX] = 'S';

            this.log.add(new LogEntry(
                    currentHead.getX(),
                    currentHead.getY(),
                    direction,
                    "EAT"
            ));

            this.log.add(new LogEntry(
                    currentHead.getX(),
                    currentHead.getY(),
                    direction,
                    "MOVE"
            ));
        }
    }
}