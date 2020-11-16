package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;
import coursework.geometry.parts.Projections;
import coursework.geometry.utils.Transformations;

import javax.swing.*;

public class ProjectionsPanel extends MenuPagePanel
        implements Projections {
    private JTextField[] axonometryFields;
    private JTextField[] obliqueFields;
    private JTextField[] perspectiveFields;
    private JToggleButton[] projectionsButtons;
    private ButtonGroup projectionsButtonsGroup;

    private static ProjectionsPanel instance;

    //TODO: finish isometric projection
    public ProjectionsPanel() {
        super();
        initializeButtons();
        initializeButtonGroup();
        initializeAxonometryFields();
        initializeObliqueFields();
        initializePerspectiveFields();

        addObliqueFieldsListener();
        addSections();
        instance = this;
    }

    private void initializeButtons() {
        projectionsButtons = new JToggleButton[]{
                new JToggleButton("Front", true),
                new JToggleButton("Up", false),
                new JToggleButton("Side", false),
                new JToggleButton("Axonometry", false),
                new JToggleButton("Oblique", false),
                new JToggleButton("Perspective", false),
        };
        projectionsButtons[FRONT_PROJECTION].setEnabled(true);
        for (JToggleButton projectionButton : projectionsButtons) {
            addProjectionButtonListener(projectionButton);
        }
    }

    private void addProjectionButtonListener(JToggleButton projectionButton) {
        projectionButton.addActionListener(e -> {
            JToggleButton button = (JToggleButton) e.getSource();
            for (int i = 0; i < projectionsButtons.length; i++) {
                if (button.equals(projectionsButtons[i])) {
                    Main.getModel().setCurrentProjection(i);
                    Main.getWindow().repaint();
                    break;
                }
            }
        });
    }

    private void initializeAxonometryFields() {
        axonometryFields = new JTextField[]{
                new JTextField(8),
                new JTextField(8)
        };
        for (JTextField field : axonometryFields) {
            field.setText("30");
        }
    }

    private void initializeObliqueFields() {
        obliqueFields = new JTextField[]{
                new JTextField(8),
                new JTextField(8)
        };
        for (JTextField field : obliqueFields) {
            field.setText("30");
        }
    }

    private void initializePerspectiveFields() {
        perspectiveFields = new JTextField[]{
                new JTextField(8),
                new JTextField(8),
                new JTextField(8),
                new JTextField(8)
        };
        for (JTextField field : perspectiveFields) {
            field.setText("30");
        }
    }

    private void initializeButtonGroup() {
        projectionsButtonsGroup = new ButtonGroup();
        for (JToggleButton button : projectionsButtons) {
            projectionsButtonsGroup.add(button);
        }
    }

    private void addSections() {
        addOrthographicSection();
        addAxonometricSection();
        addObliqueSection();
        addPerspectiveSection();
    }

    private void addOrthographicSection() {
        JLabel title = new JLabel("Orthographic projections");
        JComponent[][] components = new JComponent[][]{
                {projectionsButtons[FRONT_PROJECTION], null},
                {projectionsButtons[UP_PROJECTION], null},
                {projectionsButtons[SIDE_PROJECTION], null}
        };
        addSection(title, components);
    }

    private void addAxonometricSection() {
        JLabel title = new JLabel("Axonometry");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("\u03C6"), axonometryFields[0]},
                {new JLabel("\u03C8"), axonometryFields[1]}
        };
        addSection(title, components);
        addCenteredSection(null, projectionsButtons[AXONOMETRIC_PROJECTION]);
    }

    private void addObliqueSection() {
        JLabel title = new JLabel("Isometry");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("l"), obliqueFields[0]},
                {new JLabel("\u03B1"), obliqueFields[1]}
        };
        addSection(title, components);
        addCenteredSection(null, projectionsButtons[OBLIQUE_PROJECTION]);
    }

    private void addPerspectiveSection() {
        JLabel title = new JLabel("Perspective");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("d"), perspectiveFields[0]},
                {new JLabel("\u03C1"), perspectiveFields[1]},
        };
        JComponent[][] components1 = new JComponent[][] {
                {new JLabel("\u03C6"), perspectiveFields[2]},
                {new JLabel("\u03B8"), perspectiveFields[3]}
        };
        addSection(title, components);
        addSection(null, components1);
        addCenteredSection(null, projectionsButtons[PERSPECTIVE_PROJECTION]);
    }

    private void addObliqueFieldsListener() {
        obliqueFields[0].addActionListener(e -> {
            JTextField field = (JTextField) (e.getSource());
            double length = FieldParser.parseField(field, 10);
            Transformations.setObliqueLength(length);
        });
        obliqueFields[1].addActionListener(e -> {
            JTextField field = (JTextField) (e.getSource());
            double angle = FieldParser.parseField(field, 10);
            Transformations.setObliqueAngle(angle);
        });
    }

    //TODO: add filling fields by default values
    @Override
    public void returnToInitialValues() {
        projectionsButtons[FRONT_PROJECTION].setSelected(true);
    }

    public static double[] getAxonometricValues() {
        return FieldParser.parseFieldsArray(instance.axonometryFields, 0);
    }
}
