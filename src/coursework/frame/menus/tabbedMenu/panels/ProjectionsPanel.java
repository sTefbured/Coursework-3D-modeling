package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;
import coursework.frame.Window;
import coursework.geometry.parts.Projections;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectionsPanel extends MenuPagePanel
        implements Projections {
    private JTextField[] axonometryFields;
    private JTextField[] isometryFields;
    private JTextField[] perspectiveFields;
    private JToggleButton[] projectionsButtons;
    private ButtonGroup projectionsButtonsGroup;

    //TODO: finish isometric projection
    public ProjectionsPanel() {
        super();
        initializeButtons();
        initializeButtonGroup();
        initializeAxonometryFields();
        initializeIsometryFields();
        initializePerspectiveFields();

        addSections();
    }

    private void initializeButtons() {
        projectionsButtons = new JToggleButton[]{
                new JToggleButton("Front", true),
                new JToggleButton("Up", false),
                new JToggleButton("Side", false),
                new JToggleButton("Axonometry", false),
                new JToggleButton("Isometry", false),
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
            for (int j = 0; j < projectionsButtons.length; j++) {
                if (button.equals(projectionsButtons[j])) {
                    Main.getModel().setCurrentProjection(j);
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

    private void initializeIsometryFields() {
        isometryFields = new JTextField[]{
                new JTextField(8),
                new JTextField(8)
        };
        for (JTextField field : isometryFields) {
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
        addIsometricSection();
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

    private void addIsometricSection() {
        JLabel title = new JLabel("Isometry");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("l"), isometryFields[0]},
                {new JLabel("\u03B1"), isometryFields[1]}
        };
        addSection(title, components);
        addCenteredSection(null, projectionsButtons[ISOMETRIC_PROJECTION]);
    }

    private void addPerspectiveSection() {
        JLabel title = new JLabel("Perspective");
        JComponent[][] components = new JComponent[][]{
                {new JLabel("l"), perspectiveFields[0]},
                {new JLabel("\u03B1"), perspectiveFields[1]},
                {new JLabel("\u03B1"), perspectiveFields[2]},
                {new JLabel("\u03B1"), perspectiveFields[3]},
//                {projectionsButtons[PERSPECTIVE_PROJECTION], null}
        };
        addSection(title, components);
    }
}
