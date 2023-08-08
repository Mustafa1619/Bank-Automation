package bankaotomasyonu;

import java.util.Scanner;



public class BankaOtomasyonu {

    
    public static void main(String[] args) {
        System.out.println("Bankaya hoşgeldiniz.");
        Bank bank = new Bank();
        bank.dosyadanHesaplariYukle();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("1- Hesap Oluştur");
            System.out.println("2- Para Yatır");
            System.out.println("3- Para Çek");
            System.out.println("4- Tarihi Düzenle");
            System.out.println("5- Tüm Hesapları Görüntüle");
            System.out.println("6- Tüm Hesap Numaralarını Görüntüle");
            System.out.println("7- Kâr Hesapla");
            System.out.println("8- Özel Hesaplar Arasında Çekiliş Yap");
            System.out.println("9- Çıkış");
            System.out.println("Yapmak istediğiniz işlemin numarasını giriniz: ");
            int secim = scan.nextInt();
            
            switch(secim){
                case 1 : 
                    int type;
                    System.out.print("Yeni hesap numarasını giriniz: ");
                    String ID = scan.next();
                    System.out.print("Başlangıç bakiyesini giriniz: ");
                    int balance = scan.nextInt();
                    System.out.println("1- Kısa Vadeli Hesap");
                    System.out.println("2- Uzun Vadeli Hesap");
                    System.out.println("3- Cari Hesap");
                    System.out.println("4- Özel Hesap");
                    System.out.println("Oluşturmak istediğiniz Hesap Türünün Numarasını Giriniz: ");
                    type = scan.nextInt();
                    switch(type){
                        case 1:
                            bank.createShortTermAccount(ID, balance);
                            break;
                        case 2:
                            bank.createLongTermAccount(ID, balance);
                            break;
                        case 3: 
                            bank.createCurrentAccount(ID, balance);
                            break;
                        case 4:
                            bank.createSpecialAccount(ID, balance);
                            break;
                            
                    }break; 
                case 2 :
                    System.out.print("Para Yatırmak İstediğiniz Hesabın Numarasını Giriniz: ");
                    ID =  scan.next();
                    System.out.print("Yatırmak İstediğiniz miktarı giriniz: ");
                    balance = scan.nextInt();
                    bank.deposit(ID, balance);
                    break;
                
                case 3 :
                    System.out.print("Para Çekmek İstediğiniz Hesabın Numarasını Giriniz: ");
                    ID =  scan.next();
                    System.out.print("Çekmek İstediğiniz miktarı giriniz: ");
                    balance = scan.nextInt();
                    bank.withdraw(ID, balance);
                    break;

                case 4 :
                    System.out.print("Sistemin tarihini düzenleyiniz(YYYY/AA/GG): ");
                    int year = scan.nextInt();
                    int month = scan.nextInt();
                    int dayOfMonth = scan.nextInt();
                    bank.setDate(year,month,dayOfMonth);
                    break;
                case 5 :
                    System.out.println("Hesaplar görüntüleniyor... ");
                    bank.showAccounts();
                    break;
                case 6 :
                    System.out.println("Hesap numaraları görüntüleniyor...");
                    bank.showAccountIDs();
                    break;
                    
                case 7:
                    System.out.println("Kaç Günlük kâr hesaplanacak?: ");
                    int gun = scan.nextInt();
                    bank.calculateBenefits(gun);
                    break;
                
                case 8 :
                    System.out.println("Çekiliş Hazırlanıyor...");
                    bank.sortition();
                    break;

                case 9 :
                    System.out.println("Çıkış yapılıyor...");
                    bank.hesaplariDosyayaKaydet();
                    System.exit(0);
                    break;
                default: 
                    System.out.println("Düzgün bir değer giriniz lütfen.");
            }    
        }     
        
    }
    
}
