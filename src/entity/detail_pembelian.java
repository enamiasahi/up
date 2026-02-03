package entity;

import java.math.BigDecimal;

public class detail_pembelian {

    private String id_detail;
    private String id_pembelian;
    private String nama_item;
    private int jumlah;
    private BigDecimal harga;
    private String kode_produk;
    private String jenis_pengeluaran;
    private String keterangan;

    // ===== GETTER & SETTER =====

    public String getId_detail() {
        return id_detail;
    }

    public void setId_detail(String id_detail) {
        this.id_detail = id_detail;
    }

    public String getId_pembelian() {
        return id_pembelian;
    }

    public void setId_pembelian(String id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public String getNama_item() {
        return nama_item;
    }

    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getJenis_pengeluaran() {
        return jenis_pengeluaran;
    }

    public void setJenis_pengeluaran(String jenis_pengeluaran) {
        this.jenis_pengeluaran = jenis_pengeluaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
