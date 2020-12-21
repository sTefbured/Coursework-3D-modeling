package coursework.frame.dialogs;

import coursework.Main;
import coursework.frame.menus.utils.FieldParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParametersDialog extends JDialog {
    private final double[] defaultParameters = {150, 200, 300, 300, 300, 150};

    private JTextField[] fields;
    private JLabel[] labels;
    private JRadioButton[] radioButtons;
    private JButton button;

    public ParametersDialog(JFrame parent) {
        super(parent, "Set parameters", true);
        setLayout(new GridBagLayout());
        initializeFields();
        initializeFieldsLabels();
        initializeRadioButtons();
        initializeButton();
        addComponents();
        addListener();
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
                new JTextField(10),
                new JTextField(10),
        };
        for (JComponent component : fields) {
            component.setPreferredSize(new Dimension(100, 30));
        }
        fields[5].setEditable(false);
    }

    private void initializeFieldsLabels() {
        labels = new JLabel[]{
                new JLabel("Parallelepiped's width"),
                new JLabel("Parallelepiped's height"),
                new JLabel("Parallelepiped's depth"),
                new JLabel("Tetrahedron's height"),
                new JLabel("Tetrahedron's side length"),
                new JLabel("Circumscribed circle's radius"),
        };
    }

    private void initializeRadioButtons() {
        radioButtons = new JRadioButton[] {
                new JRadioButton("Side length input", true),
                new JRadioButton("Radius input", false)
        };
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtons[0]);
        buttonGroup.add(radioButtons[1]);
        radioButtons[0].addActionListener((e -> {
            fields[4].setEditable(true);
            fields[5].setEditable(false);
            fields[5].setText("");
        }));
        radioButtons[1].addActionListener((e -> {
            fields[5].setEditable(true);
            fields[4].setEditable(false);
            fields[4].setText("");
        }));
    }

    private synchronized void initializeButton() {
        button = new JButton("Build");
        button.addActionListener(e -> {
            double[] parameters = FieldParser.parseFieldsArray(fields,
                    defaultParameters);
            setVisible(false);
            Main.createModel(parameters, radioButtons[1].isSelected());
            Main.getWindow().repaint();
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
        addRadioButtons(constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(button, constraints);
    }

    private void addRadioButtons(GridBagConstraints constraints) {
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(radioButtons[0], constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        add(radioButtons[1], constraints);
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (Main.getModel() == null) {
                    Main.createModel(defaultParameters, false);
                    Main.getWindow().repaint();
                }
            }
        });
    }
}
