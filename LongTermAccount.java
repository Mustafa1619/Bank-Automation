/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaotomasyonu;

/**
 *
 * @author Gaming-3
 */
public class LongTermAccount extends Account{
    
    public LongTermAccount(String ID , int balance){
        super(ID,balance);
        setAccountType("LongTermAcc");
    
    }

    @Override
    public double benefit() {
        return getBalance() * 0.24 / 365;
    }

   
    
}
