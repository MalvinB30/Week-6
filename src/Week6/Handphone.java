package Week6;

public class Handphone extends Barang {
    private String warna;
    public static int total = 0;

    public Handphone(int id, String nama, double harga, int stok, String warna) {
        super(id, nama, harga, stok);
        this.warna = warna;
    }

    public String getWarna() { return warna; }
}

