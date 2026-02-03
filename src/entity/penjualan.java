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
public class penjualan {
    private String id_penjualan;
    private Date tanggal;
    private BigDecimal total;
    private String id_admin;
    
    public String getIdPenuualan() {
        return id_penjualan;
    }

    public void setIdPenjualan(String id_penjualan) {
        this.id_penjualan = id_penjualan;
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
    
    public String getIdADmin() {
        return id_admin;
    }

    public void setIdAdmin(String id_admin) {
        this.id_admin = id_admin;
    }
}