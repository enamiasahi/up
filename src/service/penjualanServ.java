/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.penjualan;
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
public class penjualanServ {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<penjualan> listPenjualan;
    penjualan penjualan;
    
    public penjualanServ(){
        conn = koneksi.getConnection();
    }
    public ArrayList<penjualan> getListPenjualan() {
        listPenjualan = new ArrayList<penjualan>();
        String query = "SELECT * FROM penjualan ORDER BY id_penjualan";
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                penjualan = new penjualan();
                penjualan.setIdPenjualan(rs.getString("id_penjualan"));
                penjualan.setTanggal(rs.getDate("tanggal"));
                penjualan.setTotal(rs.getBigDecimal("total"));
                listPenjualan.add(penjualan);
            }
        } catch (SQLException se) {
            System.out.println("Error: "+se);
        }
        return listPenjualan;
    }
    public penjualan getPembelian(String id_penjualan) {
        penjualan = new penjualan();
        String qry= "SELECT * FROM penjualan WHERE id_penjualan = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,id_penjualan);
            rs = ps.executeQuery();
            if (rs.next()) {
                penjualan.setIdPenjualan(rs.getString("id_penjualan"));
                penjualan.setTanggal(rs.getDate("tanggal"));
                penjualan.setTotal(rs.getBigDecimal("total"));
            }
        } catch(SQLException se) {
            System.out.println("Error : "+se);
        }
        return penjualan;
    }
    
    public boolean insert(String idPenjualan, String tanggal, BigDecimal total) {
    String sql = "INSERT INTO penjualan(id_penjualan, tanggal, total) VALUES(?,?,?)";
    try {
        ps = conn.prepareStatement(sql);
        ps.setString(1, idPenjualan);
        ps.setDate(2, Date.valueOf(tanggal));
        ps.setBigDecimal(3, total);
        ps.executeUpdate();
        return true;
    } catch (Exception e) {
        System.out.println("Error insert penjualan: " + e);
        return false;
    }
}

    public static void main(String[] args) {
        

    }
}