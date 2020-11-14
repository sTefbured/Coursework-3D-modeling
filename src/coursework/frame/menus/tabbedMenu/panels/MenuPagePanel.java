package coursework.frame.menus.tabbedMenu.panels;

import javax.swing.*;
import java.awt.*;

public class MenuPagePanel extends JPanel {
    protected final Font font;
    protected static final JButton button = new JButton("Repaint");

    private int position;
    private int maxWidth;

    public MenuPagePanel() {
        super();
        font = new Font(Font.MONOSPACED, Font.BOLD, 13);
        position = 0;
        maxWidth = 0;
        setLayout(new GridBagLayout());
    }

    public void addCenteredSection(JLabel title, JComponent component) {
        if (title != null) {
            addTitle(title);
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 30, 0);
        constraints.gridx = 0;
        constraints.gridy = position;
        constraints.gridwidth = maxWidth;
        add(component, constraints);
        position++;
    }

    public void addSection(JLabel title, JComponent[][] components) {
        addTitle(title);
        if (maxWidth < components.length * 2) {
            maxWidth = components.length * 2;
        }
        for (int i = 0; i < components.length; i++) {
            GridBagConstraints labelConstraints = getLabelConstraints(2 * i, position);
            components[i][0].setFont(font);
            add(components[i][0], labelConstraints);

            GridBagConstraints fieldConstraints = getFieldConstraints(i * 2 + 1, position);
            components[i][1].setFont(font);
            add(components[i][1], fieldConstraints);
        }
        position++;
    }

    private void addTitle(JLabel title) {
        GridBagConstraints titleConstraints = getTitleConstraints(0, position);
        title.setFont(font);
        add(title, titleConstraints);
        position++;
    }

    private GridBagConstraints getTitleConstraints(int xPos, int yPos) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(0, 0, 10, 0);
        labelConstraints.gridwidth = 6;
        labelConstraints.gridheight = 1;
        labelConstraints.gridx = xPos;
        labelConstraints.gridy = yPos;
        return labelConstraints;
    }

    private GridBagConstraints getLabelConstraints(int xPos, int yPos) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.insets = new Insets(0, 0, 30, 0);
        labelConstraints.gridx = xPos;
        labelConstraints.gridy = yPos;
        labelConstraints.anchor = GridBagConstraints.WEST;
        return labelConstraints;
    }

    private GridBagConstraints getFieldConstraints(int xPos, int yPos) {
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.insets = new Insets(0, 10, 30, 50);
        fieldConstraints.gridx = xPos;
        fieldConstraints.gridy = yPos;
        return fieldConstraints;
    }
}
