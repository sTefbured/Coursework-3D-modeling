package coursework.frame.menus.menuBar.menus;

import coursework.frame.dialogs.AboutDialog;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ApplicationMenu extends JMenu {
    private final JMenuItem aboutItem;
    private final JMenuItem exitItem;
    private JDialog aboutDialog;

    public ApplicationMenu() {
        super("Application");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");
        setMnemonic(KeyEvent.VK_A);
        aboutItem.setMnemonic(KeyEvent.VK_B);
        exitItem.setMnemonic(KeyEvent.VK_X);
        addListeners();
        add(aboutItem);
        add(exitItem);
    }

    private void addListeners() {
        addAboutListener();
        addExitListener();
    }

    private void addAboutListener() {
        aboutItem.addActionListener((e) -> {
            if (aboutDialog == null) {
                aboutDialog = new AboutDialog();
            }
            aboutDialog.setVisible(true);
        });
    }

    private void addExitListener() {
        exitItem.addActionListener((e) -> System.exit(0));
    }
}
