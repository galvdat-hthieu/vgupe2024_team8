package Famacy.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MessageForm extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> userComboBox;
    private JTable messageTable;
    private JButton sendButton;

    public MessageForm() {
        setTitle("Inbox");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure only this frame is closed
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Inbox Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel yourConversationLabel = new JLabel("Your Conversation :");
        yourConversationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(yourConversationLabel);

        userComboBox = new JComboBox<>();
        userComboBox.setPreferredSize(new Dimension(200, 25));
        userComboBox.setMaximumSize(new Dimension(200, 25));
        leftPanel.add(userComboBox);

        JLabel userNameLabel = new JLabel("User_Name :");
        userNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(userNameLabel);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Message_From", "Message_To", "Message_Text" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        messageTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(messageTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        sendButton = new JButton("Send_Message");
        bottomPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            dispose();
            SendMessageForm sendMessageForm = new SendMessageForm();
            sendMessageForm.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageForm messageForm = new MessageForm();
            messageForm.setVisible(true);
        });
    }
}
