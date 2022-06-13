package co.edu.unbosque.taller5rest_3.services;

import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.DTO.Likes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeService {

    private Connection conn;

    public LikeService(Connection conn) {this.conn = conn;}

    public List<Likes> likesList() {
        Statement stmt = null;

        List<Likes> likess = new ArrayList<Likes>();

        try {
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM likes";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Integer art_id = rs.getInt("art_id");
                String email= rs.getString("email");
                String registeredAt = rs.getString("registeredAt");
                likess.add(new Likes(art_id,email,registeredAt));
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

        return likess;
    }

    public Connection connect() throws SQLException {
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASS = "monosusio";

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long insertLikes(Likes likes){

        String SQL= "INSERT INTO likes(art_id,email,registeredAt)"+"VALUES(?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1,likes.getArt_id());
            pstmt.setString(2,likes.getEmail());
            pstmt.setString(3,likes.getRegisteredAt());
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
