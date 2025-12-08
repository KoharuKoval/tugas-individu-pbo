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
