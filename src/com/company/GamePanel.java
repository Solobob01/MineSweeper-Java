package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{


    public int difficulty = 0;
    public int[] nr_bombs = {15,20,25};
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 1000;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    public Map map;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;

    GamePanel(){

        //generate new map
        newMap();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getWidth());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
        repaint();
    }

    public void draw(Graphics g){
        if(!map.gameOver)
            map.draw(g);
        else {
            map.draw(g);
            map.gameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void newMap(){
        map = new Map(SCREEN_SIZE.width,SCREEN_SIZE.height,nr_bombs[0]);
    }

    public void mouse1_clicked(MouseEvent e){
        map.mouse1Clicked(e);
    }

    public void mouse2_clicked(MouseEvent e){
        map.mouse2Clicked(e);
    }


    @Override
    public void run() {

    }

}
