package ProgettiMiei.Java.SquareController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    protected static int panelWidth = 1300; // ?Larghezza schermo
    protected static int panelHeight = 630; // ?Altezza schermo
    protected static int mouseX = 0;
    protected static int mouseY = 0;
    protected static int FPSToDisplay = 0;  //? Visualizzo gli FPS a schermo
    private static long lastSec = System.nanoTime();    //? Salva il secondo precedente
    
    protected static ArrayList<Square> squares = new ArrayList<>();
    protected static int squareSelected = 0;

    int cicli = 0;

    public GamePanel() {
        setPanelSize();

        this.setBackground(Color.DARK_GRAY);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(panelWidth, panelHeight);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) { // Scrivo in questo void le cose che voglio disegnare
        super.paintComponent(g);
        
        mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX() - Game.getxLoc();
        mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY() - Game.getyLoc();

        Square square;
        for(int i = 0; i < squares.size(); i++){
            square = squares.get(i);
            square.DrawSquare(g);
            i++;
            if(i == squareSelected){
                square.DrawSelection(g);
                square.MoveSquare();
            }
            i--;
        }

        //? Disegno le scritte
        WriteTextOnScreen(g);

        if (System.nanoTime() - lastSec > 1000000000) { // ? Entro in questo if una volta al secondo
            lastSec = System.nanoTime();
            FPSToDisplay = cicli;

            panelWidth = (int) GameWindow.getjFrameSize().getWidth() - 16;
            panelHeight = (int) GameWindow.getjFrameSize().getHeight() - 39;

            cicli = 0;
        }

        cicli++;
    }

    private void WriteTextOnScreen(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Dimension: " + panelWidth + " x " + panelHeight, 3, 15);
        g.drawString("Last button pressed: " + Game.controllerSupport.getLastControllerButtonText(), 3, 30);
        g.drawString("Square selected: " + squareSelected + "/" + squares.size(), 3, 45);
        g.drawString("FPS: " + FPSToDisplay, panelWidth - 45, 15);
    }
}