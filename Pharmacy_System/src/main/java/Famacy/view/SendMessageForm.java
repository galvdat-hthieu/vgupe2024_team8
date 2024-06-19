package Famacy.view;

import javax.swing.*;
import java.awt.*;

public class SendMessageForm extends JFrame {
    private JPanel mainPanel;
    private JTextField fromTextField;
    private JComboBox<String> toComboBox;
    private JTextArea messageTextArea;
    private JButton sendButton;
    private JButton backButton;

    public SendMessageForm() {
        setTitle("Send_Message Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel);

        JLabel titleLabel = new JLabel("Send_Message Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(10, 10, 460, 30);
        mainPanel.add(titleLabel);

        JLabel messageInfoLabel = new JLabel("Message_Information", SwingConstants.LEFT);
        messageInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageInfoLabel.setForeground(Color.WHITE);
        messageInfoLabel.setBounds(20, 50, 200, 25);
        mainPanel.add(messageInfoLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 70, 460, 10);
        mainPanel.add(separator);

        JLabel fromLabel = new JLabel("Message_From :");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setBounds(20, 90, 120, 25);
        mainPanel.add(fromLabel);

        fromTextField = new JTextField();
        fromTextField.setBounds(150, 90, 200, 25);
        mainPanel.add(fromTextField);

        JLabel toLabel = new JLabel("Message_To :");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        toLabel.setForeground(Color.WHITE);
        toLabel.setBounds(20, 130, 120, 25);
        mainPanel.add(toLabel);

        toComboBox = new JComboBox<>();
        toComboBox.setBounds(150, 130, 200, 25);
        mainPanel.add(toComboBox);

        JLabel messageLabel = new JLabel("Message_Text :");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(20, 170, 120, 25);
        mainPanel.add(messageLabel);

        messageTextArea = new JTextArea();
        messageTextArea.setBounds(150, 170, 200, 100);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setBounds(150, 170, 200, 100);
        mainPanel.add(messageScrollPane);

        sendButton = new JButton("Send");
        sendButton.setBounds(150, 290, 80, 25);
        mainPanel.add(sendButton);

        backButton = new JButton("Back to Inbox");
        backButton.setBounds(250, 290, 150, 25);
        mainPanel.add(backButton);

        backButton.addActionListener(e -> {
            dispose();
            MessageForm chatApplicationUI = new MessageForm();
            chatApplicationUI.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SendMessageForm sendMessageForm = new SendMessageForm();
            sendMessageForm.setVisible(true);
        });
    }
}
