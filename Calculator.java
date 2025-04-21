import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);

        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.BLACK);
        JLabel displayLabel = new JLabel("0");
        displayLabel.setFont(new Font("Arial", Font.BOLD, 30));
        displayLabel.setBackground(Color.BLACK);
        displayLabel.setForeground(Color.GRAY);
        displayLabel.setOpaque(true);

        displayPanel.add(displayLabel);

        JPanel numberPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        addButtons(numberPanel, displayLabel);

        frame.add(displayPanel, BorderLayout.NORTH);
        frame.add(numberPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static String currentText = "";
    private static double result = 0;
    private static String operator = "";
    private static String expression = "";

    private static void addButtons(JPanel panel, JLabel displayLabel) {
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "AC" , "Close" , 
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.BLACK);
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Toolkit.getDefaultToolkit().beep();
                    String command = button.getText();
                    handleButtonPress(command, displayLabel);
                }
            });

            panel.add(button);
        }
    }

    private static void handleButtonPress(String command, JLabel displayLabel) {
        if (command.equals("C")) {
            currentText = "";
            expression = "";
            displayLabel.setText(currentText);
        } 
        else if (command.equals("AC")) {
            currentText = "";
            result = 0;
            operator = "";
            expression = "";
            displayLabel.setText("0");
        } 
        else if (command.equals("Close")){
            currentText = "" ;
            result = 0 ;
            operator = " "; 
            expression = "";
            System.exit(0); // to exit the program 
        } 
        else if (command.equals("=")) {
            calculateResult();
            displayLabel.setText(String.valueOf(result));
            currentText = String.valueOf(result); // to display the result on the screen
            operator = "";
            expression = "";
        } 
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            if (!currentText.isEmpty()) {
                result = Double.parseDouble(currentText);
            }
            operator = command;
            currentText = "";
            expression += result + " " + command + " "; 
        } 
        else {
            currentText += command;
            expression += command;
            displayLabel.setText(currentText);
        }
    }

    private static void calculateResult() {
        switch (operator) {
            case "+":
                result += Double.parseDouble(currentText);
                break;
            case "-":
                result -= Double.parseDouble(currentText);
                break;
            case "*":
                result *= Double.parseDouble(currentText);
                break;
            case "/":
                if (currentText.equals("0")) {
                    result = 0; // handle division by zero
                } else {
                    result /= Double.parseDouble(currentText);
                }
                break;
            default:
                break;
        }
    }
}
