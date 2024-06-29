package Famacy.service;

import Famacy.model.Account;
import Famacy.repository.AccountRepository;
import Famacy.repository.EmployeeRepository;
import Famacy.util.PasswordUtil;

public class AccountService {
    private AccountRepository accountRepository;
    private EmployeeRepository employeeRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
        this.employeeRepository = new EmployeeRepository();
    }

    public boolean validateAccount(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            String hashedPassword = PasswordUtil.hashPassword(password);
            return account.getPassword().equals(hashedPassword);
        }
        return false;
    }

    public boolean registerAccount(String username, String password, String role, int employeeId) {
        // Check if the username is already taken
        if (employeeRepository.findById(employeeId) == null){
            return false;
        }
        if (accountRepository.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(PasswordUtil.hashPassword(password)); // Hash password before saving
            account.setRole(role); // Set system role
            account.setEmployeeId(employeeId); // Set employeeId

            accountRepository.saveAccount(account);
            return true;
        } else {
            return false; // Username already exists
        }
    }
    
    public Integer getEmployeeIdByUsername(String username){
        return accountRepository.findEmployeeIdByUsername(username);
    }

    public boolean deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            accountRepository.deleteAccount(username);
            return true;
        } else {
            return false; // Username does not exist
        }
    }

    public String getRoleByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return (account != null) ? account.getRole() : null;
    }

    public void changePassword(String username, String newPassword) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            account.setPassword(PasswordUtil.hashPassword(newPassword)); // Hash password before saving
            accountRepository.saveAccount(account);
        }
    }
    
    public void changeRole(String username, String newRole) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            account.setRole(newRole);
            accountRepository.saveAccount(account);
        }
    }
    

}
