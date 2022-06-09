package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.DTO.WalletHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletHistoryService {

    private Connection conn;

    public WalletHistoryService(Connection conn) {this.conn = conn;}

    public List<WalletHistory> walletHistoryList() {
        Statement stmt = null;

        List<WalletHistory> walletHistory = new ArrayList<WalletHistory>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM wallethistory";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                //Integer co_id = rs.getInt("co_id");
                String email = rs.getString("email");
                String type = rs.getString("type");
                float fcoins = rs.getFloat("fcoins");
                String registeredAt = rs.getString("registeredAt");

                // Creating a new UserApp class instance and adding it to the array list
                walletHistory.add(new WalletHistory(email,type,fcoins,registeredAt));
            }

            // Printing results
            System.out.println("email | Password | name | role ");
            for (WalletHistory walletHistory2 : walletHistory) {
                System.out.println(walletHistory2.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + walletHistory.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return walletHistory;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "monosusio";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertWalletHistory(WalletHistory walletHistory){

        String SQL= "INSERT INTO wallethistory(email, type, fcoins, registeredAt)"+"VALUES(?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            //pstmt.setInt(1,coleccion.getCo_id());
            pstmt.setString(1,walletHistory.getEmail());
            pstmt.setString(2,walletHistory.getType());
            pstmt.setFloat(3,walletHistory.getFcoins());
            pstmt.setString(4,walletHistory.getRegisteredAt());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;

    }
}
