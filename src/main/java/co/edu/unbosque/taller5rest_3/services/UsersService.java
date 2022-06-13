package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.UserApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService {

    private Connection conn;
    
    public UsersService(Connection conn) {this.conn = conn;}

    public List<UserApp> listUsers() {
        Statement stmt = null;

        List<UserApp> usuarioApps = new ArrayList<UserApp>();

        try {
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM userapp";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String role = rs.getString("role");

                usuarioApps.add(new UserApp(email, password, name, role));
            }
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

        return usuarioApps;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "monosusio";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertuser(UserApp user){

        String SQL= "INSERT INTO userapp(email, password, name, role)"+"VALUES(?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1,user.getEmail());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getName());
            pstmt.setString(4,user.getRole());
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