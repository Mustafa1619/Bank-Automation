/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaotomasyonu;

/**
 *
 * @author Gaming-3
 */
public class CurrentAccount extends Account{
    
    public CurrentAccount(String ID , int balance){
        super(ID, balance);
        setAccountType("CurrentAcc");
        
    }
    
    @Override
    public double benefit() {
        return 0; 
    }

}
