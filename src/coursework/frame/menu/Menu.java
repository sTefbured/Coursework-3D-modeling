package coursework.frame.menu;

import coursework.frame.menu.panels.BasicTransformationsPanel;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Menu extends JTabbedPane {
    private JPanel basicPanel;

    public Menu() {
        addTab("Basic", new BasicTransformationsPanel());
        addTab("Not Basic, but basic", new BasicTransformationsPanel());
        addTab("Basic", new BasicTransformationsPanel());
        addTab("Not Basic, but basic", new BasicTransformationsPanel());
    }
}
