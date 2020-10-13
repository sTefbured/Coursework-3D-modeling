package coursework.frame.menu.panels;

import coursework.Main;

import javax.swing.*;
import javax.swing.text.TabSet;
import java.awt.*;

public class BasicTransformationsPanel extends JPanel {
    private JLabel mainRotationLabel;
    private JLabel rotationLabelX;
    private JLabel rotationLabelY;
    private JLabel rotationLabelZ;
    private JTextField rotationFieldX;
    private JTextField rotationFieldY;
    private JTextField rotationFieldZ;
    private final JButton paintButton;
    private JPanel fieldsPanel;

    public BasicTransformationsPanel() {
        super();
        setLayout(new GridLayout(3, 0, 0, 0));
        fieldsPanel = new JPanel(new GridLayout(3, 2, 0, 0));
        initializeLabels();
        initializeFields();
        paintButton = new JButton("Redraw");
        paintButton.addActionListener(e -> Main.getModel().rotate(
                            Double.parseDouble(rotationFieldX.getText()),
                            Double.parseDouble(rotationFieldY.getText()),
                            Double.parseDouble(rotationFieldZ.getText())));
        addComponents();
        add(mainRotationLabel);
        add(fieldsPanel);
        add(paintButton);
        setVisible(true);
    }

    private void initializeLabels() {
        mainRotationLabel = new JLabel("Rotate around:");
        rotationLabelX = new JLabel("X");
        rotationLabelY = new JLabel("Y");
        rotationLabelZ = new JLabel("Z");
    }

    private void initializeFields() {
        rotationFieldX = new JTextField();
        rotationFieldY = new JTextField();
        rotationFieldZ = new JTextField();
    }

    private void addComponents() {
        fieldsPanel.add(rotationLabelX);
        fieldsPanel.add(rotationFieldX);
        fieldsPanel.add(rotationLabelY);
        fieldsPanel.add(rotationFieldY);
        fieldsPanel.add(rotationLabelZ);
        fieldsPanel.add(rotationFieldZ);
        fieldsPanel.add(paintButton);
    }
}
