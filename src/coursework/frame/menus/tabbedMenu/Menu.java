package coursework.frame.menus.tabbedMenu;

import coursework.frame.menus.tabbedMenu.panels.BasicTransformationsPanel;
import coursework.frame.menus.tabbedMenu.panels.ProjectionsPanel;

import javax.swing.*;

public class Menu extends JTabbedPane {
    private JPanel basicPanel;

    public Menu() {
        addTab("Basic", new BasicTransformationsPanel());
        addTab("Projections", new ProjectionsPanel());
    }
}
