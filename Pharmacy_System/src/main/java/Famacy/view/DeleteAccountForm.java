package Famacy.view;

import Famacy.service.AccountService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountForm extends JFrame {
    private JTextField usernameField;
    private JButton deleteAccountButton;
    private JButton backButton;
    private JPanel deleteAccountPanel;

    private String currentUsername;

    public DeleteAccountForm(String currentUsername) {
        this.currentUsername = currentUsername;

        setTitle("Delete Account");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        deleteAccountPanel = new JPanel();
        deleteAccountPanel.setLayout(null);
        add(deleteAccountPanel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        deleteAccountPanel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 20, 165, 25);
        deleteAccountPanel.add(usernameField);

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBounds(10, 60, 150, 25);
        deleteAccountPanel.add(deleteAccountButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 60, 100, 25);
        deleteAccountPanel.add(backButton);

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String username = usernameField.getText();

                if (accountService.getRoleByUsername(username) != null) {
                    accountService.deleteAccount(username);
                    JOptionPane.showMessageDialog(null, "Account deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
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

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DeleteAccountForm deleteAccountForm = new DeleteAccountForm("admin"); // Example usage
//            deleteAccountForm.setVisible(true);
//        });
//    }
}
