/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.admin;
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
public class adminServ {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<admin> listAdmin;
    admin admin;
    
    public adminServ(){
        conn = koneksi.getConnection();
    }
    public ArrayList<admin> getListAdmin() {
        listAdmin = new ArrayList<admin>();
        String query = "SELECT * FROM admin ORDER BY id_admin";
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                admin = new admin();
                admin.setIdAdmin(rs.getString("id_admin"));
                admin.setNamaAdmin(rs.getString("nama_admin"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                listAdmin.add(admin);
            }
        } catch (SQLException se) {
            System.out.println("Error: "+se);
        }
        return listAdmin;
    }
    public admin getAdmin(String id_admin) {
        admin = new admin();
        String qry= "SELECT * FROM admin WHERE id_admin = ?";
        try {
            ps = conn.prepareStatement(qry);
            ps.setString(1,id_admin);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new admin();
                admin.setIdAdmin(rs.getString("id_admin"));
                admin.setNamaAdmin(rs.getString("nama_admin"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
            }
        } catch(SQLException se) {
            System.out.println("Error : "+se);
        }
        return admin;
    }
    
    public admin login(String username, String password) {
    admin adm = null;
    String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

    try {
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) {
            adm = new admin();
            adm.setIdAdmin(rs.getString("id_admin"));
            adm.setNamaAdmin(rs.getString("nama_admin"));
            adm.setUsername(rs.getString("username"));
            adm.setPassword(rs.getString("password"));
        }
    } catch (SQLException e) {
        System.out.println("Login Error: " + e);
    }

    return adm;
}

    
    public static void main(String[] args) {
        

    }
}