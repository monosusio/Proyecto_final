package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.ExceptionMessage;
import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.services.CollectionService;
import co.edu.unbosque.taller5rest_3.services.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/collection")
public class CollectioResource {

    private CollectionService collectionService;
    private Collection colecction;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "minicraftteo";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public CollectioResource() throws SQLException {
        collectionService = new CollectionService(conn);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<Collection> collection = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            CollectionService collectionService = new CollectionService(conn);
            collection = collectionService.collectionList();

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


        return Response.ok().entity(collection).build();
    }


   /* @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createCollection(
            //@FormParam("co_id") Integer co_id,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("category") String category,
            @FormParam("email") String email
    ){

        CollectionService collectionService = new CollectionService(conn);
        Collection coleccion_n = new Collection(name,description,category,email);
        collectionService.insertCollection(coleccion_n);

        System.out.println("Si es aca - Crear collecion");

        return null;

    }*/

    @POST
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearcoleccion(Collection coleccion){


        int random = (int) (Math.random()*(1000-1)) + 1;

        coleccion.setCo_id(random);

        for (int i = 0; i < collectionService.collectionList().size(); i ++) {
            if(collectionService.collectionList().get(i).getCo_id() != random){

                System.out.println("id: "+coleccion.getCo_id());
                System.out.println("nombre: "+coleccion.getName());
                System.out.println("descripcion: "+coleccion.getDescription());
                System.out.println("category: "+coleccion.getCategory());
                System.out.println("email: "+coleccion.getEmail());
                collectionService.insertCollection(coleccion);
            }

        }
        return Response.ok()
                .entity(coleccion)
                .build();
    }



}
