package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.DTO.UserApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionService {

    private Connection conn;

    public CollectionService(Connection conn) {this.conn = conn;}

    public List<Collection> collectionList() {
        Statement stmt = null;

        List<Collection> collections = new ArrayList<Collection>();

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM collection";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Integer co_id = rs.getInt("co_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                String email = rs.getString("email");

                collections.add(new Collection(co_id, name,description,category,email));
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

        return collections;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "monosusio";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void insertCollection(Collection coleccion){

        String SQL= "INSERT INTO collection(co_id, name, description, category, email)"+"VALUES(?,?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1,coleccion.getCo_id());
            pstmt.setString(2,coleccion.getName());
            pstmt.setString(3,coleccion.getDescription());
            pstmt.setString(4,coleccion.getCategory());
            pstmt.setString(5,coleccion.getEmail());
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

    }

}
