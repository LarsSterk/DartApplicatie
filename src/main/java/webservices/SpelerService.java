package webservices;

import dao.SpelersDAO;
import model.Speler;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringReader;

@Path("/spelers")
public class SpelerService {

    @POST
    @Path("/aanmakenRAW")
    @Produces(MediaType.APPLICATION_JSON)
    public String createSpelerRaw(String jsonBody) throws IOException {
        StringReader stringReader = new StringReader(jsonBody);
        JsonStructure structure = Json.createReader(stringReader).read();

        String message;

        if (structure.getValueType() == JsonValue.ValueType.OBJECT) {
            JsonObject jsonObject = (JsonObject) structure;
            int id = Integer.parseInt(jsonObject.getString("id"));
            String voornaam = jsonObject.getString("voornaam");
            String achternaam = jsonObject.getString("achternaam");
            String niveau = jsonObject.getString("niveau");
            int leeftijd = Integer.parseInt(jsonObject.getString("leeftijd"));

            Speler newSpeler = new Speler(id, voornaam, achternaam, leeftijd, niveau);

            if (SpelersDAO.getSpelers().addSpeler(newSpeler)) {
                message = "Speler aangemaakt.";
            } else {
                message = "Speler bestaat al.";
            }
        } else {
            message = "Wrong Json format";
        }
        return Json.createObjectBuilder().add("message", message).build().toString();
    }

    @POST
    @Path("/aanmaken")
    @RolesAllowed({"speler","beheerder"})
    @Produces(MediaType.APPLICATION_JSON)
    public String createSpeler(@FormParam("id") int id, @FormParam("voornaam") String voornaam, @FormParam("achternaam") String achternaam,
                               @FormParam("leeftijd") int leeftijd, @FormParam("niveau") String niveau) throws IOException {
        String message;


        Speler newSpeler = new Speler(id, voornaam, achternaam, leeftijd, niveau);

        if (SpelersDAO.getSpelers().addSpeler(newSpeler)) {
            message = "Speler aangemaakt.";
        } else {
            message = "Speler bestaat al.";
        }

        return Json.createObjectBuilder().add("message", message).build().toString();
    }

    @GET
    @Path("/spelerslijst")
    @RolesAllowed({"speler", "beheerder"})
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSpelers() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        SpelersDAO spelersDAO = SpelersDAO.getSpelers();

        for (Speler speler : spelersDAO.getAllSpelers()) {
            JsonObjectBuilder job = Json.createObjectBuilder();

            job.add("id", speler.getId());
            job.add("voornaam", speler.getVoornaam());
            job.add("achternaam", speler.getAchternaam());
            job.add("leeftijd", speler.getLeeftijd());
            job.add("niveau", speler.getNiveau());

            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @GET
    @Path("/spelerslijst/{id}")
    @RolesAllowed({"speler", "beheerder"})
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpelerById(@PathParam("id") int id) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Speler speler = SpelersDAO.getSpelers().getSpelerById(id);
        JsonObjectBuilder job = Json.createObjectBuilder();

        job.add("id", speler.getId());
        job.add("voornaam", speler.getVoornaam());
        job.add("achternaam", speler.getAchternaam());
        job.add("leeftijd", speler.getLeeftijd());
        job.add("niveau", speler.getNiveau());

        jab.add(job);
        JsonArray array = jab.build();

        return array.toString();
    }


    @PUT
    @Path("/spelerslijst/update/{id}")
    @RolesAllowed({"speler", "beheerder"})
    @Produces(MediaType.APPLICATION_JSON)



        public String updateSpeler(@PathParam("id") int id, @FormParam("voornaam") String voornaam, @FormParam("achternaam") String achternaam,
                                   @FormParam("leeftijd") int leeftijd, @FormParam("niveau") String niveau) throws IOException {

        String message;

            Speler newSpeler = new Speler(id, voornaam, achternaam, leeftijd, niveau);

            if (SpelersDAO.getSpelers().updateSpeler(newSpeler)) {
                message = "Speler geupdate.";
            } else {
                message = "Speler bestaat al.";
            }

        return Json.createObjectBuilder().add("message", message).build().toString();
    }

    @DELETE
    @Path("/spelerslijst/delete/{id}")
    @RolesAllowed({"beheerder"})
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSpeler(@PathParam("id") int id) throws IOException {
        String message = "Wrong json format";
        if (SpelersDAO.getSpelers().deleteSpeler(id)) {
            message = "Speler verwijderd.";
        } else {
            message = "Speler niet gevonden.";
        }
        return Json.createObjectBuilder().add("message", message).build().toString();
    }

}