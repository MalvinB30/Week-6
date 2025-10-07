package Week6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Order> daftarPesanan = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int menu;
        do {
            System.out.println("\n----------- Menu Toko Voucher & HP -----------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");

            while (!in.hasNextInt()) {
                System.out.println("Masukkan angka 0-3!");
                in.next();
                System.out.print("Pilihan : ");
            }
            menu = in.nextInt();

            switch (menu) {
                case 1 -> pesanBarang();
                case 2 -> lihatPesanan();
                case 3 -> barangBaru();
                case 0 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan salah!");
            }
        } while (menu != 0);
    }

    static void barangBaru() {
        System.out.print("Voucher / Handphone (V/H): ");
        char jenis = in.next().toLowerCase().charAt(0);
        in.nextLine(); // bersihkan newline

        System.out.print("Nama : ");
        String nama = in.nextLine();

        double harga = 0;
        while (true) {
            try {
                System.out.print("Harga : ");
                harga = in.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                in.nextLine();
            }
        }

        System.out.print("Stok : ");
        while (!in.hasNextInt()) {
            System.out.println("Masukkan angka bulat untuk stok!");
            in.next();
        }
        int stok = in.nextInt();

        if (jenis == 'v') {
            System.out.print("PPN : ");
            while (!in.hasNextDouble()) {
                System.out.println("Masukkan angka desimal untuk PPN (contoh: 0.1)");
                in.next();
            }
            double ppn = in.nextDouble();
            daftarBarang.add(new Voucher(daftarBarang.size() + 1, nama, harga, stok, ppn));
            System.out.println("Voucher telah berhasil diinput.");
        } else if (jenis == 'h') {
            in.nextLine();
            System.out.print("Warna : ");
            String warna = in.nextLine();
            daftarBarang.add(new Handphone(daftarBarang.size() + 1, nama, harga, stok, warna));
            System.out.println("Handphone telah berhasil diinput.");
        } else {
            System.out.println("Input tidak valid (gunakan 'v' atau 'h').");
        }
    }

    static void pesanBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang tersedia!");
            return;
        }

        System.out.println("\nDaftar Barang Toko Voucher & HP");
        for (Barang b : daftarBarang) {
            System.out.println("ID : " + b.getId() + " | Nama : " + b.getNama() +
                    " | Stok : " + b.getStok() + " | Harga : " + b.getHarga());
        }

        System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
        while (!in.hasNextInt()) {
            System.out.println("Masukkan angka ID yang benar!");
            in.next();
        }
        int pilih = in.nextInt();
        if (pilih == 0) return;

        Barang barang = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == pilih) barang = b;
        }

        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan Jumlah : ");
        while (!in.hasNextInt()) {
            System.out.println("Masukkan angka bulat untuk jumlah!");
            in.next();
        }
        int jumlah = in.nextInt();

        if (jumlah > barang.getStok()) {
            System.out.println("Stok tidak mencukupi jumlah pesanan.");
            return;
        }

        double total = (barang instanceof Voucher)
                ? ((Voucher) barang).getHargaJual() * jumlah
                : barang.getHarga() * jumlah;

        System.out.println(jumlah + " @ " + barang.getNama() + " total harga " + total);

        System.out.print("Masukkan jumlah uang : ");
        while (!in.hasNextDouble()) {
            System.out.println("Masukkan angka untuk uang!");
            in.next();
        }
        double uang = in.nextDouble();

        if (uang < total) {
            System.out.println("Jumlah uang tidak mencukupi!");
            return;
        }

        barang.minusStok(jumlah);

        if (barang instanceof Voucher) {
            daftarPesanan.add(new Order((Voucher) barang, jumlah));
        } else {
            daftarPesanan.add(new Order((Handphone) barang, jumlah));
        }


        System.out.println("Pesanan berhasil dibuat!");
    }

    static void lihatPesanan() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        System.out.println("\nDaftar Pesanan Toko Multiguna");
        for (Order o : daftarPesanan) {
            if (o.getHandphone() != null) {
                System.out.println("ID : " + o.getId() + " | " + o.getHandphone().getNama() +
                        " | Jumlah : " + o.getJumlah() +
                        " | Total : " + (o.getHandphone().getHarga() * o.getJumlah()));
            } else if (o.getVoucher() != null) {
                System.out.println("ID : " + o.getId() + " | " + o.getVoucher().getNama() +
                        " | Jumlah : " + o.getJumlah() +
                        " | Total : " + (o.getVoucher().getHargaJual() * o.getJumlah()));
            }
        }
    }
}
