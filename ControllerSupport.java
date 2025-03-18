package ProgettiMiei.Java.SquareController;
// https://github.com/williamahartman/Jamepad/releases/download/1.3.2/Jamepad.jar

import com.studiohartman.jamepad.*;

public class ControllerSupport implements Runnable {
    protected String lastControllerButton = "No button pressed yet";
    protected int lastControllerButtonCode = -1;
    protected boolean aPressed = false;
    protected boolean xPressed = false;
    protected boolean rightBumberPressed = false;
    protected boolean leftBumberPressed = false;

    @Override
    public void run() {
        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad(); // Inizializza Jamepad

        // Attendi che un controller venga collegato
        System.out.println("Attendi che un controller venga collegato...");
        while (controllers.getNumControllers() == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ControllerIndex controller = controllers.getControllerIndex(0);
        
        if (!controller.isConnected()) {
            System.out.println("Nessun controller trovato!");
            return;
        }

        try {
            System.out.println("Controller rilevato: " + controller.getName());
        } catch (ControllerUnpluggedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Ciclo di lettura input
        while (true) {
            lastControllerButtonCode = -1;  // Se rimuovi questa riga il movimento sarà tipo snake
            try {
                if (controller.isButtonPressed(ControllerButton.DPAD_DOWN)) {
                    //! System.out.println("> Freccia in basso premuta");
                    lastControllerButton = "DPad down";
                    lastControllerButtonCode = 0;
                }
                else if (controller.isButtonPressed(ControllerButton.DPAD_LEFT)) {
                    //! System.out.println("> Freccia sinistra premuta");
                    lastControllerButton = "DPad left";
                    lastControllerButtonCode = 1;
                }
                else if (controller.isButtonPressed(ControllerButton.DPAD_UP)) {
                    //! System.out.println("> Freccia in alto premuta");
                    lastControllerButton = "DPad up";
                    lastControllerButtonCode = 2;
                }
                else if (controller.isButtonPressed(ControllerButton.DPAD_RIGHT)) {
                    //! System.out.println("> Freccia destra premuta");
                    lastControllerButton = "DPad right";
                    lastControllerButtonCode = 3;
                }
            } catch (ControllerUnpluggedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                if (controller.isButtonPressed(ControllerButton.A) && !aPressed) {   //? Aggiungo un quadrato
                    aPressed = true;
                    lastControllerButton = "A";
                    lastControllerButtonCode = -1;
                    GamePanel.squares.add(new Square());
                    if(GamePanel.squareSelected == 0){
                        GamePanel.squareSelected = 1;
                    }
                } if(!controller.isButtonPressed(ControllerButton.A)){
                    aPressed = false;
                }
                if (controller.isButtonPressed(ControllerButton.X) && !xPressed) {  //? Rimuovo un quadrato
                    xPressed = true;
                    lastControllerButton = "X";
                    lastControllerButtonCode = -1;
                    if(GamePanel.squares.size() != 0){
                        GamePanel.squares.remove(GamePanel.squareSelected - 1);
                        if(GamePanel.squareSelected > GamePanel.squares.size()){
                            GamePanel.squareSelected--;
                        }
                    }
                } if(!controller.isButtonPressed(ControllerButton.X)){
                    xPressed = false;
                }
                if (controller.isButtonPressed(ControllerButton.RIGHTBUMPER) && !rightBumberPressed) {  //? Seleziono il quadrato successivo
                    rightBumberPressed = true;
                    lastControllerButton = "R1";
                    lastControllerButtonCode = -1;
                    if(GamePanel.squareSelected < GamePanel.squares.size()){
                        GamePanel.squareSelected++;
                    } else{
                        GamePanel.squareSelected = 1;
                    }
                }
                if(!controller.isButtonPressed(ControllerButton.RIGHTBUMPER)){
                    rightBumberPressed = false;
                }
                if (controller.isButtonPressed(ControllerButton.LEFTBUMPER) && !leftBumberPressed) {  //? Seleziono il quadrato precedente
                    leftBumberPressed = true;
                    lastControllerButton = "L1";
                    lastControllerButtonCode = -1;
                    if(GamePanel.squareSelected > 1){
                        GamePanel.squareSelected--;
                    } else{
                        GamePanel.squareSelected = GamePanel.squares.size();
                    }
                } if(!controller.isButtonPressed(ControllerButton.LEFTBUMPER)){
                    leftBumberPressed = false;
                }
            } catch (ControllerUnpluggedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            /* loat leftX = 0;
            float leftY = 0;
            try {
                leftX = controller.getAxisState(ControllerAxis.LEFTX);
            } catch (ControllerUnpluggedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                leftY = controller.getAxisState(ControllerAxis.LEFTY);
            } catch (ControllerUnpluggedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.printf("🎮 Stick sinistro: X=%.2f, Y=%.2f%n", leftX, leftY); */

            // Attendi 50ms per non sovraccaricare la CPU
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLastControllerButtonText() {
        return lastControllerButton;
    }

    public int getLastControllerButtonCode() {
        return lastControllerButtonCode;
    }
}
