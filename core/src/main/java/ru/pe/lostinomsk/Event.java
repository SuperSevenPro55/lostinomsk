package ru.pe.lostinomsk;

public class Event {
    private int x, y, width, height;
    Event(int x, int y, int width, int height) {
        this.x = x;                 
        this.y = y;
        this.width = width;
        this.height = height;
    } 

    public int getX() {return x;}
    public int getY() {return y;}
    public int getW() {return width;}
    public int getH() {return height;}
}
