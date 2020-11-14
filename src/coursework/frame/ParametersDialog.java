package coursework.frame;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;

import javax.swing.*;
import java.awt.*;

public class ParametersDialog extends JDialog {
    private final double[] defaultParameters = {150, 190, 250, 300, 350};

    private JTextField[] fields;
    private JLabel[] labels;
    private JButton button;

    public ParametersDialog(JFrame parent) {
        super(parent, "Set parameters", true);
        setLayout(new GridBagLayout());
        initializeFields();
        initializeLabels();
        initializeButton();
        addComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeFields() {
        fields = new JTextField[]{
                new JTextField(10),
                new JTextField(10),
                new JTextField(10),
                new JTextField(10),
                new JTextField(10)
        };
        for (JTextField field : fields) {
            field.setPreferredSize(new Dimension(100, 30));
        }
    }

    private void initializeLabels() {
        labels = new JLabel[]{
                new JLabel("Box width"),
                new JLabel("Box height"),
                new JLabel("Box depth"),
                new JLabel("Tetrahedron's side length"),
                new JLabel("Tetrahedron's height")
        };
    }

    private void initializeButton() {
        button = new JButton("Build");
        button.addActionListener(e -> {
            double[] parameters = FieldParser.parseFieldsArray(fields, defaultParameters);
            setVisible(false);
            Main.createModel(parameters);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Main.runMainLoop();
                }
            });
            thread.start();
        });
    }

    private void addComponents() {
        GridBagConstraints constraints = new GridBagConstraints();
        for (int i = 0; i < fields.length; i++) {
            constraints.anchor = GridBagConstraints.WEST;
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.insets = new Insets(10, 10, 10, 10);
            add(labels[i], constraints);
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.gridx = 1;
            constraints.gridy = i;
            add(fields[i], constraints);
        }
        constraints.gridx = 0;
        constraints.gridy = fields.length;
        constraints.gridwidth = 2;
        add(button, constraints);
    }
}
