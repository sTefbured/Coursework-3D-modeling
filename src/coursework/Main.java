package coursework;

import coursework.exceptions.WrongCountException;

public class Main {
    private static Model model;
    private static Window window;

    // TODO: create main loop
    public static void main(String[] args) {
        if (initializeModel(args) != 0) {
            return;
        }
        window = new Window("3d shape");
        runMainLoop();
    }

    private static int initializeModel(String[] args) {
        double boxWidth = 100;
        double boxHeight = 100;
        double boxDepth = 100;
        double tetrahedronSide = 100;
        double tetrahedronHeight = 100;
        for (int i = 0; i < args.length; i++) {
            if ((args[i].charAt(0) != '-') || (args.length - 1 <= i)) {
                continue;
            }
            try {
                switch (args[i].charAt(1)) {
                    case 'a' -> boxWidth = Double.parseDouble(args[++i]);
                    case 'b' -> boxHeight = Double.parseDouble(args[++i]);
                    case 'c' -> boxDepth = Double.parseDouble(args[++i]);
                    case 'h' ->
                            tetrahedronHeight = Double.parseDouble(args[++i]);
                    case 'd' -> tetrahedronSide = Double.parseDouble(args[++i]);
                    case 'r' -> tetrahedronSide = Double.parseDouble(args[++i])
                            * Math.sqrt(3);
                    default -> System.out.println(args[i]
                            + ": command not found");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Error. A float number expected, but \""
                                    + args[i] + "\" given.");
            }
        }
        return createModel(boxWidth, boxHeight, boxDepth,
                tetrahedronHeight, tetrahedronSide);
    }

    private static int createModel(double a, double b, double c,
                                   double h, double d) {
        try {
            model = new Model(a, b, c, h, d);
        } catch (WrongCountException exception) {
            System.out.println(exception.getMessage());
            return 1;
        }
        return 0;
    }

    private static void runMainLoop() {
        int requiredFps = 60;
        int updatesPerSecond = 120;
        int ticksCount = 0;
        int framesCount = 0;
        long newSecondStart = System.nanoTime();
        long lastTime = newSecondStart;
        long currentTime;
        long deltaFps = 0;
        long deltaUps = 0;
        long oneSecond = 1000000000;
        while (true) {
            if (deltaUps >= oneSecond / updatesPerSecond) {
                model.update();
                ticksCount++;
                deltaUps = 0;
            }
            currentTime = System.nanoTime();
            deltaUps += currentTime - lastTime;
            deltaFps += currentTime - lastTime;
            lastTime = currentTime;
            if ((deltaFps >= oneSecond / requiredFps)) {
                window.repaint();
                deltaFps = 0;
                framesCount++;
            }
            if (System.nanoTime() - newSecondStart >= oneSecond) {
                System.out.printf("FPS: %d Ticks: %d\n",
                        framesCount, ticksCount);
                framesCount = 0;
                ticksCount = 0;
                newSecondStart = System.nanoTime();
            }
        }
    }

    public static Model getModel() {
        return model;
    }

    public static Window getWindow() {
        return window;
    }
}
