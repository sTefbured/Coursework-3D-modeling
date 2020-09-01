package Coursework;

import Coursework.Exceptions.WrongCountException;

public class Main {
    private static Model model;
    private static Window window;

// TODO: add radius conversion
    public static void main(String[] args) {
        if (initializeModel(args) != 0) {
            return;
        }
        window = new Window("3d shape");
        window.paint(window.getGraphics());
    }

    private static int initializeModel(String[] args) {
        if (args.length < 5) {
            System.out.println("At least 5 arguments expected.");
            return 2;
        }
        try {
            model = new Model(Double.parseDouble(args[0]),
                    Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]),
                    Double.parseDouble(args[3]),
                    Double.parseDouble(args[4]));
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
