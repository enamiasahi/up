package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import koneksi.koneksi;

public class DashboardServ {

    private Connection conn;

    public DashboardServ() {
        conn = koneksi.getConnection();
    }

    // ================= TOTAL PEMASUKAN =================
    public BigDecimal getTotalPemasukan() {
        String sql = "SELECT IFNULL(SUM(total),0) FROM penjualan";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getBigDecimal(1);

        } catch (Exception e) {
            System.out.println("Error total pemasukan: " + e);
        }
        return BigDecimal.ZERO;
    }

    // ================= TOTAL PENGELUARAN =================
    public BigDecimal getTotalPengeluaran() {
        String sql = "SELECT IFNULL(SUM(total),0) FROM pembelian";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getBigDecimal(1);

        } catch (Exception e) {
            System.out.println("Error total pengeluaran: " + e);
        }
        return BigDecimal.ZERO;
    }

    // ================= TOTAL BARANG TERJUAL =================
    public int getTotalBarangTerjual() {
        String sql = "SELECT IFNULL(SUM(jumlah),0) FROM detail_penjualan";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            System.out.println("Error barang terjual: " + e);
        }
        return 0;
    }

    // ================= 5 TRANSAKSI TERAKHIR =================
    public ArrayList<Object[]> getLastTransactions() {
        ArrayList<Object[]> list = new ArrayList<>();

        String sql =
            "SELECT tanggal, 'Penjualan' AS jenis, total FROM penjualan " +
            "UNION ALL " +
            "SELECT tanggal, 'Pembelian' AS jenis, total FROM pembelian " +
            "ORDER BY tanggal DESC " +
            "LIMIT 5";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getDate("tanggal"),
                    rs.getString("jenis"),
                    rs.getBigDecimal("total")
                });
            }

        } catch (Exception e) {
            System.out.println("Error transaksi terakhir: " + e);
        }
        return list;
    }
}
