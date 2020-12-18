package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ColoringPanel extends MenuPagePanel {
    private final JToggleButton deleteInvisibleToggleButton;
    private final JToggleButton coloringToggleButton;
    private final JToggleButton lightToggleButton;

    private JTextField[] lightCoordinatesFields;
    private JButton lightPointButton;

    public ColoringPanel() {
        super();
        deleteInvisibleToggleButton = new JToggleButton("Off");
        coloringToggleButton = new JToggleButton("Off");
        lightToggleButton = new JToggleButton("Off");
        lightCoordinatesFields = new JTextField[] {
                new JTextField(8),
                new JTextField(8),
                new JTextField(8)
        };
        lightPointButton = new JButton("Set light point");
        addListeners();
        addSections();
    }

    private void addListeners() {
        addDeleteInvisibleButtonListener();
        addColoringButtonListener();
        addLightButtonListener();
        addLightPointButton();
    }

    private void addDeleteInvisibleButtonListener() {
        deleteInvisibleToggleButton.addActionListener((e) -> {
            if (deleteInvisibleToggleButton.getText().equals("Off")) {
                deleteInvisibleToggleButton.setText("On");
                Main.getModel().setDeleteInvisibleMode(true);
            } else {
                deleteInvisibleToggleButton.setText("Off");
                Main.getModel().setDeleteInvisibleMode(false);
                performActions(coloringToggleButton);
            }
            Main.getWindow().repaint();
        });
    }

    private void addColoringButtonListener() {
        coloringToggleButton.addActionListener((e) -> {
            if (!deleteInvisibleToggleButton.isSelected()) {
                coloringToggleButton.setSelected(false);
                coloringToggleButton.setText("Off");
                Main.getModel().setColoringMode(false);
                performActions(lightToggleButton);
                return;
            }
            if (coloringToggleButton.getText().equals("Off")) {
                coloringToggleButton.setText("On");
                Main.getModel().setColoringMode(true);
            } else {
                coloringToggleButton.setText("Off");
                performActions(lightToggleButton);
                Main.getModel().setColoringMode(false);
            }
            Main.getWindow().repaint();
        });
    }

    private void addLightButtonListener() {
        lightToggleButton.addActionListener((e) -> {
            if (!coloringToggleButton.isSelected()) {
                lightToggleButton.setSelected(false);
                lightToggleButton.setText("Off");
                Main.getModel().setLightMode(false);
                return;
            }
            if (lightToggleButton.getText().equals("Off")) {
                lightToggleButton.setText("On");
                Main.getModel().setLightMode(true);
            } else {
                lightToggleButton.setText("Off");
                Main.getModel().setLightMode(false);
            }
            Main.getWindow().repaint();
        });
    }

    private void addLightPointButton() {
        lightPointButton.addActionListener((e) -> {
            double x = FieldParser.parseField(lightCoordinatesFields[0], 0);
            double y = FieldParser.parseField(lightCoordinatesFields[1], 0);
            double z = FieldParser.parseField(lightCoordinatesFields[2], 0);
            Main.getModel().setLightPoint(x, y, z);
            Main.getWindow().repaint();
        });
    }

    private void performActions(JComponent component) {
        ActionListener[] listeners = component
                .getListeners(ActionListener.class);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(component,
                    ActionEvent.ACTION_PERFORMED,
                    "Programmatically created."));
        }
    }

    private void addSections() {
        JComponent[][] components = new JComponent[][]{
                {new JLabel("Delete invisible lines"), deleteInvisibleToggleButton},
                {new JLabel("Coloring"), coloringToggleButton},
                {new JLabel("Light"), lightToggleButton}
        };
        JComponent[][] components1 = new JComponent[][]{
                {new JLabel("X"), lightCoordinatesFields[0]},
                {new JLabel("Y"), lightCoordinatesFields[1]},
                {new JLabel("Z"), lightCoordinatesFields[2]}
        };
        for (JComponent[] component : components) {
            addCenteredSection((JLabel) component[0], component[1]);
        }
        addSection(new JLabel("Light point coordinates"), components1);
        addCenteredSection(null, lightPointButton);
    }

    @Override
    public void returnToInitialValues() {
        //TODO: fill that
    }
}
