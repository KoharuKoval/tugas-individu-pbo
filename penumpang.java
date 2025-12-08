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
