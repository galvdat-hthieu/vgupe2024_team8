// package Famacy.view;

// import Famacy.model.Employee;
// import Famacy.model.Message;
// import Famacy.repository.AccountRepository;
// import Famacy.repository.EmployeeRepository;
// import Famacy.repository.MessageRepository;

// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.util.List;

// public class MessageForm extends JFrame {
//     private JPanel mainPanel;
//     private JComboBox<String> userComboBox;
//     private JTable messageTable;
//     private DefaultTableModel model;
//     private JButton sendButton;
//     private static String username;

//     private AccountRepository accountRepository;
//     private EmployeeRepository employeeRepository;
//     private MessageRepository messageRepository;

//     public MessageForm(String username) {
//         this.username = username;
//         employeeRepository = new EmployeeRepository();
//         messageRepository = new MessageRepository();
//         accountRepository = new AccountRepository();

//         setTitle("Inbox");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure only this frame is closed
//         setLocationRelativeTo(null);

//         mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout());
//         add(mainPanel);

//         // Top Panel
//         JPanel topPanel = new JPanel(new BorderLayout());
//         JLabel titleLabel = new JLabel("Inbox Form", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         topPanel.add(titleLabel, BorderLayout.CENTER);
//         mainPanel.add(topPanel, BorderLayout.NORTH);

//         // Left Panel
//         JPanel leftPanel = new JPanel();
//         leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//         leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         JLabel userNameLabel = new JLabel("User_Name : " + username);
//         userNameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//         leftPanel.add(userNameLabel);

//         JLabel yourConversationLabel = new JLabel("Your Conversation :");
//         yourConversationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
//         leftPanel.add(yourConversationLabel);

//         userComboBox = new JComboBox<>();
//         userComboBox.setPreferredSize(new Dimension(200, 25));
//         userComboBox.setMaximumSize(new Dimension(200, 25));
//         userComboBox.addActionListener(e -> loadMessages());
//         leftPanel.add(userComboBox);

//         mainPanel.add(leftPanel, BorderLayout.WEST);

//         // Center Panel
//         JPanel centerPanel = new JPanel(new BorderLayout());
//         String[] columnNames = { "Message_From", "Message_To", "Message_Text" };
//         model = new DefaultTableModel(columnNames, 0);
//         messageTable = new JTable(model);
//         JScrollPane scrollPane = new JScrollPane(messageTable);
//         centerPanel.add(scrollPane, BorderLayout.CENTER);

//         mainPanel.add(centerPanel, BorderLayout.CENTER);

//         // Bottom Panel
//         JPanel bottomPanel = new JPanel(new BorderLayout());
//         sendButton = new JButton("Send_Message");
//         bottomPanel.add(sendButton, BorderLayout.EAST);

//         mainPanel.add(bottomPanel, BorderLayout.SOUTH);

//         sendButton.addActionListener(e -> {
//             dispose();
//             SendMessageForm sendMessageForm = new SendMessageForm(username);
//             sendMessageForm.setVisible(true);
//         });

//         loadEmployeeList();
//         loadMessages();
//     }

//     private void loadEmployeeList() {
//         List<Employee> employees = employeeRepository.findAll();
//         userComboBox.removeAllItems();
//         for (Employee employee : employees) {
//             userComboBox.addItem(employee.getName());
//         }
//     }

//     private void loadMessages() {
//         String selectedUser = (String) userComboBox.getSelectedItem();
//         Integer selectedUserId = employeeRepository.findEmployeeIdByName(selectedUser);
//         Integer realId = accountRepository.findEmployeeIdByUsername(username);

//         List<Message> messages = messageRepository.findAll();
//         model.setRowCount(0); // Clear existing rows

