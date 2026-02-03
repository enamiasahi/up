/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author buildAccount
 */
public class detail_penjualan {
    private String id_detail;
    private String id_penjualan;
    private Integer jumlah;
    private BigDecimal harga;
    private String kode_produk;
    
    public String getIdDetail() {
        return id_detail;
    }

    public void setIdDetail(String id_detail) {
        this.id_detail = id_detail;
    }
    
    public String getIdPenjualan() {
        return id_penjualan;
    }

    public void setIdPenjualan(String id_penjualan) {
        this.id_penjualan = id_penjualan;
    }
    
    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    public String getKodeProduk() {
        return kode_produk;
    }

    public void setKodeProduk(String kode_produk) {
        this.kode_produk = kode_produk;
    }
}