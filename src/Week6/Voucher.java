package Week6;

public class Voucher extends Barang {
    private double pajak;
    public static int total = 0;

    public Voucher(int id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga, stok);
        this.pajak = pajak;
    }

    public double getHargaJual() {
        return harga + (harga * pajak);
    }
}

