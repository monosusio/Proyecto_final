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
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM collection";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                //Integer co_id = rs.getInt("co_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                String email = rs.getString("email");

                // Creating a new UserApp class instance and adding it to the array list
                collections.add(new Collection(name,description,category,email));
            }

            // Printing results
            System.out.println("email | Password | name | role ");
            for (Collection collecion : collections) {
                System.out.println(collecion.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + collections.size() + "\n");

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

        return collections;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "minicraftteo";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertCollection(Collection coleccion){

        String SQL= "INSERT INTO collection(name, description, category, email)"+"VALUES(?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            //pstmt.setInt(1,coleccion.getCo_id());
            pstmt.setString(1,coleccion.getName());
            pstmt.setString(2,coleccion.getDescription());
            pstmt.setString(3,coleccion.getCategory());
            pstmt.setString(4,coleccion.getEmail());
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
