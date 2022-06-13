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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/wallethistory")
public class WalletHistoryResource {

    private WalletHistoryService walletHistoryService;
    private WalletHistory walletHistory;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "minicraftteo";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


    public WalletHistoryResource() throws SQLException {
        walletHistoryService = new WalletHistoryService(conn);
        walletHistory = new WalletHistory();
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
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearWallet(WalletHistory walletHistory){


        int random = (int) (Math.random()*(1000-1)) + 1;

        walletHistory.setWh_id(random);

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        walletHistory.setRegisteredAt(timestamp);


        System.out.println("Entra al metodo para añadir las lukas");
        System.out.println("id: "+walletHistory.getWh_id());
        System.out.println("FCoins: "+walletHistory.getFcoins());
        System.out.println("Email: "+walletHistory.getEmail());
        System.out.println("fecha: "+walletHistory.getRegisteredAt());


        boolean b = true;
        for (int i = 0; i < walletHistoryService.walletHistoryList().size(); i ++) {

                System.out.println("Entra al primer for para añadir las lukas");
                System.out.println("Email dentro del for: " + walletHistoryService.walletHistoryList().get(i).getEmail() + "\n");
                System.out.println("Tamaño del malparido " + walletHistoryService.walletHistoryList().size() + "\n");


                //while (b) {
                //if(walletHistoryService.walletHistoryList().get(i).getWh_id() != random){

                if (walletHistoryService.walletHistoryList().get(i).getEmail().equals(walletHistory.getEmail())) {
                    System.out.println("Dentro del miserable IF");
                    float suma = walletHistoryService.walletHistoryList().get(i).getFcoins() + walletHistory.getFcoins();
                    int random2 = (int) (Math.random() * (1000 - 1)) + 1;

                    walletHistory.setFcoins(suma);
                    walletHistory.setWh_id(random2);

                    walletHistoryService.insertWalletHistory(walletHistory);

                    System.out.println("La suma de fcoins es: " + suma);

                    break;
                }

                else if (!(walletHistoryService.walletHistoryList().get(i).getEmail()).equals(walletHistory.getEmail())) {


                    walletHistoryService.insertWalletHistory(walletHistory);
                    System.out.println("Fuera del miserable IF");

                    System.out.println("Esta es la prueba del email impreso: " + walletHistoryService.walletHistoryList().get(i).getEmail() + "\n");



                }



        }


        //}
        return Response.ok()
                .entity(walletHistory)
                .build();
    }

}
