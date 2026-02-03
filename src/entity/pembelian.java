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
public class pembelian {
    private String id_pembelian;
    private Date tanggal;
    private BigDecimal total;
    private String jenis_pengeluaran;
    
    public String getIdPembelian() {
        return id_pembelian;
    }

    public void setIdPembelian(String id_pembelian) {
        this.id_pembelian = id_pembelian;
    }
    
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public String getJenisPengeluaran() {
        return jenis_pengeluaran;
    }

    public void setJenisPengeluaran(String jenis_pengeluaran) {
        this.jenis_pengeluaran = jenis_pengeluaran;
    }
}