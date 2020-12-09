package coursework.frame.menus.tabbedMenu.panels;

import javax.swing.*;

public class ColoringPanel extends  MenuPagePanel {
    private JToggleButton coloringToggleButton;

    public ColoringPanel() {
        super();
        coloringToggleButton = new JToggleButton("Fill with color");
        addSections();
    }

    private void addSections() {
        JComponent[][] components = new JComponent[][] {
                {coloringToggleButton, null}
        };
        addSection(null, components);
    }

    @Override
    public void returnToInitialValues() {

    }
}
