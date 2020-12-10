package coursework.frame.menus.tabbedMenu.panels;

import coursework.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColoringPanel extends MenuPagePanel {
    private final JToggleButton deleteInvisibleToggleButton;
    private final JToggleButton coloringToggleButton;
    private final JToggleButton lightToggleButton;

    public ColoringPanel() {
        super();
        deleteInvisibleToggleButton = new JToggleButton("Off");
        coloringToggleButton = new JToggleButton("Off");
        lightToggleButton = new JToggleButton("Off");
        addListeners();
        addSections();
    }

    private void addListeners() {
        addDeleteInvisibleButtonListener();
        addColoringButtonListener();
        addLightButtonListener();
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
        for (JComponent[] component : components) {
            addCenteredSection((JLabel) component[0], component[1]);
        }
    }

    @Override
    public void returnToInitialValues() {
        //TODO: fill that
    }
}
