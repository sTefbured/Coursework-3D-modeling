package coursework.frame.menus.tabbedMenu;

import coursework.frame.menus.tabbedMenu.panels.BasicTransformationsPanel;
import coursework.frame.menus.tabbedMenu.panels.MenuPagePanel;
import coursework.frame.menus.tabbedMenu.panels.ProjectionsPanel;

import javax.swing.*;

public class Menu extends JTabbedPane {
    private final MenuPagePanel basicPanel;
    private final MenuPagePanel projectionsPanel;


    public Menu() {
        basicPanel = new BasicTransformationsPanel();
        projectionsPanel = new ProjectionsPanel();
        addTab("Basic", basicPanel);
        addTab("Projections", projectionsPanel);
    }

    public void returnToInitialValues() {
        basicPanel.returnToInitialValues();
        projectionsPanel.returnToInitialValues();
    }
}
