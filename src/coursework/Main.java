package coursework;

import coursework.exceptions.WrongCountException;
import coursework.frame.dialogs.ParametersDialog;
import coursework.frame.Window;
import coursework.model.Model;

import javax.swing.*;

public class Main {
    private static Model model;
    private static Window window;
    private static boolean isRunning;
    private static JDialog parametersDialog;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            window = new Window("3d shape");
            parametersDialog = new ParametersDialog(window);
            parametersDialog.setVisible(true);
        });
    }

    public static void createModel(double[] parameters) {
        try {
            model = new Model(parameters[0], parameters[1], parameters[2],
                    parameters[3], parameters[4]);
        } catch (WrongCountException exception) {
            System.out.println(exception.getMessage());
        }
    }

    //TODO: make a hard decision
//    public static void runMainLoop() {
//        int requiredFps = 60;
//        int updatesPerSecond = 200;
//        int ticksCount = 0;
//        int framesCount = 0;
//        long newSecondStart = System.nanoTime();
//        long lastTime = newSecondStart;
//        long currentTime;
//        long deltaFps = 0;
//        long deltaUps = 0;
//        long oneSecond = 1000000000;
//        isRunning = true;
//        while (isRunning) {
//            if (deltaUps >= oneSecond / updatesPerSecond) {
//                model.update();
//                ticksCount++;
//                deltaUps = 0;
//            }
//            currentTime = System.nanoTime();
//            deltaUps += currentTime - lastTime;
//            deltaFps += currentTime - lastTime;
//            lastTime = currentTime;
//            if (deltaFps >= oneSecond / requiredFps) {
//                window.repaint();
//                deltaFps = 0;
//                framesCount++;
//            }
//            if (System.nanoTime() - newSecondStart >= oneSecond) {
//                System.out.printf("FPS: %d Ticks: %d\n",
//                        framesCount, ticksCount);
//                framesCount = 0;
//                ticksCount = 0;
//                newSecondStart = System.nanoTime();
//            }
//        }
//    }

    public static void setRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    public static Model getModel() {
        return model;
    }

    public static Window getWindow() {
        return window;
    }

    public static JDialog getParametersDialog() {
        return parametersDialog;
    }
}
