package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Art;
import co.edu.unbosque.taller5rest_3.services.ArtService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


@Path("/art")
public class ArtResource {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "Santuario11";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public ArtResource() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<Art> art = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            ArtService artService = new ArtService(conn);
            art = artService.ArtList();

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


        return Response.ok().entity(art).build();
    }


    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createCollection(
            //@FormParam("co_id") Integer co_id,
            @FormParam("name") String name,
            @FormParam("price") float price,
            @FormParam("imagePath") String imagePath,
            @FormParam("forSale") boolean forSale,
            @FormParam("co_id") Integer co_id
    ){

        ArtService artService = new ArtService(conn);
        Art art_n = new Art(name,price,imagePath, forSale, co_id);
        artService.insertArt(art_n);

        System.out.println("Si es aca - Crear Art");

        return null;

    }

}
