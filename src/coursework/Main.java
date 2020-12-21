package coursework;

import coursework.frame.dialogs.ParametersDialog;
import coursework.frame.Window;
import coursework.model.Model;

import javax.swing.*;

public class Main {
    private static Model model;
    private static Window window;
    private static JDialog parametersDialog;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            window = new Window("3d модель");
            parametersDialog = new ParametersDialog(window);
            parametersDialog.setVisible(true);
        });
    }

    public static void createModel(double[] parameters, boolean isRadiusGiven) {
        if (isRadiusGiven) {
            parameters[4] = parameters[5] * Math.sqrt(3);
        }
        model = new Model(parameters[0], parameters[1], parameters[2],
                parameters[3], parameters[4]);
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
