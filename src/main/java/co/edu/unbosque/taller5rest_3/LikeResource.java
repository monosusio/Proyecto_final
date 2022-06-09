package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Likes;
import co.edu.unbosque.taller5rest_3.services.LikeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/likes")
public class LikeResource {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "Santuario11";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public LikeResource() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<Likes> like = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            LikeService likeService = new LikeService(conn);
            like = likeService.likesList();

            //PetsService petsService = new PetsService(conn);
            //petsService.countBySpecies("dog");

            //OwnersService ownersService = new OwnersService(conn);
            //ownersService.updateOwner(new Owner(6697, null, "Pepe"));

            conn.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


        return Response.ok().entity(like).build();
    }


    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createLike(
            //@FormParam("co_id") Integer co_id,
            @FormParam("name") Integer art_id,
            @FormParam("description") String email,
            @FormParam("category") String registeredAt
    ){

        LikeService likesService = new LikeService(conn);
        Likes likes_n = new Likes(art_id,email,registeredAt);
        likesService.insertLikes(likes_n);

        System.out.println("Si es aca - Crear Like");

        return null;

    }

}
