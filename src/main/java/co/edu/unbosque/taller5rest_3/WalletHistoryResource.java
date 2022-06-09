package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Collection;
import co.edu.unbosque.taller5rest_3.DTO.WalletHistory;
import co.edu.unbosque.taller5rest_3.services.CollectionService;
import co.edu.unbosque.taller5rest_3.services.UsersService;
import co.edu.unbosque.taller5rest_3.services.WalletHistoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/wallethistory")
public class WalletHistoryResource {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "Santuario11";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public WalletHistoryResource() throws SQLException {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Connection conn = null;

        List<WalletHistory> walletHistory = null;

        try {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            WalletHistoryService walletHistoryService = new WalletHistoryService(conn);
            walletHistory = walletHistoryService.walletHistoryList();

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


        return Response.ok().entity(walletHistory).build();
    }


    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createWalletHistory(
            //@FormParam("co_id") Integer co_id,
            @FormParam("email") String email,
            @FormParam("type") String type,
            @FormParam("fcoins") float fcoins,
            @FormParam("registeredAt") String registeredAt
    ){

        WalletHistoryService walletHistoryService = new WalletHistoryService(conn);
        WalletHistory walletHistory_n = new WalletHistory(email,type,fcoins,registeredAt);
        walletHistoryService.insertWalletHistory(walletHistory_n);

        System.out.println("Si es aca - Crear collecion");

        return null;

    }
}
