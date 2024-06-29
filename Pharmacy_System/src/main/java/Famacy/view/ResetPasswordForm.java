package Famacy.view;

import Famacy.service.AccountService;
import Famacy.PharmacyMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPasswordForm extends JFrame {
    private JTextField usernameField;
    private JButton resetPasswordButton;
    private JButton backButton;
    private JPanel resetPasswordPanel;

    private String currentUsername;

    public ResetPasswordForm(String currentUsername) {
        this.currentUsername = currentUsername;

        setTitle("Reset Password");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        resetPasswordPanel = new JPanel();
        resetPasswordPanel.setLayout(null);
        add(resetPasswordPanel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        resetPasswordPanel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 20, 165, 25);
        resetPasswordPanel.add(usernameField);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setBounds(10, 60, 150, 25);
        resetPasswordPanel.add(resetPasswordButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 60, 100, 25);
        resetPasswordPanel.add(backButton);

        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String username = usernameField.getText();

                if (accountService.getRoleByUsername(username) != null) {
                    accountService.changePassword(username, "123");
                    JOptionPane.showMessageDialog(null, "Password reset to default (123) successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to main menu
                dispose();;
            }
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ResetPasswordForm resetPasswordForm = new ResetPasswordForm("admin"); // Example usage
//            resetPasswordForm.setVisible(true);
//        });
//    }
}
