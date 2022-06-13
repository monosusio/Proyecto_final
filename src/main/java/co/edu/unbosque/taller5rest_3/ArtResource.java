package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Art;
import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.services.ArtService;
import co.edu.unbosque.taller5rest_3.services.CollectionService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


@Path("/art")
public class ArtResource {

    private ArtService artService;
    private Art art;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "monosusio";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public ArtResource() throws SQLException {
        artService = new ArtService(conn);
        art = new Art();
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

            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearArte(Art art){

        int random = (int) (Math.random()*(1000-1)) + 1;

        art.setArt_id(random);
        System.out.println("para por la api de arte (fuera del for)");

        /*for (int i = 0; i < artService.ArtList().size(); i ++) {
            if (artService.ArtList().get(i).getArt_id() != random) {*/

                System.out.println("art_ID: " + art.getArt_id());
                System.out.println("nombre: " + art.getName());
                System.out.println("precio: " + art.getPrice());
                System.out.println("booleano: " + art.isForSale());
                System.out.println("codigo de la coleccion " + art.getCo_id());
                artService.insertArt(art);
                System.out.println("para por la api de arte");

        return Response.ok()
                .entity(art)
                .build();
    }

}
