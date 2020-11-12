package coursework.frame.menu.panels;

import javax.swing.*;
import java.awt.*;

public class ProjectionsPanel extends MenuPagePanel {
    private JTextField[] transitionFields;
    private JTextField[] rotationFields;
    private JTextField[] scalingFields;
    private JButton button;

    public ProjectionsPanel() {
        super();
        transitionFields = new JTextField[] {
                new JTextField(8),
                new JTextField(8),
                new JTextField(8)
        };
        button = new JButton("Redraw");
        button.setPreferredSize(new Dimension(300, 100));
        addSections();
    }

    private void addSections() {
        addTransitionSection();
        addRotationSection();
        addScaleSection();
        addButtonSection(button);
    }

    private void addTransitionSection() {
        JLabel title = new JLabel("Transition");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("dX"), transitionFields[0]},
                {new JLabel("dY"), transitionFields[1]},
                {new JLabel("dZ"), transitionFields[2]}
        };
        addSection(title, components);
    }

    private void addRotationSection() {
        JLabel title = new JLabel("Rotation");
        JComponent[][] components = new JComponent[][] {
                {new JLabel("X"), new JTextField(8)},
                {new JLabel("Y"), new JTextField(8)},
                {new JLabel("Z"), new JTextField(8)}
        };
        addSection(title, components);
    }

    private void addScaleSection() {
        JLabel title = new JLabel("Scaling");
        JComponent[][] components = new JComponent[][] {
                {new JLabel("dX"), new JTextField(8)},
                {new JLabel("dY"), new JTextField(8)},
                {new JLabel("dZ"), new JTextField(8)}
        };
        addSection(title, components);
    }
}
