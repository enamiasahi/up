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
public class admin {
    private String id_admin;
    private String username;
    private String password;
    private String nama_admin;
    
    
    public String getIdAdmin() {
        return id_admin;
    }

    public void setIdAdmin(String id_admin) {
        this.id_admin = id_admin;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNamaAdmin() {
        return nama_admin;
    }

    public void setNamaAdmin(String nama_admin) {
        this.nama_admin = nama_admin;
    }
}