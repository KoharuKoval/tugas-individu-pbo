import java.util.ArrayList;
import java.util.List;
 
public class Bus {
     private List<Penumpang> penumpangBiasa; 
    private List<Penumpang> penumpangPrioritas; 
    private List<Penumpang> penumpangBerdiri; 
    private final int ONGKOS_BUS = 2000; 
    private int totalPendapatan;  

    private final int MAX_KURSI_BIASA = 16; 
    private final int MAX_KURSI_PRIORITAS = 4;
    private final int MAX_BERDIRI = 20;
    private final int MAX_KAPASITAS = 40;

    public Bus() {

        this.penumpangBiasa = new ArrayList<>(); 
        this.penumpangPrioritas = new ArrayList<>();
        this.penumpangBerdiri = new ArrayList<>();
        this.totalPendapatan = 0;
    }
    

    public List<Penumpang> getPenumpangBiasa() {
        return penumpangBiasa;
    }

    public List<Penumpang> getPenumpangPrioritas() {
        return penumpangPrioritas;
    }

    public List<Penumpang> getPenumpangBerdiri() {
        return penumpangBerdiri;
    }
    
    public int getJumlahPenumpangBiasa() {
        return penumpangBiasa.size();
    }

    public int getJumlahPenumpangPrioritas() {
        return penumpangPrioritas.size();
    }

    public int getJumlahPenumpangBerdiri() {
        return penumpangBerdiri.size();
    }

    public int getTotalPenumpang() {
        return getJumlahPenumpangBiasa() + getJumlahPenumpangPrioritas() + getJumlahPenumpangBerdiri();
    }

    // Method untuk menambah penumpang (NaikPenumpang) [cite: 56]
    public boolean naikkanPenumpang(Penumpang penumpang) {
        // 1. Cek apakah bus sudah penuh [cite: 15]
        if (getTotalPenumpang() >= MAX_KAPASITAS) {
            System.out.println(" Penumpang GAGAL naik. Bus sudah penuh (Kapasitas Maks. 40 orang).");
            return false;
        }

        // 2. Cek Saldo [cite: 58]
        if (penumpang.getSaldo() < ONGKOS_BUS) {
            System.out.println(" Penumpang GAGAL naik. Saldo tidak mencukupi (Ongkos: " + ONGKOS_BUS + "). Saldo: " + penumpang.getSaldo());
            return false;
        }

        // 3. Tentukan tempat duduk/berdiri
        boolean berhasilDuduk = false;
        
        if (penumpang.isPrioritas()) {
            // Penumpang Prioritas [cite: 8]
            
            // Coba kursi prioritas dulu
            if (getJumlahPenumpangPrioritas() < MAX_KURSI_PRIORITAS) {
                penumpangPrioritas.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI PRIORITAS.");
            } 
            // Kalau kursi prioritas penuh, boleh duduk di kursi biasa [cite: 14]
            else if (getJumlahPenumpangBiasa() < MAX_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI BIASA (Kursi prioritas penuh).");
            }
            // Kalau semua kursi penuh, harus berdiri
            else if (getJumlahPenumpangBerdiri() < MAX_BERDIRI) {
                penumpangBerdiri.add(penumpang);
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik, namun HARUS BERDIRI.");
            }
        } else {
            // Penumpang Biasa [cite: 12]
            
            // Dilarang duduk di kursi prioritas [cite: 12]
            // Coba kursi biasa
            if (getJumlahPenumpangBiasa() < MAX_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI BIASA.");
            }
            // Kalau kursi penuh, harus berdiri [cite: 13]
            else if (getJumlahPenumpangBerdiri() < MAX_BERDIRI) {
                penumpangBerdiri.add(penumpang);
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik, namun HARUS BERDIRI.");
            } 
            // Kapasitas sudah penuh dicek di awal
        }
        
        // 4. Proses pembayaran jika berhasil naik
        if (getTotalPenumpang() > 0 && (penumpangBiasa.contains(penumpang) || penumpangPrioritas.contains(penumpang) || penumpangBerdiri.contains(penumpang))) {
            penumpang.kurangiSaldo(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS; // Pendapatan bus ditambah [cite: 59]
            return true;
        }

        return false; // Seharusnya tidak tercapai jika logika di atas benar
    }

    // Method untuk menurunkan penumpang (turunkanPenumpang) 
    public boolean turunkanPenumpang(String nama) {
        Penumpang p;
        // Cari di Penumpang Biasa
        for (int i = 0; i < penumpangBiasa.size(); i++) {
            p = penumpangBiasa.get(i);
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangBiasa.remove(i);
                System.out.println(" hi " + nama + " berhasil turun dari Kursi Biasa.");
                return true;
            }
        }

        // Cari di Penumpang Prioritas
        for (int i = 0; i < penumpangPrioritas.size(); i++) {
            p = penumpangPrioritas.get(i);
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangPrioritas.remove(i);
                System.out.println(" hey " + nama + " berhasil turun dari Kursi Prioritas.");
                return true;
            }
        }
        
        // Cari di Penumpang Berdiri
        for (int i = 0; i < penumpangBerdiri.size(); i++) {
            p = penumpangBerdiri.get(i);
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangBerdiri.remove(i);
                System.out.println(" hai " + nama + " berhasil turun dari Penumpang Berdiri.");
                return true;
            }
        }

        // Penumpang tidak ditemukan [cite: 62]
        System.out.println(" Penumpang dengan nama " + nama + " TIDAK ditemukan!");
        return false;
    }
