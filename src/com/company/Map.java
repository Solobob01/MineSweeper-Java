package com.company;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.*;

public class Map {
    public SquareMap[][] map;

    public Bomb bomb = new Bomb();
    public Tile tile = new Tile();
    public Number number = new Number();
    public Flag flag = new Flag();

    public boolean gameOver = false;

    public Map(int width,int height,int bombs){
        map = new SquareMap[width/bombs][height/bombs];
        for(int x = 0; x < map.length ; x++){
            for(int y = 0; y < map[0].length ; y++){
                int rand = (int)(Math.random()*(30-bombs));
                if(rand == 0){
                    map[x][y] = new SquareMap(bomb.ID);
                }
                else
                    map[x][y] = new SquareMap(tile.ID);
            }
        }
        find_neighbor();
    }

    public void draw(Graphics g){
        for(int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y].discovered == 0) {

                    draw_tile(g, x * tile.width, y * tile.height);
                } else {
                    //System.out.println("AAAA");
                    if (map[x][y].id == bomb.ID) {
                        draw_bomb(g, x * tile.width, y * tile.height);
                    }
                    if (map[x][y].id == number.ID) {

                        draw_tile(g, x * tile.width, y * tile.height);
                        draw_number(g, x, y);
                    }
                }
            }
        }
    }
    public void draw_number(Graphics g,int mapX,int mapY){
        int fontSize = 50;
        g.setColor(Color.black);
        g.setFont(new Font("serif",Font.BOLD,fontSize));
        g.drawString(Integer.toString(map[mapX][mapY].neighbor_bombs),mapX*tile.width  + 33,(mapY + 1) * tile.height - 33);
    }

    public void draw_bomb(Graphics g ,int x,int y){
        g.setColor(bomb.color);
        g.fillRect(x,y,bomb.width,bomb.height);
    }
    public void draw_tile(Graphics g,int x ,int y){

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(tile.color);
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(5));
        g.drawRect(x,y,tile.width,tile.height);
        g2.setStroke(oldStroke);
    }

    public void mouse1Clicked(MouseEvent e){
        for(int x = 0;x < map.length; x++){
            for(int y = 0; y < map[0].length; y++){
                if(intersect(e.getX(),e.getY(),x,y)){
                    tile_clicked(x,y);
                }
            }
        }

    }

    public void mouse2Clicked(MouseEvent e){

    }

    public void tile_clicked(int x,int y){
        map[x][y].discovered = 1;
        System.out.println("Ok");
        if(map[x][y].id == bomb.ID){

            System.out.println("Game Over");
            gameOver = true;
        }
        else if(map[x][y].id == tile.ID){
            System.out.println("Transform in number");
            map[x][y].id = number.ID;
        }
    }

    public void gameOver(Graphics g){
        int fontSize = 50;
        g.setColor(Color.black);
        g.setFont(new Font("serif",Font.BOLD,fontSize));
        g.drawString("Ai pierdut",500,500);
    }

    public boolean intersect(int mouseX,int mouseY,int mapX,int mapY){
        System.out.println(mouseX + " " + mouseY);
        if(mouseX > mapX * tile.width && mouseX < (mapX+1) * tile.width
                && mouseY > mapY * tile.width && mouseY < (mapY+1) * tile.height)
            return true;
        return false;
    }

    public void find_neighbor(){
        for(int x = 0; x < map.length; x++){
            for(int y = 0 ;y < map[0].length; y++){
                if(map[x][y].id == tile.ID){
                    determine_neighbors(x,y);
                }
            }
        }
    }

    public void determine_neighbors(int x,int y){
        if( x > 0 && x < map.length - 1){
            if(y > 0 && y < map[0].length - 1){
                for(int i = x - 1;i <= x + 1 ; i++){
                    for(int j = y - 1; j <= y + 1; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            else if(y == 0){
                for(int i = x - 1 ; i <= x + 1;i++){
                    for(int j = y ; j <= y + 1;j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            else if(y == map[0].length - 1)
            {
                for(int i = x - 1 ; i <= x + 1;i++){
                    for(int j = y - 1 ; j <= y;j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
        }
        else if(x == 0){
            if(y > 0 && y < map[0].length - 1){
                for(int i = x;i <= x + 1 ; i++){
                    for(int j = y - 1; j <= y + 1; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            else if(y == 0){
                for(int i = x ; i <= x + 1; i++){
                    for(int j = y; j <= y + 1;j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            else if(y == map[0].length - 1){
                for(int i = 0; i <= x + 1; i++){
                    for(int j = y - 1; j <= y; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }

        }
        else if(x == map.length - 1){
            if(y > 0 && y < map[0].length - 1){
                for(int i = x - 1;i <= x ; i++){
                    for(int j = y - 1; j <= y + 1; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            else if(y == 0){
                for(int i = x - 1; i <= x ; i++){
                    for(int j = y; j <= y+1 ; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
            }
            else if(y == map[0].length)
            {
                for(int i = x - 1;i <= x; i++){
                    for(int j = y - 1; j <= y; j++){
                        if(map[i][j].id == bomb.ID){
                            map[x][y].neighbor_bombs++;
                        }
                    }
                }
            }
    }

}
