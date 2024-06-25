package Famacy.view;

import Famacy.model.Employee;
import Famacy.model.Message;
import Famacy.repository.AccountRepository;
import Famacy.repository.EmployeeRepository;
import Famacy.repository.MessageRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SendMessageForm extends JFrame {
    private JPanel mainPanel;
    private JPanel checkboxPanel;
    private JTextArea messageTextArea;
    private JButton sendButton;
    private JButton selectAllButton;
    private static String username;

    private EmployeeRepository employeeRepository;
    private MessageRepository messageRepository;

    private List<JCheckBox> userCheckBoxes;

    public SendMessageForm(String username) {
        this.username = username;
        employeeRepository = new EmployeeRepository();
        messageRepository = new MessageRepository();

        setTitle("Send Message");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure only this frame is closed
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Send Message", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Checkbox Panel
        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        JScrollPane checkboxScrollPane = new JScrollPane(checkboxPanel);
        checkboxScrollPane.setBorder(BorderFactory.createTitledBorder("Select Users"));
        centerPanel.add(checkboxScrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        sendButton = new JButton("Next");
        selectAllButton = new JButton("All");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(selectAllButton);
        buttonPanel.add(sendButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> proceedToMessage());
        selectAllButton.addActionListener(e -> selectAllUsers());

        loadEmployeeList();
    }

    private void loadEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        userCheckBoxes = new ArrayList<>();
        for (Employee employee : employees) {
            JCheckBox checkBox = new JCheckBox(employee.getName());
            userCheckBoxes.add(checkBox);
            checkboxPanel.add(checkBox);
        }
    }

    private void selectAllUsers() {
        for (JCheckBox checkBox : userCheckBoxes) {
            checkBox.setSelected(true);
        }
    }

    private void proceedToMessage() {
        List<String> selectedUsers = new ArrayList<>();
        for (JCheckBox checkBox : userCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedUsers.add(checkBox.getText());
            }
        }

        if (selectedUsers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one user.");
            return;
        }

        // Remove the user selection panel and add the message input panel
        mainPanel.removeAll();

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageTextArea = new JTextArea();
        messageTextArea.setBorder(BorderFactory.createTitledBorder("Message"));
        messagePanel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);

        JPanel sendPanel = new JPanel(new BorderLayout());
        sendButton = new JButton("Send Message");
        sendPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(messagePanel, BorderLayout.CENTER);
        mainPanel.add(sendPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage(selectedUsers));

        // Refresh the frame
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void sendMessage(List<String> selectedUsers) {
        String messageContent = messageTextArea.getText();

        for (String user : selectedUsers) {
            Integer receiverId = employeeRepository.findEmployeeIdByName(user);
            Integer senderId = AccountRepository.findEmployeeIdByUsername(username);

            if (receiverId != null && senderId != null) {
                Message message = new Message();
                message.setSenderID(senderId);
                message.setReceiverID(receiverId);
                message.setContent(messageContent);

                // Debugging information to verify message before saving
                System.out.println("Sending message from ID " + senderId + " to ID " + receiverId + " with content: "
                        + messageContent);

                messageRepository.saveMessage(message);

                // Debugging information to verify message was saved
                System.out.println("Message saved for user: " + user);
            } else {
                // Debugging information for potential issues with IDs
                System.out.println("Failed to find sender or receiver ID for user: " + user);
            }
        }

        JOptionPane.showMessageDialog(this, "Message sent to selected users.");
        dispose();
        MessageForm messageForm = new MessageForm(username);
        messageForm.setVisible(true);
    }
}
