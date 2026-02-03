/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.produk;
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
public class produkServ {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<produk> listProduk;
    produk produk;
    
    public produkServ(){
        conn = koneksi.getConnection();
    }
    public ArrayList<produk> getListProduk() {
        listProduk = new ArrayList<produk>();
        String query = "SELECT * FROM produk ORDER BY kode_produk";
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                produk = new produk();
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setHarga(rs.getBigDecimal("harga"));
                produk.setJenis(rs.getString("jenis"));
                listProduk.add(produk);
            }
        } catch (SQLException se) {
            System.out.println("Error: "+se);
        }
        return listProduk;
    }
    public produk getProduk(String kode_produk) {
        produk = new produk();
        String qry= "SELECT * FROM produk WHERE kode_produk = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,kode_produk);
            rs = ps.executeQuery();
            if (rs.next()) {
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setHarga(rs.getBigDecimal("harga"));
                produk.setJenis(rs.getString("jenis"));
            }
        } catch(SQLException se) {
            System.out.println("Error : "+se);
        }
        return produk;
    }
    
    public void simpan(produk p) {
    String qry;

    // cek data lama
    produk cek = getProduk(p.getKodeProduk());

        if (cek.getKodeProduk() != null) {
            // UPDATE
            qry = "UPDATE produk SET nama_produk=?, harga=?, jenis=? WHERE kode_produk=?";
        } else {
            // INSERT
            qry = "INSERT INTO produk (kode_produk, nama_produk, harga, jenis) VALUES (?,?,?,?)";
        }

        try {
            ps = conn.prepareStatement(qry);

            if (cek.getKodeProduk() != null) {
                // UPDATE
                ps.setString(1, p.getNamaProduk());
                ps.setBigDecimal(2, p.getHarga());
                ps.setString(3, p.getJenis());
                ps.setString(4, p.getKodeProduk());
            } else {
                // INSERT
                ps.setString(1, p.getKodeProduk());
                ps.setString(2, p.getNamaProduk());
                ps.setBigDecimal(3, p.getHarga());
                ps.setString(4, p.getJenis());
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error simpan produk: " + e);
        }
    }
    
    public void hapus(String kode_produk) {
        try {
            ps = conn.prepareStatement("delete from produk where kode_produk=?");
            ps.setString(1, kode_produk);
            ps.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Error : "+ se);
        }
    }

    
    public static void main(String[] args) {
        

    }
}