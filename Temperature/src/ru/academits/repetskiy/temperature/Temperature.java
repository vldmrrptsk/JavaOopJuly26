package ru.academits.repetskiy.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temperature {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = createFrame();
                frame.setVisible(true);
            }
        });
    }

    private static double convertFahrenheitToCelsius(double initialTemperature) {
        return Math.round((initialTemperature - 32.0) * 0.555556);
    }

    private static double convertCelsiusToFahrenheit(double initialTemperature) {
        return Math.round((initialTemperature * 1.8) + 32.0);
    }

    private static double convertFahrenheitToKelvin(double initialTemperature) {
        return Math.round((initialTemperature + 459.67) * 0.555556);
    }

    private static double convertKelvinToFahrenheit(double initialTemperature) {
        return Math.round((initialTemperature * 1.8) - 459.67);
    }

    private static double convertCelsiusToKelvin(double initialTemperature) {
        return Math.round(initialTemperature + 273.15);
    }

    private static double convertKelvinToCelsius(double initialTemperature) {
        return Math.round(initialTemperature - 273.15);
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Перевод Температур");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField inputField = createInputField();
        JTextField outputField = createOutputField();

        JRadioButton fromC = new JRadioButton("Цельсий", true);
        JRadioButton fromF = new JRadioButton("Фаренгейт");
        JRadioButton fromK = new JRadioButton("Кельвин");

        JRadioButton toC = new JRadioButton("Цельсий", true);
        JRadioButton toF = new JRadioButton("Фаренгейт");
        JRadioButton toK = new JRadioButton("Кельвин");

        ButtonGroup fromGroup = new ButtonGroup();
        fromGroup.add(fromC);
        fromGroup.add(fromF);
        fromGroup.add(fromK);

        ButtonGroup toGroup = new ButtonGroup();
        toGroup.add(toC);
        toGroup.add(toF);
        toGroup.add(toK);

        JPanel fromPanel = new JPanel(new GridLayout(3, 1));
        fromPanel.setBorder(BorderFactory.createTitledBorder("Из шкалы:"));
        fromPanel.add(fromC);
        fromPanel.add(fromF);
        fromPanel.add(fromK);

        JPanel toPanel = new JPanel(new GridLayout(3, 1));
        toPanel.setBorder(BorderFactory.createTitledBorder("В шкалу:"));
        toPanel.add(toC);
        toPanel.add(toF);
        toPanel.add(toK);

        frame.add(inputField);
        frame.add(outputField);
        frame.add(fromPanel);
        frame.add(toPanel);

        JButton button = new JButton("Конвертировать");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temperature = Double.parseDouble(inputField.getText());

                String from;
                if (fromC.isSelected()) {
                    from = "C";
                } else if (fromF.isSelected()) {
                    from = "F";
                } else {
                    from = "K";
                }

                String to;
                if (toC.isSelected()) {
                    to = "C";
                } else if (toF.isSelected()) {
                    to = "F";
                } else {
                    to = "K";
                }

                if (from.equals(to)) {
                    outputField.setText(String.valueOf(temperature));
                    return;
                }

                double resultTemperature;

                if (from.equals("F") && to.equals("C")) {
                    resultTemperature = convertFahrenheitToCelsius(temperature);
                } else if (from.equals("C") && to.equals("F")) {
                    resultTemperature = convertCelsiusToFahrenheit(temperature);
                } else if (from.equals("F")) {
                    resultTemperature = convertFahrenheitToKelvin(temperature);
                } else if (from.equals("K") && to.equals("F")) {
                    resultTemperature = convertKelvinToFahrenheit(temperature);
                } else if (from.equals("K")) {
                    resultTemperature = convertKelvinToCelsius(temperature);
                } else {
                    resultTemperature = convertCelsiusToKelvin(temperature);
                }

                outputField.setText(String.valueOf(resultTemperature));
            }
        });

        frame.add(button);

        return frame;
    }

    private static JTextField createInputField() {
        return new JTextField(8);
    }

    private static JTextField createOutputField() {
        JTextField field = new JTextField(8);
        field.setEditable(false);
        return field;
    }
}
