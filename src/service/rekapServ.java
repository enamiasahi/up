package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class rekapServ {

    private Connection conn;

    public rekapServ() {
        conn = koneksi.getConnection();
    }

    // ================= PEMBELIAN =================

    public void rekapPembelianHarian(DefaultTableModel model) {
    String sql =
        "SELECT DATE(tanggal) AS periode, SUM(total) AS total " +
        "FROM pembelian " +
        "GROUP BY DATE(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}


    public void rekapPembelianBulanan(DefaultTableModel model) {
    String sql =
        "SELECT DATE_FORMAT(tanggal, '%Y-%m') AS periode, SUM(total) AS total " +
        "FROM pembelian " +
        "GROUP BY YEAR(tanggal), MONTH(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}


    public void rekapPembelianTahunan(DefaultTableModel model) {
    String sql =
        "SELECT YEAR(tanggal) AS periode, SUM(total) AS total " +
        "FROM pembelian " +
        "GROUP BY YEAR(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}


    // ================= PENJUALAN =================

    public void rekapPenjualanHarian(DefaultTableModel model) {
    String sql =
        "SELECT DATE(tanggal) AS periode, SUM(total) AS total " +
        "FROM penjualan " +
        "GROUP BY DATE(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}


    public void rekapPenjualanBulanan(DefaultTableModel model) {
    String sql =
        "SELECT DATE_FORMAT(tanggal, '%Y-%m') AS periode, SUM(total) AS total " +
        "FROM penjualan " +
        "GROUP BY YEAR(tanggal), MONTH(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}


    public void rekapPenjualanTahunan(DefaultTableModel model) {
    String sql =
        "SELECT YEAR(tanggal) AS periode, SUM(total) AS total " +
        "FROM penjualan " +
        "GROUP BY YEAR(tanggal) " +
        "ORDER BY periode DESC";

    execute(sql, model);
}

    // ================= EXECUTOR =================

    private void execute(String sql, DefaultTableModel model) {
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("periode"),
                    rs.getBigDecimal("total")
                });
            }

        } catch (Exception e) {
            System.out.println("Error rekap: " + e);
        }
    }
}
