package coursework.frame.menu.panels;

import javax.swing.*;
import java.awt.*;

public class MenuPagePanel extends JPanel {
    private final Font font;

    public MenuPagePanel() {
        super();
        font = new Font(Font.MONOSPACED, Font.BOLD, 25);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addButtonSection(JButton button) {
        JPanel section = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        section.add(button, constraints);
        add(section);
    }

    public void addSection(JLabel title, JComponent[][] components) {
        JPanel section = new JPanel(new GridBagLayout());
        setPanelConstraints(section, title, components);
        add(section);
    }

    private void setPanelConstraints(JPanel section,
                                     JLabel title,
                                     JComponent[][] components) {
        GridBagConstraints titleConstraints = getTitleConstraints();
        title.setFont(font);
        section.add(title, titleConstraints);
        for (int i = 0; i < components.length; i++) {
            GridBagConstraints labelConstraints = getLabelConstraints(2 * i);
            components[i][0].setFont(font);
            section.add(components[i][0], labelConstraints);

            GridBagConstraints fieldConstraints = getFieldConstraints(i * 2 + 1);
            components[i][1].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            components[i][1].setPreferredSize(new Dimension(200, 30));
            section.add(components[i][1], fieldConstraints);
        }
    }

    private GridBagConstraints getTitleConstraints() {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(0, 0, 10, 0);
        labelConstraints.gridwidth = 6;
        labelConstraints.gridheight = 1;
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        return labelConstraints;
    }

    private GridBagConstraints getLabelConstraints(int xPos) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = xPos;
        labelConstraints.gridy = 1;
        labelConstraints.gridwidth = 1;
        labelConstraints.anchor = GridBagConstraints.WEST;
        return labelConstraints;
    }

    private GridBagConstraints getFieldConstraints(int xPos) {
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.insets = new Insets(0, 10, 0, 50);
        fieldConstraints.gridx = xPos;
        fieldConstraints.gridy = 1;
        fieldConstraints.gridwidth = 1;
        return fieldConstraints;
    }
}
