package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;

import javax.swing.*;
import java.awt.*;

public class BasicTransformationsPanel extends MenuPagePanel {
    private final JTextField[] transitionFields;
    private final JTextField[] rotationFields;
    private final JTextField[] scalingFields;
    private final JButton initialValuesButton;
    private final JButton repaintButton;

    public BasicTransformationsPanel() {
        super();
        transitionFields = createFieldsArray();
        rotationFields = createFieldsArray();
        scalingFields = createFieldsArray();
        initialValuesButton = new JButton("Initial values");
        addInitialValuesButtonListener();
        repaintButton = new JButton("Repaint");
        addRepaintButtonListener();
        repaintButton.setPreferredSize(new Dimension(300, 100));
        addSections();
    }

    private JTextField[] createFieldsArray() {
        JTextField[] fields = new JTextField[]{
                new JTextField(12),
                new JTextField(12),
                new JTextField(12)
        };
        for (JTextField field : fields) {
            field.setPreferredSize(new Dimension(300, 30));
        }
        return fields;
    }

    private void addSections() {
        addTransitionSection();
        addRotationSection();
        addScaleSection();
        addCenteredSection(null, initialValuesButton);
        addCenteredSection(null, repaintButton);
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
        JComponent[][] components = new JComponent[][]{
                {new JLabel("X"), rotationFields[0]},
                {new JLabel("Y"), rotationFields[1]},
                {new JLabel("Z"), rotationFields[2]}
        };
        addSection(title, components);
    }

    private void addScaleSection() {
        JLabel title = new JLabel("Scaling");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("dX"), scalingFields[0]},
                {new JLabel("dY"), scalingFields[1]},
                {new JLabel("dZ"), scalingFields[2]}
        };
        addSection(title, components);
    }

    private void addInitialValuesButtonListener() {
        initialValuesButton.addActionListener(e -> {
            Main.getWindow().returnToInitialValues();
            Main.getWindow().repaint();
        });
    }

    private void addRepaintButtonListener() {
        repaintButton.addActionListener(e -> {
            double[] deltas = FieldParser
                    .parseFieldsArray(transitionFields, 0);
            double[] angles = FieldParser
                    .parseFieldsArray(rotationFields, 0);
            double[] scales = FieldParser
                    .parseFieldsArray(scalingFields, 1);
            Main.getModel().transit(deltas[0], deltas[1], deltas[2]);
            Main.getModel().rotate(angles[0], angles[1], angles[2]);
            Main.getModel().scale(scales[0], scales[1], scales[2]);
            Main.getWindow().repaint();
        });
    }

    //TODO: fill the method
    @Override
    public void returnToInitialValues() {

    }
}
