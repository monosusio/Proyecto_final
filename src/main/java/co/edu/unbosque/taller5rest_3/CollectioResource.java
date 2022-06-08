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

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "monosusio";
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

            UsersService usersService = new UsersService(conn);
            //collection = usersService.listUsers();

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
    @Path("/found")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response found(Collection coleccion){
        System.out.println("entrando al metodo found");


        UsersService bass =new UsersService(conn);
        Collection n=new Collection(null,null,null, null,null);

        String email_n= coleccion.getEmail();
        String name_n= coleccion.getName();
        String password_n= coleccion.getPassword();


        System.out.println("este  es el email"+user_n.getEmail());
        System.out.println("este  es el password"+user_n.getPassword());
        System.out.println("este  es el username"+user_n.getName());
        System.out.println("este  es el role"+user_n.getRole());

        System.out.println("Fuera del if");

        if (user_n != null) {

            user.setEmail(user_n.getEmail());
            user.setPassword(user_n.getPassword());
            user.setName(user_n.getName());
            user.setRole(user_n.getRole());
            System.out.println("Dentro del if");

            return Response.ok()
                    .entity(user_n)
                    .build();

        } else {
            System.out.println("En el Else");

            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
        }

    }*/


   /* @POST
    @Path("/found")
    public Response found (@FormParam("username")String name,@FormParam("password")String password) throws SQLException {

        Usuario usuario = null;
        Connection connection = null;
        List<Usuario> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String query = "SELECT  * FROM Usuario";
            ResultSet result = statement.executeQuery(query);
            System.out.println("sexo con monos, la secuela");
            while (result.next()) {

                String username = result.getString("username");
                String key = result.getString("password");
                String role = result.getString("role");
                if (Optional.ofNullable(role).orElse("Shopper").equalsIgnoreCase("Artist"))
                    list.add(new Artist(name, password, ""));
                else
                    list.add(new Shopper(name, password, 0));
            }
                list.forEach(x -> System.out.println(x));
                usuario = list.stream()
                        .filter(x -> x.getUsername().equals(username) && x.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);

                System.out.println("sexo con monos");
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        return Response.ok()
                .entity(usuario)
                .build();
    }*/

    /*@GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username){

        List<Usuario> users = new UsersService(conn).listUsers();

        Usuario user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (user != null) {

            return Response.ok()
                    .entity(user)
                    .build();
        } else {
            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
        }

    }*/

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  createCollection(
            @FormParam("co_id") Integer co_id,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("category") String category,
            @FormParam("email") String email
    ){

        CollectionService collectionService = new CollectionService(conn);

        Collection coleccion_n = new Collection(co_id, name,description,category,email);

        collectionService.insertuser(coleccion_n);

        System.out.println("Si es aca - Crear collecion");

        return null;

    }



}
