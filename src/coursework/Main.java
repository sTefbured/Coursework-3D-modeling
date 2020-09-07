package coursework;

import coursework.exceptions.WrongCountException;

public class Main {
    private static Model model;
    private static Window window;

    public static void main(String[] args) {
        if (initializeModel(args) != 0) {
            return;
        }
        window = new Window("3d shape");
        window.paint(window.getGraphics());
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

    public static Model getModel() {
        return model;
    }

    public static Window getWindow() {
        return window;
    }
}
