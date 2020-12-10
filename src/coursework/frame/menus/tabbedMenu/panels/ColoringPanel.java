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
            } else {
                deleteInvisibleToggleButton.setText("Off");
                performActions(coloringToggleButton);
            }
            Main.getModel().switchDeleteInvisibleMode();
            Main.getWindow().repaint();
        });
    }

    private void addColoringButtonListener() {
        coloringToggleButton.addActionListener((e) -> {
            if (!deleteInvisibleToggleButton.isSelected()) {
                coloringToggleButton.setSelected(false);
                coloringToggleButton.setText("Off");
                performActions(lightToggleButton);
                return;
            }
            if (coloringToggleButton.getText().equals("Off")) {
                coloringToggleButton.setText("On");
            } else {
                coloringToggleButton.setText("Off");
                performActions(lightToggleButton);
            }
            Main.getModel().switchColoringMode();
            Main.getWindow().repaint();
        });
    }

    private void addLightButtonListener() {
        lightToggleButton.addActionListener((e) -> {
            if (!coloringToggleButton.isSelected()) {
                lightToggleButton.setSelected(false);
                lightToggleButton.setText("Off");
                return;
            }
            if (lightToggleButton.getText().equals("Off")) {
                lightToggleButton.setText("On");
            } else {
                lightToggleButton.setText("Off");
            }
            Main.getModel().switchLightMode();
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
