package Famacy.view;

import Famacy.service.AccountService;
import Famacy.PharmacyMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JTextField employeeIdField;
    private JButton registerButton;
    private JButton backButton;
    private JPanel registrationPanel;

    private String currentUsername;  // Store the current logged-in username

    private static final String[] ROLES = {"admin", "user"}; // Define available roles

    public RegistrationForm(String currentUsername) {
        this.currentUsername = currentUsername;

        setTitle("Register Account");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        registrationPanel = new JPanel();
        add(registrationPanel);
        placeComponents(registrationPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String username = usernameField.getText();
                String password = "123";
                String role = (String) roleComboBox.getSelectedItem(); // Get selected role
                int employeeId;
                
                 try {
                    employeeId = Integer.parseInt(employeeIdField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Employee ID.");
                    return;
                }

                if (accountService.registerAccount(username, password, role, employeeId)) {
                    JOptionPane.showMessageDialog(null, "Account registered successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to main menu
                dispose();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 20, 165, 25);
        panel.add(usernameField);

        // JLabel passwordLabel = new JLabel("Password:");
        // passwordLabel.setBounds(10, 50, 80, 25);
        // panel.add(passwordLabel);

        // passwordField = new JPasswordField(20);
        // passwordField.setBounds(150, 50, 165, 25);
        // panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 50, 80, 25);
        panel.add(roleLabel);

        roleComboBox = new JComboBox<>(ROLES);
        roleComboBox.setBounds(150, 50, 165, 25);
        panel.add(roleComboBox);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(10, 80, 100, 25);
        panel.add(employeeIdLabel);

        employeeIdField = new JTextField(20);
        employeeIdField.setBounds(150, 80, 165, 25);
        panel.add(employeeIdField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 110, 150, 25);
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 110, 100, 25);
        panel.add(backButton);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            RegistrationForm registrationForm = new RegistrationForm("admin"); // Example usage
//            registrationForm.setVisible(true);
//        });
//    }
}
