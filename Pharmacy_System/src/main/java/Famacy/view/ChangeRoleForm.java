package Famacy.view;

import Famacy.service.AccountService;
import Famacy.PharmacyMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeRoleForm extends JFrame {
    private JTextField usernameField;
    private JComboBox<String> roleComboBox;
    private JButton changeRoleButton;
    private JButton backButton;
    private JPanel changeRolePanel;

    private String currentUsername;
    private static final String[] ROLES = {"user", "user (Authorized)"}; // Define available roles

    public ChangeRoleForm(String currentUsername) {
        this.currentUsername = currentUsername;

        setTitle("Change Role");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        changeRolePanel = new JPanel();
        changeRolePanel.setLayout(null);
        add(changeRolePanel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        changeRolePanel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 20, 165, 25);
        changeRolePanel.add(usernameField);

        JLabel roleLabel = new JLabel("New Role:");
        roleLabel.setBounds(10, 60, 80, 25);
        changeRolePanel.add(roleLabel);

        roleComboBox = new JComboBox<>(ROLES);
        roleComboBox.setBounds(150, 60, 165, 25);
        changeRolePanel.add(roleComboBox);

        changeRoleButton = new JButton("Change Role");
        changeRoleButton.setBounds(10, 100, 150, 25);
        changeRolePanel.add(changeRoleButton);

        backButton = new JButton("Back");
        backButton.setBounds(170, 100, 100, 25);
        changeRolePanel.add(backButton);

        changeRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = new AccountService();
                String username = usernameField.getText();
                String newRole = (String) roleComboBox.getSelectedItem();

                if (accountService.getRoleByUsername(username) != null) {
                    accountService.changeRole(username, newRole);
                    JOptionPane.showMessageDialog(null, "Role changed successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PharmacyMain pharmacyMain = new PharmacyMain(currentUsername);
                pharmacyMain.setVisible(true);
            }
        });
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         ChangeRoleForm changeRoleForm = new ChangeRoleForm("admin"); // Example usage
    //         changeRoleForm.setVisible(true);
    //     });
    // }
}
