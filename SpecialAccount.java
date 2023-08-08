
package bankaotomasyonu;


public class SpecialAccount extends Account{
    
    private int startBalance;
    
    public SpecialAccount(String ID , int balance ){
        super(ID, balance);
        setAccountType("SpecialAcc");
        this.startBalance = balance;
    }
    @Override
    public void withdraw(String ID, int cash) throws BalanceInsufficentException{
        int withdrawable = getBalance() - startBalance;
        if(withdrawable >= cash){
            setBalance(getBalance()-cash);
            System.out.println(cash +" TL tutarında para çekildi. Yeni bakiyeniz: "+ getBalance());
            String transaction = String.format("Withdraw: -%d", cash);
            transactions.add(transaction);
        }else{
            throw new BalanceInsufficentException("Ana parayı çekemezsiniz.");
        }
    }
    @Override
    public double benefit() {
        return getBalance() * 0.12 / 365;
    }
}
