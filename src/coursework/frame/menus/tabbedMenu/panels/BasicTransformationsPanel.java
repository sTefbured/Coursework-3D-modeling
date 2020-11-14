package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;
import coursework.geometry.parts.Projections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//FIXME: hard-coded values
public class BasicTransformationsPanel extends MenuPagePanel
        implements Projections {
    private final JTextField[] transitionFields;
    private final JTextField[] rotationFields;
    private final JTextField[] scalingFields;
    private final JRadioButton[] projectionsButtons;
    private final ButtonGroup projectionsButtonsGroup;

    public BasicTransformationsPanel() {
        super();
        transitionFields = createFieldsArray();
        rotationFields = createFieldsArray();
        scalingFields = createFieldsArray();
        projectionsButtons = new JRadioButton[]{
                new JRadioButton("XOY", true),
                new JRadioButton("XOZ", false),
                new JRadioButton("ZOY", false)
        };
        projectionsButtons[0].setEnabled(true);
        projectionsButtonsGroup = new ButtonGroup();
        for (JRadioButton button : projectionsButtons) {
            projectionsButtonsGroup.add(button);
        }
        addButtonListener();
        button.setPreferredSize(new Dimension(300, 100));
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
        addOrthometricProjectionsSection();
        addCenteredSection(null, button);
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

    private void addOrthometricProjectionsSection() {
        JLabel title = new JLabel("Orthographic projections");
        JPanel panel = new JPanel(new GridLayout(1, 5, 70, 0));
        for (JRadioButton projectionsButton : projectionsButtons) {
            projectionsButton.setFont(font);
            panel.add(projectionsButton);
        }
        addCenteredSection(title, panel);
    }

    private void addButtonListener() {
        button.addActionListener(e -> {
            double[] deltas = FieldParser.parseFieldsArray(transitionFields, 0);
            double[] angles = FieldParser.parseFieldsArray(rotationFields, 0);
            double[] scales = FieldParser.parseFieldsArray(scalingFields, 0);
            Main.getModel().transit(deltas[0], deltas[1], deltas[2]);
            Main.getModel().rotate(angles[0], angles[1], angles[2]);
            Main.getModel().scale(scales[0], scales[1], scales[2]);
            Main.getModel().setCurrentProjection(getProjectionIndex());
            Main.getWindow().repaint();
        });
    }

    private int getProjectionIndex() {
        for (int i = 0; i < projectionsButtons.length; i++) {
            ButtonModel buttonModel = projectionsButtons[i].getModel();
            if (projectionsButtonsGroup.isSelected(buttonModel)) {
                return i;
            }
        }
        return XOY_PROJECTION;
    }
}
