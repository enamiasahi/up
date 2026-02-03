/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.pembelian;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import koneksi.koneksi;

/**
 *
 * @author buildAccount
 */
public class pembelianServ {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<pembelian> listPembelian;
    pembelian pembelian;
    
    public pembelianServ(){
        conn = koneksi.getConnection();
    }
    public ArrayList<pembelian> getListPembelian() {
        listPembelian = new ArrayList<pembelian>();
        String query = "SELECT * FROM pembelian ORDER BY id_pembelian";
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                pembelian = new pembelian();
                pembelian.setJenisPengeluaran(rs.getString("jenis_pengeluaran"));
                pembelian.setIdPembelian(rs.getString("id_pembelian"));
                pembelian.setTanggal(rs.getDate("tanggal"));
                pembelian.setTotal(rs.getBigDecimal("total"));
                listPembelian.add(pembelian);
            }
        } catch (SQLException se) {
            System.out.println("Error: "+se);
        }
        return listPembelian;
    }
    public pembelian getPembelian(String id_pembelian) {
        pembelian = new pembelian();
        String qry= "SELECT * FROM pembelian WHERE id_pembelian = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,id_pembelian);
            rs = ps.executeQuery();
            if (rs.next()) {
                pembelian.setJenisPengeluaran(rs.getString("jenis_pengeluaran"));
                pembelian.setIdPembelian(rs.getString("id_pembelian"));
                pembelian.setTanggal(rs.getDate("tanggal"));
                pembelian.setTotal(rs.getBigDecimal("total"));
            }
        } catch(SQLException se) {
            System.out.println("Error : "+se);
        }
        return pembelian;
    }
    
   public boolean insertPembelian(String idPembelian, java.sql.Date tanggal, BigDecimal total) {
    String sql = "INSERT INTO pembelian (id_pembelian, tanggal, total) VALUES (?, ?, ?)";

    try {
        ps = conn.prepareStatement(sql);
        ps.setString(1, idPembelian);
        ps.setDate(2, tanggal);   // tanggal manual dari form
        ps.setBigDecimal(3, total);

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Error insert pembelian: " + e);
        return false;
    }
}


    
    public static void main(String[] args) {
        

    }
}