//         for (Message message : messages) {
//             Integer senderId = message.getId().getSenderID();
//             Integer receiverId = message.getId().getReceiverID();
//             String senderName = employeeRepository.findEmployeeNameById(senderId);
//             String receiverName = employeeRepository.findEmployeeNameById(receiverId);
//             String content = message.getContent(); // Ensure correct content is fetched here
//             //System.out.println(senderId + " " + receiverId + " " + content);

//             if ((senderId.equals(realId) && receiverId.equals(selectedUserId)) ||
//                     (senderId.equals(selectedUserId) && receiverId.equals(realId))) {
//                 model.addRow(new Object[] { senderName, receiverName, content });
//             }
//         }
//     }

//     public void addMessageToTable(String messageFrom, String messageTo, String messageText) {
//         model.addRow(new Object[] { messageFrom, messageTo, messageText });
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             MessageForm messageForm = new MessageForm(username);
//             messageForm.setVisible(true);
//         });
//     }
// }

package Famacy.view;

import Famacy.model.Employee;
import Famacy.model.Message;
import Famacy.repository.AccountRepository;
import Famacy.repository.EmployeeRepository;
import Famacy.repository.MessageRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MessageForm extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> userComboBox;
    private JTable messageTable;
    private DefaultTableModel model;
    private JButton sendButton;
    private static String username;

    private AccountRepository accountRepository;
    private EmployeeRepository employeeRepository;
    private MessageRepository messageRepository;

    public MessageForm(String username) {
        this.username = username;
        employeeRepository = new EmployeeRepository();
        messageRepository = new MessageRepository();
        accountRepository = new AccountRepository();

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

        JLabel userNameLabel = new JLabel("User_Name : " + username);
        userNameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        leftPanel.add(userNameLabel);

        JLabel yourConversationLabel = new JLabel("Your Conversation :");
        yourConversationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        leftPanel.add(yourConversationLabel);

        userComboBox = new JComboBox<>();
        userComboBox.setPreferredSize(new Dimension(200, 25));
        userComboBox.setMaximumSize(new Dimension(200, 25));
        userComboBox.addActionListener(e -> loadMessages());
        leftPanel.add(userComboBox);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Message_From", "Message_To", "Message_Text" };
        model = new DefaultTableModel(columnNames, 0);
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
            SendMessageForm sendMessageForm = new SendMessageForm(username);
            sendMessageForm.setVisible(true);
        });

        loadEmployeeList();
        loadMessages();
    }

    private void loadEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        userComboBox.removeAllItems();
        for (Employee employee : employees) {
            userComboBox.addItem(employee.getName());
        }
    }

    private void loadMessages() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        if (selectedUser == null)
            return;

        Integer selectedUserId = employeeRepository.findEmployeeIdByName(selectedUser);
        if (selectedUserId == null)
            return;

        Integer realId = accountRepository.findEmployeeIdByUsername(username);
        if (realId == null)
            return;

        List<Message> messages = messageRepository.findAll();
        model.setRowCount(0); // Clear existing rows

        for (Message message : messages) {
            Integer senderId = message.getSenderID();
            Integer receiverId = message.getReceiverID();
            String senderName = employeeRepository.findEmployeeNameById(senderId);
            String receiverName = employeeRepository.findEmployeeNameById(receiverId);
            String content = message.getContent(); // Ensure correct content is fetched here

            // Debugging statements to verify message content
            System.out.println("Message: senderId=" + senderId + ", receiverId=" + receiverId + ", content=" + content);

            if ((senderId.equals(realId) && receiverId.equals(selectedUserId)) ||
                    (senderId.equals(selectedUserId) && receiverId.equals(realId))) {
                System.out.println("Adding row: " + senderName + ", " + receiverName + ", " + content);
                model.addRow(new Object[] { senderName, receiverName, content });
            }
        }
    }

    public void addMessageToTable(String messageFrom, String messageTo, String messageText) {
        model.addRow(new Object[] { messageFrom, messageTo, messageText });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageForm messageForm = new MessageForm(username);
            messageForm.setVisible(true);
        });
    }
}
