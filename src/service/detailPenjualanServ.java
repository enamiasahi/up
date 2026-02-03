package service;

import entity.detail_penjualan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import koneksi.koneksi;
import java.math.BigDecimal;

public class detailPenjualanServ {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public detailPenjualanServ() {
        conn = koneksi.getConnection();
    }

    // =====================================================================
    // GET ALL DETAIL PENJUALAN
    // =====================================================================
    public ArrayList<detail_penjualan> getListDetailPenjualan() {
        ArrayList<detail_penjualan> list = new ArrayList<>();

        String query = "SELECT * FROM detail_penjualan ORDER BY id_detail";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                detail_penjualan dp = new detail_penjualan();

                dp.setIdDetail(rs.getString("id_detail"));
                dp.setIdPenjualan(rs.getString("id_penjualan"));
                dp.setJumlah(rs.getInt("jumlah"));
                dp.setHarga(rs.getBigDecimal("harga"));
                dp.setKodeProduk(rs.getString("kode_produk"));

                list.add(dp);
            }

        } catch (SQLException e) {
            System.out.println("Error getListDetailPenjualan: " + e);
        }
        return list;
    }

    // =====================================================================
    // GET DETAIL BY ID
    // =====================================================================
    public detail_penjualan getDetailPenjualan(String id_detail) {

        detail_penjualan dp = null;
        String sql = "SELECT * FROM detail_penjualan WHERE id_detail = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id_detail);
            rs = ps.executeQuery();

            if (rs.next()) {
                dp = new detail_penjualan();

                dp.setIdDetail(rs.getString("id_detail"));
                dp.setIdPenjualan(rs.getString("id_penjualan"));
                dp.setJumlah(rs.getInt("jumlah"));
                dp.setHarga(rs.getBigDecimal("harga"));
                dp.setKodeProduk(rs.getString("kode_produk"));
            }

        } catch (SQLException e) {
            System.out.println("Error getDetailPenjualan: " + e);
        }

        return dp;
    }
// =====================================================================
// GENERATE ID DETAIL PENJUALAN (DJ0001)
// =====================================================================
public String generateIdDetail() {
    String newId = "DJ0001";

    try {
        String sql = "SELECT MAX(id_detail) AS maxId FROM detail_penjualan";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next() && rs.getString("maxId") != null) {
            String maxId = rs.getString("maxId").substring(2); // buang "DJ"
            int next = Integer.parseInt(maxId) + 1;
            newId = String.format("DJ%04d", next);
        }

    } catch (Exception e) {
        System.out.println("Error generateIdDetail: " + e);
    }

    return newId;
}



    // =====================================================================
    // GENERATE ID PENJUALAN (PJ0001)
    // =====================================================================
    public String generateIdPenjualan() {
        String newId = "PJ0001";

        try {
            String sql = "SELECT MAX(id_penjualan) AS maxId FROM penjualan";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next() && rs.getString("maxId") != null) {

                String maxId = rs.getString("maxId").substring(2); // buang PJ
                int next = Integer.parseInt(maxId) + 1;

                newId = String.format("PJ%04d", next);
            }

        } catch (Exception e) {
            System.out.println("Error generateIdPenjualan: " + e);
        }

        return newId;
    }
// =====================================================================
// INSERT DETAIL PENJUALAN
// =====================================================================
public boolean insert(detail_penjualan dp) {
    String sql = "INSERT INTO detail_penjualan (id_detail, id_penjualan, kode_produk, harga, jumlah) VALUES (?,?,?,?,?)";

    try {
        ps = conn.prepareStatement(sql);
        ps.setString(1, dp.getIdDetail());
        ps.setString(2, dp.getIdPenjualan());
        ps.setString(3, dp.getKodeProduk());
        ps.setBigDecimal(4, dp.getHarga());
        ps.setInt(5, dp.getJumlah());

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Error insert detail penjualan: " + e);
        return false;
    }
}


   public boolean insertBatch(ArrayList<detail_penjualan> list) {
    String sql = "INSERT INTO detail_penjualan (id_detail, id_penjualan, kode_produk, harga, jumlah) VALUES (?,?,?,?,?)";

    try {
        ps = conn.prepareStatement(sql);

        for (detail_penjualan dp : list) {
            ps.setString(1, dp.getIdDetail());
            ps.setString(2, dp.getIdPenjualan());
            ps.setString(3, dp.getKodeProduk());
            ps.setBigDecimal(4, dp.getHarga());
            ps.setInt(5, dp.getJumlah());
            ps.addBatch();
        }

        ps.executeBatch();
        return true;

    } catch (SQLException e) {
        System.out.println("Error insertBatch detail penjualan: " + e);
        return false;
    }
}

}
