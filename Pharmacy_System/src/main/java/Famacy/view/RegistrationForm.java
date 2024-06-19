package Famacy.view;

import Famacy.service.AccountService;

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
    private JButton logoutButton;
    private JPanel registrationPanel;

    private static final String[] ROLES = {"admin", "user"}; // Define available roles
    private String currentUsername;

    public RegistrationForm(String currentUsername) {
        this.currentUsername = currentUsername;

        setTitle("Register Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        registrationPanel = new JPanel();
        add(registrationPanel);
        placeComponents(registrationPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem(); // Get selected role
                int employeeId = Integer.parseInt(employeeIdField.getText());

                if (accountService.registerAccount(username, password, role, employeeId)) {
                    JOptionPane.showMessageDialog(null, "Account registered successfully!");
                    dispose();
                    MainMenu mainMenu = new MainMenu(currentUsername); // Continue with the current logged-in user
                    mainMenu.setVisible(true);
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
                MainMenu mainMenu = new MainMenu(currentUsername); // Continue with the current logged-in user
                mainMenu.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logout and return to login screen
                dispose();
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
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

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 50, 165, 25);
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        roleComboBox = new JComboBox<>(ROLES);
        roleComboBox.setBounds(150, 80, 165, 25);
        panel.add(roleComboBox);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(10, 110, 100, 25);
        panel.add(employeeIdLabel);

        employeeIdField = new JTextField(20);
        employeeIdField.setBounds(150, 110, 165, 25);
        panel.add(employeeIdField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 140, 150, 25);
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 140, 100, 25);
        panel.add(backButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 170, 150, 25);
        panel.add(logoutButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistrationForm registrationForm = new RegistrationForm("currentuser"); // Example usage
            registrationForm.setVisible(true);
        });
    }
}
