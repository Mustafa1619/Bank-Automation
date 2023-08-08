package bankaotomasyonu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Bank{
    private static LocalDate currentDate;
    private static final String fileName = "C:\\Users\\Gaming-3\\Documents\\NetBeansProjects\\BankaOtomasyonu\\src\\bankaotomasyonu\\hesaplar.txt";
    private static List<Account> hesaplar = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    
    
    public Bank(){     
        
       setDate(2023, 05, 05);
     
    }
    
    public void showAccounts() {
        System.out.println("Hesaplar:");
        for (Account account : hesaplar) {
            System.out.println(account);
            showAccountTransactions(account.getID());
        }
    }
    
    public void showAccountTransactions(String ID) {
        Account account = findAccount(ID);
        if (account != null) {
            System.out.println("Hesap numarası: " + ID);
            account.showTransactions();
        } else {
            System.out.println("Hesap bulunamadı.");
        }
    }

        public void showAccountIDs() {
        System.out.println("Hesap numaraları:");
        for (Account account : hesaplar) {
            System.out.println(account.getID());
        }
    }

    
 

    public void deposit(String ID, int cash){
        Account account = findAccount(ID);// casting
        if(account !=null){
            account.deposit(ID, cash);
        }
        else{
            System.out.println("Hesap bulunamadı.");
        }

    }
    
    public void withdraw(String ID, int cash){
        Account account = findAccount(ID);
        if(account != null){
            try{
                account.withdraw(ID, cash);
            }catch(BalanceInsufficentException exception){
                System.out.println(exception.getMessage());
            }
        }else{
            System.out.println("Hesap bulunamadı.");
        }
    }
    
     private Account findAccount(String ID){
        for (Account account : hesaplar){
            if (account.getID().equals(ID)) {
                return account;
            }
        }
        return null;
    }
    
    
public void sortition() {
        List<SpecialAccount> specialAccounts = getSpecialAccounts();
        if (specialAccounts.isEmpty()) {
            System.out.println("Hiç özel hesap bulunamadı.");
        } else {
            int totalPoints = calculateTotalPoints(specialAccounts);
            int winningNumber = generateRandomNumber(totalPoints);
            SpecialAccount winner = selectWinner(specialAccounts, winningNumber);
            if (winner != null) {
                winner.deposit(winner.getID(), 10000);
                System.out.println("Çekiliş yapıldı. Kazananın hesap numarası" + winner.getID());
                System.out.println("Ödül miktarı (10000 TL) kazananın hesabına yatırıldı.");
            }
        }
    }
    private List<SpecialAccount> getSpecialAccounts() {
        List<SpecialAccount> specialAccounts = new ArrayList<>();
        for (Account account : hesaplar) {
            if (account instanceof SpecialAccount) {
                specialAccounts.add((SpecialAccount) account);
            }
        }
        return specialAccounts;
    }
    
     private int calculateTotalPoints(List<SpecialAccount> specialAccounts) {
        int totalPoints = 0;
        for (SpecialAccount account : specialAccounts) {
            totalPoints += account.getBalance() / 2000;
        }
        return totalPoints;
    }
    
      private int generateRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
      
      private SpecialAccount selectWinner(List<SpecialAccount> specialAccounts, int winningNumber) {
        int accumulatedPoints = 0;
        for (SpecialAccount account : specialAccounts) {
            int points = account.getBalance() / 2000;
            accumulatedPoints += points;
            if (winningNumber <= accumulatedPoints) {
                return account;
            }
        }
        return null;
    }
     
    public void setDate(int year, int month, int dayOfMonth){
        try{
            currentDate = LocalDate.of(year, month, dayOfMonth);
            System.out.println("Sistem tarihi güncellendi: " + currentDate);
        }catch(DateTimeException e){
            System.out.println("Düzgün formatta tarih giriniz lütfen");
        }
    }   
    
    public void createShortTermAccount(String ID, int balance) {
            if(!isAccountValid(ID)){
                if (balance >= 1000) {
                    Account account = new ShortTermAccount(ID, balance);
                    hesaplar.add(account);
                    System.out.println("Kısa vadeli hesap oluşturuldu. Hesap numarası: " + account.getID());
                } else {
                    System.out.println("Kısa vadeli hesap açmak için gereken minimum miktar 1000 TL'dir.");
                }
            }else{
                System.out.println("Böyle bir hesap numarası zaten kullanımda. Başka hesap numarası deneyiniz.");
            }
    }
    
    public void createSpecialAccount(String ID ,int balance) {
            if(!isAccountValid(ID)){
                Account account = new SpecialAccount(ID,balance);
                hesaplar.add(account);
                System.out.println("Özel hesap oluşturuldu. Hesap numarası: " + account.getID());
            }else{
                System.out.println("Böyle bir hesap numarası zaten kullanımda. Başka hesap numarası deneyiniz.");
            }
        }
    
    public void createLongTermAccount(String ID,int balance) {
        if(!isAccountValid(ID)){
            if (balance >= 1500) {
                Account account = new LongTermAccount(ID, balance);
                hesaplar.add(account);
                System.out.println("Uzun vadeli hesap oluşturuldu. Hesap numarası: " + account.getID());
            } else {
                System.out.println("Uzun vadeli hesap açmak için gereken minimum miktar 1500 TL'dir.");
            }
        }else{
            System.out.println("Böyle bir hesap numarası zaten kullanımda. Başka hesap numarası deneyiniz.");
        }
    }
    
    public void createCurrentAccount(String ID ,int balance) {
        if(!isAccountValid(ID)){
            Account account = new CurrentAccount(ID,balance);
            hesaplar.add(account);
            System.out.println("Cari hesap oluşturuldu. Hesap numarası: " + account.getID());
        }else{
        System.out.println("Böyle bir hesap numarası zaten kullanımda. Başka hesap numarası deneyiniz.");
        }
    }
    
    private boolean isAccountValid(String ID){
        for(Account account : hesaplar){
            if(account.getID().equals(ID)){
                return true;    
            }
        }
        return false;
    }
    
   
    
    public void hesaplariDosyayaKaydet(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : hesaplar) {
                writer.write(account.getID() + "\t" + account.getBalance() + "\t" + account.getAccountType()+ "\t" + account.getOpeningDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hesap bilgileri dosyaya kaydedilemedi: " + e.getMessage());
        }    
    }
    
    public void dosyadanHesaplariYukle(){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountInfo = line.split("\t");
                if (accountInfo.length == 4) {
                    
                    String ID = accountInfo[0];
                    int balance = Integer.parseInt(accountInfo[1]);
                    String accountType = accountInfo[2];
                    LocalDate openingDate = LocalDate.parse(accountInfo[3]);
                    switch(accountType){
                        case "ShortTermAcc":
                            Account account = new ShortTermAccount(ID, balance);
                            account.setOpeningDate(openingDate);
                            hesaplar.add(account);
                            break;
                        
                        case "LongTermAcc":
                            account = new LongTermAccount(ID, balance);
                            account.setOpeningDate(openingDate);
                            hesaplar.add(account);                            
                            break;
                        
                        case "CurrentAcc":
                            account = new CurrentAccount(ID, balance);
                            account.setOpeningDate(openingDate);
                            hesaplar.add(account);                            
                            break;
                        
                        case "SpecialAcc":
                            account = new SpecialAccount(ID, balance);
                            account.setOpeningDate(openingDate);
                            hesaplar.add(account);
                            break;
                        
                        default: 
                            System.out.println("düzgün bir değer giriniz lütfen.");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Hesap bilgileri dosyadan yüklenemedi: " + e.getMessage());
        }
    }

    public void calculateBenefits(int gun){
        
        for(Account account: hesaplar){
            if(!"CurrentAcc".equals(account.getAccountType())){
            account.setBalance(account.getBalance()+(int)account.benefit() * gun);
            }
        System.out.println("Faizler yatırıldı.");
        }
    }
    
    public static LocalDate getDate(){
        return currentDate;
    }
    
}

