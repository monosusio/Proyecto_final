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

    private CollectionService col;
    private Collection colecction;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "minicraftteo";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public CollectioResource() throws SQLException {
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


    @POST
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

    }

    @POST
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearcoleccion(Collection coleccion){
        //coleccion.setColecctionid(col.listacolection().size()+1);
        //System.out.println("esta es la cantidad de colecciones registradas en la base de datos "+col.listacolection().size());
        //System.out.println("este es le coleccition id "+coleccion.getColecctionid());
        System.out.println("este es el nombre "+coleccion.getName());
        System.out.println("este es la descripcion "+coleccion.getDescription());
        System.out.println("este es la category "+coleccion.getCategory());
        System.out.println("este es le email "+coleccion.getEmail());
        col.insertCollection(coleccion);
        System.out.println("se esta pasando por la api de coleccion");
        return Response.ok()
                .entity(coleccion)
                .build();
    }



}
