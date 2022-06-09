package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.Art;
import co.edu.unbosque.taller5rest_3.DTO.Collection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtService {

    private Connection conn;

    public ArtService(Connection conn) {this.conn = conn;}

    public List<Art> ArtList() {
        Statement stmt = null;

        List<Art> arts = new ArrayList<Art>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM art";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                //Integer co_id = rs.getInt("co_id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String imagePath = rs.getString("imagePath");
                boolean forSale = rs.getBoolean("forSale");
                int co_id = rs.getInt("co_id");

                // Creating a new UserApp class instance and adding it to the array list
                arts.add(new Art(name,price,imagePath, forSale, co_id));
            }

            // Printing results
            System.out.println("name | price | imagePath | forSale | co_id ");
            for (Art art : arts) {
                System.out.println(art.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + arts.size() + "\n");

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

        return arts;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "Santuario11";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertArt(Art art){

        String SQL= "INSERT INTO art(name,price,imagePath, forSale, co_id)"+"VALUES(?,?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            //pstmt.setInt(1,coleccion.getCo_id());
            pstmt.setString(1,art.getName());
            pstmt.setFloat(2,art.getPrice());
            pstmt.setString(3,art.getImagePath());
            pstmt.setBoolean(4,art.isForSale());
            pstmt.setInt(5,art.getCo_id());
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
