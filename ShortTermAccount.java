/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaotomasyonu;

/**
 *
 * @author Gaming-3
 */
public class ShortTermAccount extends Account{
    
    public ShortTermAccount(String ID, int balance){
        super(ID, balance);
        setAccountType("ShortTermAcc");
    }
    
    @Override
    public double benefit() {
        return getBalance() * 0.17 / 365;
    }
}
