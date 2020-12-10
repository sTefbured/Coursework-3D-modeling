package coursework.frame.menus.tabbedMenu;

import coursework.frame.menus.tabbedMenu.panels.*;

import javax.swing.*;

public class Menu extends JTabbedPane {
    private final MenuPagePanel basicPanel;
    private final MenuPagePanel projectionsPanel;
    private final MenuPagePanel coloringPanel;

    public Menu() {
        basicPanel = new BasicTransformationsPanel();
        projectionsPanel = new ProjectionsPanel();
        coloringPanel = new ColoringPanel();

        addTab("Basic", basicPanel);
        addTab("Projections", projectionsPanel);
        addTab("Coloring", coloringPanel);
    }

    public void returnToInitialValues() {
        basicPanel.returnToInitialValues();
        projectionsPanel.returnToInitialValues();
        coloringPanel.returnToInitialValues();
    }
}
