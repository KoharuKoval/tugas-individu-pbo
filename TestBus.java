import java.util.Scanner;

public class TestBus {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         Bus transKoetaradja = new Bus();
        int pilihan;
        int penumpangIdCounter = 1;  

        System.out.println("  SIMULASI SISTEM BUS TRANS KOETARADJA V1.0 ");

        do {
             System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Naikkan Penumpang");  
            System.out.println("2. Turunkan Penumpang"); 
            System.out.println("3. Lihat Penumpang & Statistik Bus");  
            System.out.println("4. Keluar Program");
            System.out.print("Pilihan: ");

             try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                pilihan = 0;  
            }

            switch (pilihan) {
                case 1:
                     System.out.println("\n--- Input Data Penumpang ---");
                    
                    System.out.print("Nama Penumpang: ");  
                    String nama = scanner.nextLine(); 

                    System.out.print("Umur (Tahun): "); 
                    int umur;
                    try {
                         umur = Integer.parseInt(scanner.nextLine()); 
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Umur tidak valid. Diatur ke 17 tahun.");
                        umur = 17;
                    }

                    System.out.print("Hamil (y/n)? "); 
                    String hamilInput = scanner.nextLine(); 
                    boolean hamil = hamilInput.trim().equalsIgnoreCase("y");
 
                    Penumpang pBaru = new Penumpang(penumpangIdCounter++, nama, umur, hamil);
                     
                    pBaru.tambahSaldo(0);  

                     transKoetaradja.naikkanPenumpang(pBaru);

                    break;

                case 2:
                     System.out.println("\n--- Turunkan Penumpang ---");
                    System.out.print("Nama Penumpang yang akan turun: ");
                    String namaTurun = scanner.nextLine();
                    
                    transKoetaradja.turunkanPenumpang(namaTurun); 

                    break;

                case 3:
      
                    System.out.println(transKoetaradja.toString());

                    break;
                
                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem Trans Koetaradja. Sampai jumpa!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 4);
        
        scanner.close();
    }
}
