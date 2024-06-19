package Famacy.view;

import Famacy.service.AccountService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordForm extends JFrame {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton changePasswordButton;
    private JButton backButton;
    private JButton logoutButton;
    private JPanel changePasswordPanel;

    private String username;

    public ChangePasswordForm(String username) {
        this.username = username;

        setTitle("Change Password");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        changePasswordPanel = new JPanel();
        add(changePasswordPanel);
        placeComponents(changePasswordPanel);

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());

                if (accountService.validateAccount(username, oldPassword)) {
                    accountService.changePassword(username, newPassword);
                    JOptionPane.showMessageDialog(null, "Password changed successfully!");
                    dispose();
                    MainMenu mainMenu = new MainMenu(username);
                    mainMenu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Old password is incorrect.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to main menu
                dispose();
                MainMenu mainMenu = new MainMenu(username);
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

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setBounds(10, 20, 100, 25);
        panel.add(oldPasswordLabel);

        oldPasswordField = new JPasswordField(20);
        oldPasswordField.setBounds(150, 20, 165, 25);
        panel.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(10, 50, 100, 25);
        panel.add(newPasswordLabel);

        newPasswordField = new JPasswordField(20);
        newPasswordField.setBounds(150, 50, 165, 25);
        panel.add(newPasswordField);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(10, 80, 150, 25);
        panel.add(changePasswordButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 80, 100, 25);
        panel.add(backButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 110, 150, 25);
        panel.add(logoutButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChangePasswordForm changePasswordForm = new ChangePasswordForm("testuser"); // Example usage
            changePasswordForm.setVisible(true);
        });
    }
}
