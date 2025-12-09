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

     public boolean naikkanPenumpang(Penumpang penumpang) {
         if (getTotalPenumpang() >= MAX_KAPASITAS) {
            System.out.println(" Penumpang GAGAL naik. Bus sudah penuh (Kapasitas Maks. 40 orang).");
            return false;
        }

         if (penumpang.getSaldo() < ONGKOS_BUS) {
            System.out.println(" Penumpang GAGAL naik. Saldo tidak mencukupi (Ongkos: " + ONGKOS_BUS + "). Saldo: " + penumpang.getSaldo());
            return false;
        }

         boolean berhasilDuduk = false;
        
        if (penumpang.isPrioritas()) {
             
             if (getJumlahPenumpangPrioritas() < MAX_KURSI_PRIORITAS) {
                penumpangPrioritas.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI PRIORITAS.");
            } 
             else if (getJumlahPenumpangBiasa() < MAX_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI BIASA (Kursi prioritas penuh).");
            }
             else if (getJumlahPenumpangBerdiri() < MAX_BERDIRI) {
                penumpangBerdiri.add(penumpang);
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik, namun HARUS BERDIRI.");
            }
        } else {
      
            if (getJumlahPenumpangBiasa() < MAX_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
                berhasilDuduk = true;
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik dan duduk di KURSI BIASA.");
            }
             else if (getJumlahPenumpangBerdiri() < MAX_BERDIRI) {
                penumpangBerdiri.add(penumpang);
                System.out.println(" ~ " + penumpang.getNama() + " berhasil naik, namun HARUS BERDIRI.");
            } 
         }
        
         if (getTotalPenumpang() > 0 && (penumpangBiasa.contains(penumpang) || penumpangPrioritas.contains(penumpang) || penumpangBerdiri.contains(penumpang))) {
            penumpang.kurangiSaldo(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;  
            return true;
        }

        return false;  
    }

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

         for (int i = 0; i < penumpangPrioritas.size(); i++) {
            p = penumpangPrioritas.get(i);
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangPrioritas.remove(i);
                System.out.println(" hey " + nama + " berhasil turun dari Kursi Prioritas.");
                return true;
            }
        }
        
         for (int i = 0; i < penumpangBerdiri.size(); i++) {
            p = penumpangBerdiri.get(i);
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangBerdiri.remove(i);
                System.out.println(" hai " + nama + " berhasil turun dari Penumpang Berdiri.");
                return true;
            }
        }

        System.out.println(" Penumpang dengan nama " + nama + " TIDAK ditemukan!");
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n=== STATISTIK BUS TRANS KOETARADJA ===\n");
        
        sb.append("Penumpang Biasa (Kursi: ").append(getJumlahPenumpangBiasa()).append("/").append(MAX_KURSI_BIASA).append("): ");
        if (penumpangBiasa.isEmpty()) {
            sb.append("<kosong>\n");
        } else {
            for (Penumpang p : penumpangBiasa) {
                sb.append(p.getNama()).append(", ");
            }
            sb.setLength(sb.length() - 2); 
            sb.append("\n");
        }

        sb.append("Penumpang Prioritas (Kursi: ").append(getJumlahPenumpangPrioritas()).append("/").append(MAX_KURSI_PRIORITAS).append("): ");
        if (penumpangPrioritas.isEmpty()) {
            sb.append("<kosong>\n");
        } else {
            for (Penumpang p : penumpangPrioritas) {
                sb.append(p.getNama()).append(", ");
            }
            sb.setLength(sb.length() - 2); 
            sb.append("\n");
        }

  
        sb.append("Penumpang Berdiri (Maks. ").append(MAX_BERDIRI).append("): ");
        if (penumpangBerdiri.isEmpty()) {
            sb.append("<kosong>\n");
        } else {
            for (Penumpang p : penumpangBerdiri) {
                sb.append(p.getNama()).append(", ");
            }
            sb.setLength(sb.length() - 2); 
            sb.append("\n");
        }
        
        sb.append("\n----------------------------------------\n");
        sb.append("Total Jumlah Penumpang: ").append(getTotalPenumpang()).append(" / ").append(MAX_KAPASITAS).append("\n");
        sb.append("Total Pendapatan Bus: ").append(totalPendapatan).append(" IDR\n");
        sb.append("========================================\n");
        
        return sb.toString();
    }
}
