package coursework.frame.menus.menuBar;

import coursework.Main;
import coursework.frame.ParametersDialog;

import javax.swing.*;

public class ModelMenu extends JMenu {
    private JMenuItem parametersItem;
    private JDialog parametersDialog;

    public ModelMenu() {
        super("Model");
        parametersItem = new JMenuItem("Set parameters");
        addListeners();
        add(parametersItem);
    }

    private void addListeners() {
        addParametersListener();
    }

    private void addParametersListener() {
        parametersItem.addActionListener((e) -> {
            Main.getParametersDialog().setVisible(true);
        });
    }
}
