package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.ExceptionMessage;
import co.edu.unbosque.taller5rest_3.DTO.UserApp;
import co.edu.unbosque.taller5rest_3.services.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.List;

@Path("/users")
public class UsersResource {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "monosusio";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public UsersResource() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<UserApp> users = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            UsersService usersService = new UsersService(conn);
            users = usersService.listUsers();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


        return Response.ok().entity(users).build();
    }

    @POST
    @Path("/found")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response found(UserApp user){
 System.out.println("entrando al metodo found");


        UsersService bass =new UsersService(conn);
        UserApp n=new UserApp(null,null,null, null);
        String email_n=user.getEmail();
        String name_n=user.getName();
        String password_n=user.getPassword();


        List<UserApp> users = bass.listUsers();

        UserApp user_n = users.stream()
                .filter(u -> u.getEmail().equals(email_n) && u.getPassword().equals(password_n))
                .findFirst()
                .orElse(null);



        System.out.println("Fuera del if");

        if (user_n != null) {

            System.out.println("este  es el email"+user_n.getEmail());
            System.out.println("este  es el password"+user_n.getPassword());
            System.out.println("este  es el username"+user_n.getName());
            System.out.println("este  es el role"+user_n.getRole());

            user.setEmail(user_n.getEmail());
            user.setPassword(user_n.getPassword());
            user.setName(user_n.getName());
            user.setRole(user_n.getRole());
            System.out.println("Dentro del if");

            return Response.ok()
                    .entity(user_n)
                    .build();

        } else {
            System.out.println("En el Else de que no encontro el usuario");

            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();


        }

    }

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  createForm(
            @FormParam("email") String email,
            @FormParam("password") String password,
            @FormParam("name") String name,
            @FormParam("role") String role
            ){

            UsersService usersService = new UsersService(conn);

        UserApp user_n = new UserApp(email, password, name, role);
        usersService.insertuser(user_n);

        System.out.println("Si es aca - Crear usuario");

        return null;

    }


}