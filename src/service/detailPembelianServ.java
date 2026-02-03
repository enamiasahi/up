package service;

import entity.detail_pembelian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import koneksi.koneksi;
import java.math.BigDecimal;

public class detailPembelianServ {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public detailPembelianServ() {
        conn = koneksi.getConnection();
    }

    // =====================================================================
    // GET ALL DETAIL PEMBELIAN
    // =====================================================================
    public ArrayList<detail_pembelian> getListDetailPembelian() {
        ArrayList<detail_pembelian> list = new ArrayList<>();

        String query = "SELECT * FROM detail_pembelian ORDER BY id_detail";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                detail_pembelian dp = new detail_pembelian();

                dp.setId_detail(rs.getString("id_detail"));
                dp.setId_pembelian(rs.getString("id_pembelian"));
                dp.setNama_item(rs.getString("nama_item"));
                dp.setJumlah(rs.getInt("jumlah"));
                dp.setHarga(rs.getBigDecimal("harga"));
                dp.setKode_produk(rs.getString("kode_produk"));
                dp.setJenis_pengeluaran(rs.getString("jenis_pengeluaran"));
                dp.setKeterangan(rs.getString("keterangan"));

                list.add(dp);
            }

        } catch (SQLException e) {
            System.out.println("Error getListDetailPembelian: " + e);
        }
        return list;
    }

    // =====================================================================
    // GET DETAIL BY ID
    // =====================================================================
    public detail_pembelian getDetailPembelian(String id_detail) {

        detail_pembelian dp = null;
        String sql = "SELECT * FROM detail_pembelian WHERE id_detail = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id_detail);
            rs = ps.executeQuery();

            if (rs.next()) {
                dp = new detail_pembelian();

                dp.setId_detail(rs.getString("id_detail"));
                dp.setId_pembelian(rs.getString("id_pembelian"));
                dp.setNama_item(rs.getString("nama_item"));
                dp.setJumlah(rs.getInt("jumlah"));
                dp.setHarga(rs.getBigDecimal("harga"));
                dp.setKode_produk(rs.getString("kode_produk"));
                dp.setJenis_pengeluaran(rs.getString("jenis_pengeluaran"));
                dp.setKeterangan(rs.getString("keterangan"));
            }

        } catch (SQLException e) {
            System.out.println("Error getDetailPembelian: " + e);
        }

        return dp;
    }

    // =====================================================================
    // GENERATE ID DETAIL (DT0001)
    // =====================================================================
    public String generateIdDetail() {
        String newId = "DT0001";

        try {
            String sql = "SELECT MAX(id_detail) AS maxId FROM detail_pembelian";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next() && rs.getString("maxId") != null) {

                String maxId = rs.getString("maxId").substring(2); // remove "DT"
                int next = Integer.parseInt(maxId) + 1;

                newId = String.format("DT%04d", next);
            }

        } catch (Exception e) {
            System.out.println("Error generateIdDetail: " + e);
        }

        return newId;
    }
    
    public String generateIdPembelian() {
    String newId = "PB0001";

    try {
        String sql = "SELECT MAX(id_pembelian) AS maxId FROM pembelian";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next() && rs.getString("maxId") != null) {
            String maxId = rs.getString("maxId").substring(2); // buang PB
            int next = Integer.parseInt(maxId) + 1;
            newId = String.format("PB%04d", next);
        }

    } catch (Exception e) {
        System.out.println("Error generateIdPembelian: " + e);
    }

    return newId;
}



    // =====================================================================
    // INSERT DETAIL PEMBELIAN (Single Row)
    // =====================================================================
    public boolean insert(detail_pembelian dp) {
    String sql = "INSERT INTO detail_pembelian "
            + "(id_detail, id_pembelian, nama_item, jumlah, harga, kode_produk, jenis_pengeluaran, keterangan) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        ps = conn.prepareStatement(sql);
        ps.setString(1, dp.getId_detail());
        ps.setString(2, dp.getId_pembelian());
        ps.setString(3, dp.getNama_item());
        ps.setInt(4, dp.getJumlah());
        ps.setBigDecimal(5, dp.getHarga());

        // kode_produk boleh NULL
        if (dp.getKode_produk() == null || dp.getKode_produk().isEmpty()) {
            ps.setNull(6, java.sql.Types.VARCHAR);
        } else {
            ps.setString(6, dp.getKode_produk());
        }

        ps.setString(7, dp.getJenis_pengeluaran());
        ps.setString(8, dp.getKeterangan());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error insert detail pembelian: " + e);
        return false;
    }
}


    // =====================================================================
    // INSERT BATCH (Untuk JTable)
    // =====================================================================
    public boolean insertBatch(ArrayList<detail_pembelian> listDetail) {

    String sql = "INSERT INTO detail_pembelian "
            + "(id_detail, id_pembelian, nama_item, jumlah, harga, kode_produk, jenis_pengeluaran, keterangan) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        conn.setAutoCommit(false);
        ps = conn.prepareStatement(sql);

        for (detail_pembelian dp : listDetail) {
            ps.setString(1, dp.getId_detail());
            ps.setString(2, dp.getId_pembelian());
            ps.setString(3, dp.getNama_item());
            ps.setInt(4, dp.getJumlah());
            ps.setBigDecimal(5, dp.getHarga());

            // NULL handling
            if (dp.getKode_produk() == null) {
    ps.setNull(6, java.sql.Types.VARCHAR);
} else {
    ps.setString(6, dp.getKode_produk());
}


            ps.setString(7, dp.getJenis_pengeluaran());
            ps.setString(8, dp.getKeterangan());

            ps.addBatch();
        }

        ps.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        return true;

    } catch (SQLException e) {
        System.out.println("Error insertBatch detail pembelian: " + e);
        try {
            conn.rollback();
        } catch (SQLException ex) {}
    }
    return false;
}

}
