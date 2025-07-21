package SquareController;

import java.awt.Color;
import java.awt.Graphics;

public class Square {
    protected int WIDTH = 50;
    protected int xSquare;
    protected int ySquare;
    Color randomColor;

    public Square(){
        this.randomColor = new Color((int)(Math.random() * 0x1000000));
        this.WIDTH = 50;
        this.xSquare = GamePanel.panelWidth / 2 - WIDTH;
        this.ySquare = GamePanel.panelHeight / 2 - WIDTH;
    }

    protected void DrawSquare(Graphics g){
        g.setColor(randomColor);
        g.fillRect(xSquare, ySquare, WIDTH, WIDTH);
    }

    protected void MoveSquare(){
        if (Game.controllerSupport.getLastControllerButtonCode() == 0) {
            ySquare += 5;
        }
        if (Game.controllerSupport.getLastControllerButtonCode() == 1) {
            xSquare -= 5;
        }
        if (Game.controllerSupport.getLastControllerButtonCode() == 2) {
            ySquare -= 5;
        }
        if (Game.controllerSupport.getLastControllerButtonCode() == 3) {
            xSquare += 5;
        }

        if (xSquare < 0) {
            xSquare = 0;
        }
        if (xSquare > GamePanel.panelWidth - WIDTH) {
            xSquare = GamePanel.panelWidth - WIDTH;
        }
        if (ySquare < 0) {
            ySquare = 0;
        }
        if (ySquare > GamePanel.panelHeight - WIDTH) {
            ySquare = GamePanel.panelHeight - WIDTH;
        }
    }

    protected void DrawSelection(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(xSquare - 1, ySquare - 1, WIDTH + 2, WIDTH + 2);
        g.setColor(randomColor);
        g.drawRect(xSquare, ySquare, WIDTH, WIDTH);
    }
}