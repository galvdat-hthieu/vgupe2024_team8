package Famacy.service;

import Famacy.model.Transaction;
import Famacy.model.TransactionItem;
import Famacy.repository.MedicineRepository;
import Famacy.repository.ConsumableRepository;
import Famacy.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
    private TransactionRepository transactionRepository;
    private MedicineRepository medicineRepository;
    private ConsumableRepository consumableRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
        this.medicineRepository = new MedicineRepository();
        this.consumableRepository = new ConsumableRepository();
    }

    public Transaction saveTransaction(Transaction transaction) {
        adjustQuantities(transaction);
        return transactionRepository.saveOrUpdate(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public void updateTransaction(Transaction transaction) {
        transactionRepository.saveOrUpdate(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.delete(id);
    }

    public List<Transaction> findTransactionsByDate(String date) {
        return transactionRepository.findTransactionsByDate(date);
    }

    public boolean medicineExists(String name) {
        return !medicineRepository.findMedicineNames(name).isEmpty();
    }

    public boolean consumableExists(String name) {
        return !consumableRepository.findConsumableNames(name).isEmpty();
    }
    
    private void adjustQuantities(Transaction transaction) {
        for (TransactionItem item : transaction.getItems()) {
            if (item.getItemType().equals("Medicine")) {
                medicineRepository.updateQuantity(item.getItemName(), item.getQuantity());
            } else if (item.getItemType().equals("Consumable")) {
                consumableRepository.updateQuantity(item.getItemName(), item.getQuantity());
            }
        }
    }
    
    public List<String> getMedicineNames(String name) {
        return medicineRepository.findMedicineNames(name);
    }

    public List<String> getConsumableNames(String name) {
        return consumableRepository.findConsumableNames(name);
    }

}
