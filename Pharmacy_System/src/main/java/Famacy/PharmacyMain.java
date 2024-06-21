package Famacy;

import Famacy.service.AccountService;
import Famacy.view.ChangePasswordForm;
import Famacy.view.DeleteAccountForm;
import Famacy.view.LoginForm;
import Famacy.view.RegistrationForm;
import Famacy.view.ResetPasswordForm;
import Famacy.view.EmployeeManagementForm;
import Famacy.view.MedicineManagementForm;
import Famacy.view.MessageForm;
import Famacy.service.EmployeeService;
import Famacy.service.MedicineService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PharmacyMain extends JFrame {
    private String username;
    private String role;
    private EmployeeService employeeService;
    private MedicineService medicineService;

    public PharmacyMain(String username) {
        this.username = username;
        AccountService accountService = new AccountService();
        this.role = accountService.getRoleByUsername(username);
        this.employeeService = new EmployeeService();
        this.medicineService = new MedicineService();

        setTitle("Famacy's Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        initializeComponents();
    }

    private void initializeComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#E6EEF2")); // Set background color

        // Top search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode("#E6EEF2"));
        JTextField searchField = new JTextField("Enter the meds name here...", 30);
        JButton searchButton = new JButton(new ImageIcon("search_icon.png")); // Use actual path to your icon
        JButton micButton = new JButton(new ImageIcon("mic_icon.png")); // Use actual path to your icon
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(micButton);

        // Middle buttons
        JPanel middlePanel = new JPanel(new GridLayout(1, 4, 10, 10));
        middlePanel.setBackground(Color.decode("#E6EEF2"));
        JButton inventoryButton = new JButton("Inventory Management");
        JButton supplyButton = new JButton("Supply Form Generation");
        JButton chatButton = new JButton("Internal Chat");

        middlePanel.add(inventoryButton);
        middlePanel.add(supplyButton);
        middlePanel.add(chatButton);

        // Only add the Employee Management button if the user's role is admin
        if ("admin".equals(role)) {
            JButton employeeButton = new JButton("Employee Management");
            middlePanel.add(employeeButton);

            employeeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EmployeeManagementForm recordForm = new EmployeeManagementForm(employeeService);
                    recordForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    recordForm.setVisible(true);
                }
            });
        }

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineManagementForm medicineForm = new MedicineManagementForm(medicineService);
                medicineForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                medicineForm.setVisible(true);
            }
        });

        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageForm chatApplicationUI = new MessageForm(username);
                chatApplicationUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                chatApplicationUI.setVisible(true);
            }
        });

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(14, 1, 10, 10));
        sidebar.setBackground(Color.decode("#E6EEF2"));

        // Admin functionalities
        if ("admin".equals(role)) {
            JButton accountManagerButton = new JButton("Account Manager");
            accountManagerButton.addActionListener(e -> {
                JFrame accountManagerFrame = new JFrame("Account Manager");
                accountManagerFrame.setSize(400, 300);
                accountManagerFrame.setLocationRelativeTo(null);
                accountManagerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel accountManagerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                JButton createAccountButton = new JButton("Create Account");
                JButton resetPasswordButton = new JButton("Reset Password");
                JButton deleteAccountButton = new JButton("Delete Account");

                createAccountButton.addActionListener(event -> {
                    RegistrationForm registrationForm = new RegistrationForm(username);
                    registrationForm.setVisible(true);
                    dispose(); // Close PharmacyMain
                    accountManagerFrame.dispose();
                });

                resetPasswordButton.addActionListener(event -> {
                    ResetPasswordForm resetPasswordForm = new ResetPasswordForm(username);
                    resetPasswordForm.setVisible(true);
                    dispose(); // Close PharmacyMain
                    accountManagerFrame.dispose();
                });

                deleteAccountButton.addActionListener(event -> {
                    DeleteAccountForm deleteAccountForm = new DeleteAccountForm(username);
                    deleteAccountForm.setVisible(true);
                    dispose(); // Close PharmacyMain
                    accountManagerFrame.dispose();
                });

                accountManagerPanel.add(createAccountButton);
                accountManagerPanel.add(resetPasswordButton);
                accountManagerPanel.add(deleteAccountButton);
                accountManagerFrame.add(accountManagerPanel);

                accountManagerFrame.setVisible(true);
            });

            sidebar.add(accountManagerButton);
        }

        // Your Account button
        JButton yourAccountButton = new JButton("Your Account");
        yourAccountButton.addActionListener(e -> {
            JFrame accountFrame = new JFrame("Your Account");
            accountFrame.setSize(300, 200);
            accountFrame.setLocationRelativeTo(null);
            accountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel accountPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            JButton changePasswordButton = new JButton("Change Password");
            JButton logoutButton = new JButton("Logout");

            changePasswordButton.addActionListener(event -> {
                ChangePasswordForm changePasswordForm = new ChangePasswordForm(username);
                changePasswordForm.setVisible(true);
                accountFrame.dispose();
                dispose();
            });

            logoutButton.addActionListener(event -> {
                dispose();
                new LoginForm().setVisible(true);
            });

            accountPanel.add(changePasswordButton);
            accountPanel.add(logoutButton);
            accountFrame.add(accountPanel);
            accountFrame.setVisible(true);
        });
        sidebar.add(yourAccountButton);

        String[] sidebarItems = { "Personal Info", "History", "Transactions", "Records", "Themes", "Zooms", "Print",
                "Help", "Setting" };
        for (String item : sidebarItems) {
            JButton button = new JButton(item);
            sidebar.add(button);
        }

        // Status bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel userInfoLabel = new JLabel("User: " + username + " | Role: " + role);
        userInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(userInfoLabel, BorderLayout.WEST);

        // Assemble all panels
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(sidebar, BorderLayout.EAST);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
