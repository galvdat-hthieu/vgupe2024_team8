package Famacy.view;

import Famacy.service.AccountService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton changePasswordButton;
    private JButton registerAccountButton;
    private JButton resetPasswordButton;
    private JButton deleteAccountButton;
    private JButton logoutButton;
    private JLabel usernameLabel;
    private JLabel roleLabel;
    private JPanel mainPanel;

    public MainMenu(String username) {
        setTitle("Main Menu");
        setSize(500, 350); // Adjust size to fit all buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        AccountService accountService = new AccountService();
        String role = accountService.getRoleByUsername(username);

        usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setBounds(10, 10, 200, 25);
        mainPanel.add(usernameLabel);

        roleLabel = new JLabel("Role: " + role);
        roleLabel.setBounds(10, 40, 200, 25);
        mainPanel.add(roleLabel);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(50, 100, 150, 25);
        mainPanel.add(changePasswordButton);

        registerAccountButton = new JButton("Create Account");
        registerAccountButton.setBounds(250, 100, 150, 25);
        mainPanel.add(registerAccountButton);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setBounds(250, 150, 150, 25);
        mainPanel.add(resetPasswordButton);

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBounds(250, 200, 150, 25);
        mainPanel.add(deleteAccountButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 150, 150, 25);
        mainPanel.add(logoutButton);

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Change Password Form
                ChangePasswordForm changePasswordForm = new ChangePasswordForm(username);
                changePasswordForm.setVisible(true);
                dispose();
            }
        });

        registerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Registration Form
                RegistrationForm registrationForm = new RegistrationForm(username);
                registrationForm.setVisible(true);
                dispose();
            }
        });

        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Reset Password Form
                ResetPasswordForm resetPasswordForm = new ResetPasswordForm(username);
                resetPasswordForm.setVisible(true);
                dispose();
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Delete Account Form
                DeleteAccountForm deleteAccountForm = new DeleteAccountForm(username);
                deleteAccountForm.setVisible(true);
                dispose();
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

        if (!"admin".equals(role)) {
            registerAccountButton.setEnabled(false); // Disable the button for non-admin users
            resetPasswordButton.setEnabled(false); // Disable the button for non-admin users
            deleteAccountButton.setEnabled(false); // Disable the button for non-admin users
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu("testuser"); // Example usage
            mainMenu.setVisible(true);
        });
    }
}
