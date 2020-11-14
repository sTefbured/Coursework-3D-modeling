package coursework.frame.menus.utils;

import javax.swing.*;

public abstract class FieldParser {
    public static double[] parseFieldsArray(JTextField[] fields,
                                            double[] defaultValues) {
        double[] outValues = new double[fields.length];
        for (int i = 0; i < outValues.length; i++) {
            outValues[i] = parseField(fields[i], defaultValues[i]);
        }
        return outValues;
    }

    public static double[] parseFieldsArray(JTextField[] fields,
                                            double defaultValue) {
        double[] outValues = new double[fields.length];
        for (int i = 0; i < fields.length; i++) {
            outValues[i] = parseField(fields[i], defaultValue);
        }
        return outValues;
    }

    public static double parseField(JTextField field, double defaultValue) {
        double outValue;
        try {
            outValue = Double.parseDouble(field.getText());
        } catch (NumberFormatException exception) {
            outValue = defaultValue;
        }
        return outValue;
    }
}
