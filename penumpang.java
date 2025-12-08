import java.util.Objects;


public class Penumpang {

    private int id; 
    private String nama; 
    private int umur;
    private boolean hamil;
    private int saldo;

    public Penumpang(int id, String nama, int umur, boolean hamil) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = 10000; 
    }
public int getID() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public boolean isHamil() {
        return hamil; 
    }

    public int getSaldo() {
        return saldo;
    }

    
    public boolean isPrioritas() {

        return umur > 60 || umur < 10 || hamil;
    }

    public void tambahSaldo(int saldobaru) {
        this.saldo += saldobaru; 
    }
