package coursework.frame.menu.panels;

import coursework.Main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.text.TabSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BasicTransformationsPanel extends JPanel {
    private final Font font;

    private final JPanel transitionFieldsPanel;
    private final JPanel rotationFieldsPanel;
    private final JPanel scaleFieldsPanel;

    private final JButton paintButton;

    private JTextField[] transitionFields;
    private JTextField[] rotationFields;
    private JTextField[] scaleFields;

    private JLabel[] transitionLabels;
    private JLabel[] rotationLabels;
    private JLabel[] scaleLabels;

    private JLabel mainTransitionLabel;
    private JLabel mainRotationLabel;
    private JLabel mainScaleLabel;

    public BasicTransformationsPanel() {
        super();
        font = new Font(Font.MONOSPACED, Font.BOLD, 25);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        rotationFieldsPanel = new JPanel(new GridBagLayout());
        transitionFieldsPanel = new JPanel(new GridBagLayout());
        scaleFieldsPanel = new JPanel(new GridBagLayout());
        initializeLabels();
        initializeFields();
        JPanel panel = new JPanel();
        paintButton = new JButton("Redraw");
        paintButton.setPreferredSize(new Dimension(200, 100));
        addButtonListener();

        setComponentsFont();
        panel.add(paintButton);

        addTransitionComponents();
        addRotationComponents();
        addScaleComponents();
        add(mainTransitionLabel);
        add(transitionFieldsPanel);
        add(mainRotationLabel);
        add(rotationFieldsPanel);
        add(mainScaleLabel);
        add(scaleFieldsPanel);
        add(panel);
        setVisible(true);
    }

    private void initializeLabels() {
        mainTransitionLabel = new JLabel("Transit: ");
        transitionLabels = new JLabel[]{
                new JLabel("dX", SwingConstants.CENTER),
                new JLabel("dY", SwingConstants.CENTER),
                new JLabel("dZ", SwingConstants.CENTER)
        };

        mainRotationLabel = new JLabel("Rotate around:");
        rotationLabels = new JLabel[]{
                new JLabel("X", SwingConstants.CENTER),
                new JLabel("Y", SwingConstants.CENTER),
                new JLabel("Z", SwingConstants.CENTER)
        };

        mainScaleLabel = new JLabel("Scale:");
        scaleLabels = new JLabel[]{
                new JLabel("X", SwingConstants.CENTER),
                new JLabel("Y", SwingConstants.CENTER),
                new JLabel("Z", SwingConstants.CENTER)
        };
    }

    private void initializeFields() {
        transitionFields = new JTextField[]{
                new JTextField(5),
                new JTextField(5),
                new JTextField(5)
        };

        for (JTextField field : transitionFields) {
            field.setPreferredSize(new Dimension(100, 50));
        }

        rotationFields = new JTextField[]{
                new JTextField(5),
                new JTextField(5),
                new JTextField(5)
        };

        for (JTextField field : rotationFields) {
            field.setPreferredSize(new Dimension(100, 50));
        }

        scaleFields = new JTextField[]{
                new JTextField(5),
                new JTextField(5),
                new JTextField(5)
        };

        for (JTextField field : scaleFields) {
            field.setPreferredSize(new Dimension(100, 50));
        }
    }

    private void addTransitionComponents() {
        for (int i = 0; i < transitionLabels.length; i++) {
            JPanel panel = new JPanel();
            panel.add(transitionLabels[i]);
            panel.add(transitionFields[i]);
            transitionFieldsPanel.add(transitionLabels[i]);
            transitionFieldsPanel.add(transitionFields[i]);
        }
    }

    private void addRotationComponents() {
        for (int i = 0; i < rotationLabels.length; i++) {
            JPanel panel = new JPanel();
            panel.add(rotationLabels[i]);
            panel.add(rotationFields[i]);
            rotationFieldsPanel.add(panel);
        }
    }

    private void addScaleComponents() {
        for (int i = 0; i < scaleLabels.length; i++) {
            JPanel panel = new JPanel();
            panel.add(scaleLabels[i]);
            panel.add(scaleFields[i]);
            scaleFieldsPanel.add(panel);
        }
    }

    //TODO: work with scaling
    private void addButtonListener() {
        paintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] deltas = new int[transitionFields.length];
                double[] angles = new double[rotationFields.length];
                double[] scales = new double[scaleFields.length];
                for (int i = 0; i < deltas.length; i++) {
                    try {
                        deltas[i] = Integer.parseInt(transitionFields[i].getText());
                    } catch (NumberFormatException exception) {
                        deltas[i] = 0;
                    }
                }
                for (int i = 0; i < angles.length; i++) {
                    try {
                        angles[i] = Double.parseDouble(rotationFields[i].getText());
                    } catch (NumberFormatException exception) {
                        angles[i] = 0;
                    }
                }
                for (int i = 0; i < scales.length; i++) {
                    try {
                        scales[i] = Double.parseDouble(scaleFields[i].getText());
                    } catch (NumberFormatException exception) {
                        scales[i] = 1;
                    }
                }
                Main.getModel().transit(deltas[0], deltas[1], deltas[2]);
                Main.getModel().rotate(angles[0], angles[1], angles[2]);
                Main.getModel().scale(scales[0], scales[1], scales[2]);
            }
        });
    }

    private void addFieldListener(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void setFont(JComponent... components) {
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    private void setComponentsFont() {
        setFont(transitionFields);
        setFont(rotationFields);
        setFont(scaleFields);
        setFont(transitionLabels);
        setFont(rotationLabels);
        setFont(scaleLabels);
        setFont(mainRotationLabel,
                mainTransitionLabel,
                mainScaleLabel,
                paintButton);
    }
}
