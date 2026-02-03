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
public class produk {
    private String kode_produk;
    private String nama_produk;
    private BigDecimal harga;
    private String jenis;
    
    
    public String getKodeProduk() {
        return kode_produk;
    }

    public void setKodeProduk(String kode_produk) {
        this.kode_produk = kode_produk;
    }
    
    
    public String getNamaProduk() {
        return nama_produk;
    }

    public void setNamaProduk(String nama_produk) {
        this.nama_produk = nama_produk;
    }
    
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
    
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}