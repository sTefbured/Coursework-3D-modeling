package coursework.frame.menus.menuBar.menus;

import coursework.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ModelMenu extends JMenu {
    private final JMenuItem parametersItem;

    public ModelMenu() {
        super("Model");
        parametersItem = new JMenuItem("Set parameters");
        setMnemonic(KeyEvent.VK_M);
        parametersItem.setMnemonic(KeyEvent.VK_P);
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
