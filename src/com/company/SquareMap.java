package com.company;

public class SquareMap {
    public int id;
    public int neighbor_bombs;
    public int discovered;
    public SquareMap(int id){
        this.id = id;
        this.neighbor_bombs = 0;
        this.discovered = 0;
    }
}
