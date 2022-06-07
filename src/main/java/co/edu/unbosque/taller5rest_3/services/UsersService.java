package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService {

    private Connection conn;
    
    public UsersService(Connection conn) {this.conn = conn;}

    public List<Usuario> listUsers() {
        Statement stmt = null;

        List<Usuario> usuarioApps = new ArrayList<Usuario>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Usuario";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                usuarioApps.add(new Usuario(username, password, role));
            }

            // Printing results
            System.out.println("Username | Password | Role");
            for (Usuario usuario : usuarioApps) {
                System.out.println(usuario.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + usuarioApps.size() + "\n");

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

        return usuarioApps;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "monosusio";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertuser(Usuario user){

        String SQL= "INSERT INTO usuario(username, password, role)"+"VALUES(?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1,user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
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