import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SmartCalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;  // Display area for showing numbers and results
    private StringBuilder input; // To store input as the user types numbers
    private double result;       // Store the current result of calculations
    private char operator;       // Store the operator for arithmetic operations

    public SmartCalculatorGUI() {
        // Setup JFrame properties
        setTitle("Smart Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize display area
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Input string for storing user input
        input = new StringBuilder();

        // Create a panel for the calculator buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Button texts for digits, operators, and special commands
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Create and add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this); // Assign action listener to each button
            buttonPanel.add(button);
        }

        // Add the button panel to the JFrame
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true); // Make the frame visible
    }

    // Handle button clicks (ActionEvent triggered)
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the text of the clicked button

        // If the button is a digit (0-9), append it to the input
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            input.append(command);
            display.setText(input.toString()); // Show the current input in the display
        }
        // If the 'C' button (clear) is pressed, reset input and display
        else if (command.equals("C")) {
            input.setLength(0); // Clear the input
            display.setText(""); // Clear the display
            result = 0;
        }
        // If the '=' button (equals) is pressed, calculate the result
        else if (command.equals("=")) {
            calculate(); // Perform the calculation
        }
        // If an operator button (+, -, *, /) is pressed, store the operator and first number
        else {
            operator = command.charAt(0); // Store the operator
            result = Double.parseDouble(input.toString()); // Store the first operand
            input.setLength(0); // Clear input for the second operand
        }
    }

    // Method to perform the calculation based on the stored operator
    private void calculate() {
        double secondOperand = Double.parseDouble(input.toString());

        switch (operator) {
            case '+':
                result += secondOperand;
                break;
            case '-':
                result -= secondOperand;
                break;
            case '*':
                result *= secondOperand;
                break;
            case '/':
                if (secondOperand == 0) {
                    JOptionPane.showMessageDialog(this, "Error: Cannot divide by zero");
                    return;
                }
                result /= secondOperand;
                break;
        }

        // Display the result and reset the input
        display.setText(String.valueOf(result));
        input.setLength(0); // Clear input for next calculation
    }

    public static void main(String[] args) {
        // Create an instance of the calculator GUI
        new SmartCalculatorGUI();
    }
}
