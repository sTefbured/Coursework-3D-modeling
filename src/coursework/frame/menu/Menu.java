package coursework.frame.menu;

import coursework.frame.menu.panels.BasicTransformationsPanel;
import coursework.frame.menu.panels.ProjectionsPanel;

import javax.swing.*;

public class Menu extends JTabbedPane {
    private JPanel basicPanel;

    public Menu() {
        addTab("Basic", new BasicTransformationsPanel());
        addTab("Projections", new ProjectionsPanel());
    }
}
