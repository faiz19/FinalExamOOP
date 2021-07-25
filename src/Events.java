import java.io.Serializable;
import java.util.ArrayList;

public class Events implements Serializable {
    private String nama;
    private String pembuat;
    private String jenisOlahraga;
    private String level;
    private int tanggal;
    private int bulan;
    private int tahun;
    private String tempat;
    private int jumlahMin;
    private int jumlahMax;
    //counter jumlah
    private int jumlahSekarang = 0;
    private ArrayList<String> pemain = new ArrayList<>();
    private int biaya;
    //mau batal atau engga atau selesai
    private boolean status= true;
    private boolean penuh=false;

    public Events(String nama, String pembuat, String jenisOlahraga, String level, int tanggal, int bulan, int tahun, String tempat, int jumlahMin, int jumlahMax, int biaya){
        this.nama = nama;
        this.pembuat = pembuat;
        this.jenisOlahraga = jenisOlahraga;
        this.level = level;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.tempat = tempat;
        this.jumlahMin = jumlahMin;
        this.jumlahMax = jumlahMax;
        this.biaya = biaya;
    }

    public String getPembuat() {
        return pembuat;
    }

    public String getNama() {
        return nama;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getJenisOlahraga() {
        return jenisOlahraga;
    }

    public String getLevel() {
        return level;
    }

    public int getTanggal() {
        return tanggal;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public String getTempat() {
        return tempat;
    }

    public int getJumlahMin() {
        return jumlahMin;
    }

    public int getJumlahMax() {
        return jumlahMax;
    }

    public int getJumlahSekarang() {
        return jumlahSekarang;
    }

    public int getBiaya() {
        return biaya;
    }

    public boolean isStatus() {
        return status;
    }

    public void TambahPemain (String username){
        jumlahSekarang = jumlahSekarang+1;
        if (jumlahMax==jumlahSekarang){
            penuh=true;
        }
        pemain.add(username);
    }

    public void KurangiPemain (String username) {
        jumlahSekarang = jumlahSekarang-1;
        penuh = false;
        pemain.remove(new String(username));
    }

    public ArrayList<String> getPemain() {
        return pemain;
    }

    public boolean isPenuh() {
        return penuh;
    }
}
