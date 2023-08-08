
package bankaotomasyonu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    
    private final String ID;
    private int balance;
    private String accountType;
    private LocalDate openingDate;
    protected List<String> transactions;
    
    public Account(String ID, int balance){
        this.ID= ID;
        this.balance = balance;
        this.openingDate= Bank.getDate();
        this.transactions = new ArrayList();
    }   
    
    public void deposit(String ID, int cash){
        balance += cash;
        System.out.println("Para hesaba yatırıldı.");        
        String transaction = String.format("Deposit: +%d", cash);
        transactions.add(transaction);
    }
    
    public void withdraw(String ID, int cash) throws BalanceInsufficentException{
        if(balance>=cash){
            setBalance(balance-cash); 
            System.out.println(cash +" TL tutarında para çekildi. Yeni bakiyeniz: "+ balance);
            String transaction = String.format("Withdraw: -%d", cash);
            transactions.add(transaction);
        }
        else{
            throw new BalanceInsufficentException("Bakiye yetersiz.");
        }
    }
    
    public void showTransactions() {
        System.out.println("Transactions:");
        for (int i = Math.max(0, transactions.size() - 5); i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }
    
    public abstract double benefit();  
    
    public int getBalance(){
        return balance;
    }
    public String getID(){
        return ID;
    }
    public LocalDate getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }
    public String getAccountType(){
        return accountType;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }  
    
}
