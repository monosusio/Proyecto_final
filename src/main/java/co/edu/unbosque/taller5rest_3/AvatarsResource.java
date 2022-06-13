package co.edu.unbosque.taller5rest_3;

import co.edu.unbosque.taller5rest_3.DTO.Art;
import co.edu.unbosque.taller5rest_3.services.ArtService;


import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Path("/users/{username}/avatars")
public class AvatarsResource {

    @Context
    ServletContext context;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    static final String USER = "postgres";
    static final String PASS = "monosusio";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    private final String UPLOAD_DIRECTORY = "/imagen/";

    public AvatarsResource() throws SQLException {
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)


    public Response uploadFile(@FormParam("nombre") String nombre,@FormParam("colecctionid") String colecctionid,@FormParam("precio") String precio, MultipartFormDataInput input) {
        String fileName = "";
        String filename2="";
        Integer colectioni=0;
        Integer precio2=0;
        Integer piece=0;
        String alfanumerico="";
        Art art_n=new Art();
        System.out.println("este es el username "+nombre);

        try {
            // Getting the file from form input
            Map<String, List<InputPart>> formParts = input.getFormDataMap();

            // Extracting text by input name
            if (formParts.get("filename") != null) {
                fileName = formParts.get("filename").get(0).getBodyAsString();
            }

            if(formParts.get("nombre") != null){
                filename2 = formParts.get("nombre").get(0).getBodyAsString()+".jpg";
            }
            if(formParts.get("colecctionid") != null){
                colectioni=Integer.parseInt(formParts.get("colecctionid").get(0).getBodyAsString());
            }
            if(formParts.get("precio") != null){
                precio2=Integer.parseInt(formParts.get("precio").get(0).getBodyAsString());
            }

            ArtService arts = new ArtService(conn);
            piece =arts.ArtList().size();

            List<InputPart> inputParts = formParts.get("imagen");
            System.out.println("este es el filename2 "+ filename2);

            for (InputPart inputPart : inputParts) {
                // If file name is not specified as input, use default file name
                if (fileName.equals("") || fileName == null) {
                    // Retrieving headers and reading the Content-Disposition header to file name
                    MultivaluedMap<String, String> headers = inputPart.getHeaders();
                    fileName = parseFileName(headers);
                }

                // Handling the body of the part with an InputStream
                InputStream istream = inputPart.getBody(InputStream.class,null);

                // Saving the file on disk
                saveFile(istream,alfanumerico, context);
            }

            art_n.setPrice(piece);
            art_n.setName(filename2);
            art_n.setPrice(precio2);
            art_n.setImagePath(alfanumerico);
            art_n.setCo_id(colectioni);

            return Response.status(201)
                    .entity("Avatar successfully uploaded for " + filename2)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    // Parse Content-Disposition header to get the file name
    private String parseFileName(MultivaluedMap<String, String> headers) {
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"","");
                return fileName;
            }
        }

        return "unknown";
    }

    // Save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream, String fileName, ServletContext context) {
        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            // Complementing servlet path with the relative path on the server
            String uploadPath = this.context.getRealPath("") + UPLOAD_DIRECTORY;

            // Creating the upload folder, if not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // Persisting the file by output stream
            OutputStream outpuStream = new FileOutputStream(uploadPath + fileName);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String crear(){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}