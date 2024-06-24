package Famacy.view;

import Famacy.model.Transaction;
import Famacy.model.TransactionItem;
import Famacy.service.TransactionService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionManagementForm extends JFrame {
    private TransactionService transactionService;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JTextField searchDateField;
    private JTextField itemNameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField transactionDateField;
    private DefaultTableModel itemTableModel;
    private JComboBox<String> itemTypeComboBox;
    private JWindow suggestionWindow;
    private JList<String> suggestionList;

    public TransactionManagementForm(TransactionService transactionService) {
        this.transactionService = transactionService;
        setTitle("Transaction Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        loadTransactionData();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Input Panel for adding new transactions
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        
        inputPanel.add(new JLabel("Item Type:"));
        itemTypeComboBox = new JComboBox<>(new String[]{"Medicine", "Consumable"});
        inputPanel.add(itemTypeComboBox);

        inputPanel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        inputPanel.add(itemNameField);

        suggestionWindow = new JWindow();
        suggestionList = new JList<>();
        suggestionWindow.add(new JScrollPane(suggestionList));
        suggestionWindow.setSize(200, 200);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Transaction Date:"));
        transactionDateField = new JTextField();
        inputPanel.add(transactionDateField);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        inputPanel.add(new JPanel()); // Empty cell
        inputPanel.add(addItemButton);

        add(inputPanel, BorderLayout.NORTH);

        // Initialize tableModel
        tableModel = new DefaultTableModel(new String[]{"ID", "Transaction Date", "Total Amount"}, 0);
        transactionTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(transactionTable);
        add(tableScrollPane, BorderLayout.WEST);

        // Table for displaying transaction items
        itemTableModel = new DefaultTableModel(new String[]{"Item Type", "Item Name", "Quantity", "Price"}, 0);
        JTable itemTable = new JTable(itemTableModel);
        JScrollPane itemTableScrollPane = new JScrollPane(itemTable);
        add(itemTableScrollPane, BorderLayout.CENTER);

        // Footer Panel for transaction and search functionality
        JPanel footerPanel = new JPanel(new BorderLayout());
        searchDateField = new JTextField();
        JButton searchButton = new JButton("Search by Date");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTransactions();
            }
        });

        JButton saveTransactionButton = new JButton("Save Transaction");
        saveTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransaction();
            }
        });

        footerPanel.add(new JLabel("Transaction Date:"), BorderLayout.WEST);
        footerPanel.add(searchDateField, BorderLayout.CENTER);
        footerPanel.add(searchButton, BorderLayout.EAST);
        footerPanel.add(saveTransactionButton, BorderLayout.SOUTH);
        add(footerPanel, BorderLayout.SOUTH);

        // Set up auto-complete for item names
        setupAutoComplete();
    }

    private void loadTransactionData() {
        tableModel.setRowCount(0); // Clear the table model
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                    transaction.getId(),
                    transaction.getTransactionDate(),
                    transaction.getTotalAmount()
            });
        }
    }

    private void addItem() {
        String itemType = itemTypeComboBox.getSelectedItem().toString();
        String itemName = itemNameField.getText();
        Integer quantity = Integer.parseInt(quantityField.getText());
        Double price = Double.parseDouble(priceField.getText());

        boolean itemExists = false;

        if (itemType.equals("Medicine")) {
            itemExists = transactionService.medicineExists(itemName);
        } else if (itemType.equals("Consumable")) {
            itemExists = transactionService.consumableExists(itemName);
        }

        if (itemExists) {
            itemTableModel.addRow(new Object[]{itemType, itemName, quantity, price});
        } else {
            JOptionPane.showMessageDialog(this, itemType + " with name " + itemName + " does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveTransaction() {
        List<TransactionItem> items = new ArrayList<>();
        double totalAmount = 0.0;

        for (int i = 0; i < itemTableModel.getRowCount(); i++) {
            String itemType = (String) itemTableModel.getValueAt(i, 0);
            String itemName = (String) itemTableModel.getValueAt(i, 1);
            Integer quantity = (Integer) itemTableModel.getValueAt(i, 2);
            Double price = (Double) itemTableModel.getValueAt(i, 3);

            TransactionItem item = new TransactionItem();
            item.setItemType(itemType);
            item.setItemName(itemName);
            item.setQuantity(quantity);
            item.setPrice(price);
            items.add(item);

            totalAmount += quantity * price;
        }

        String transactionDate = transactionDateField.getText();

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setTotalAmount(totalAmount);
        transaction.setItems(items);

        for (TransactionItem item : items) {
            item.setTransaction(transaction);
        }

        transactionService.saveTransaction(transaction);
        JOptionPane.showMessageDialog(this, "Transaction saved successfully!");

        // Clear the transaction items table
        itemTableModel.setRowCount(0);

        // Refresh transaction data
        loadTransactionData();
    }

    private void searchTransactions() {
        String date = searchDateField.getText();
        List<Transaction> transactions = transactionService.findTransactionsByDate(date);
        tableModel.setRowCount(0); // Clear the table model
        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                    transaction.getId(),
                    transaction.getTransactionDate(),
                    transaction.getTotalAmount()
            });
        }
    }

    private void setupAutoComplete() {
        itemNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        itemNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN && suggestionWindow.isVisible()) {
                    suggestionList.requestFocusInWindow();
                    suggestionList.setSelectedIndex(0);
                }
            }
        });

        suggestionList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    itemNameField.setText(suggestionList.getSelectedValue());
                    suggestionWindow.setVisible(false);
                }
            }
        });

        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    itemNameField.setText(suggestionList.getSelectedValue());
                    suggestionWindow.setVisible(false);
                }
            }
        });
    }

    private void updateSuggestions() {
        String itemType = itemTypeComboBox.getSelectedItem().toString();
        String input = itemNameField.getText();
        List<String> suggestions;

        if (itemType.equals("Medicine")) {
            suggestions = transactionService.getMedicineNames(input);
        } else {
            suggestions = transactionService.getConsumableNames(input);
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String suggestion : suggestions) {
            model.addElement(suggestion);
        }
        suggestionList.setModel(model);

        if (suggestions.size() > 0) {
            Point location = itemNameField.getLocationOnScreen();
            suggestionWindow.setLocation(location.x, location.y + itemNameField.getHeight());
            suggestionWindow.setVisible(true);
        } else {
            suggestionWindow.setVisible(false);
        }
    }

}
