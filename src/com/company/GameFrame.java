package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class GameFrame extends JFrame implements MouseListener {

    GamePanel panel;

    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("MineSweeper");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            panel.mouse1_clicked(e);
        else if(e.getButton() == MouseEvent.BUTTON2)
            panel.mouse2_clicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
}
