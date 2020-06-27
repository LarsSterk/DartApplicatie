package webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringReader;

@Path("/spel")
public class SpelService {
    @POST
    @Path("/aanmaken")
    @RolesAllowed({"speler", "beheerder"})
    @Produces(MediaType.APPLICATION_JSON)
    public String createSpel(@FormParam("id1") int idSpeler1, @FormParam("id2") int idSpeler2) throws IOException {

        String message = "OK";


        return Json.createObjectBuilder().add("message", message).build().toString();
    }

}