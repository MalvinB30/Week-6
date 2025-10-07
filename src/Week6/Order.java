package Week6;

public class Order {
    private String id;
    private Voucher voucher;
    private Handphone handphone;
    private int jumlah;
    private static int counter = 1;

    public Order(Voucher voucher, int jumlah) {
        this.id = "OV" + counter++;
        this.voucher = voucher;
        this.jumlah = jumlah;
    }

    public Order(Handphone handphone, int jumlah) {
        this.id = "OH" + counter++;
        this.handphone = handphone;
        this.jumlah = jumlah;
    }

    public String getId() { return id; }
    public Voucher getVoucher() { return voucher; }
    public Handphone getHandphone() { return handphone; }
    public int getJumlah() { return jumlah; }
}

