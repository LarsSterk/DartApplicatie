package webservices;

import dao.SpelersDAO;
import model.Spel;
import model.Speler;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

@Path("/spelers")
public class SpelerService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createSpeler(String jsonBody){
        StringReader stringReader = new StringReader(jsonBody);
        JsonStructure structure = Json.createReader(stringReader).read();

        String message;

        if (structure.getValueType() == JsonValue.ValueType.OBJECT){
            JsonObject jsonObject = (JsonObject)structure;
            int id = Integer.parseInt(jsonObject.getString("id"));
            String voornaam = jsonObject.getString("voornaam");
            String achternaam = jsonObject.getString("achternaam");
            String niveau = jsonObject.getString("niveau");
            int leeftijd = Integer.parseInt(jsonObject.getString("leeftijd"));

            Speler newSpeler = new Speler(id, voornaam, achternaam, leeftijd, niveau);
            //Speler newSpelerLft = new Speler(id, voornaam, achternaam, leeftijd, niveau);

            if (SpelersDAO.getSpelers().addSpeler(newSpeler) /*|| SpelersDAO.getSpelers().addSpeler(newSpelerLft)*/){
                message = "Speler aangemaakt.";
            }else{
                message = "Speler bestaat al.";
            }
        }else{
            message = "Wrong Json format";
        }
        return Json.createObjectBuilder().add("message", message).build().toString();
    }

    @GET
    @Path("/spelerslijst")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllSpelers(){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        SpelersDAO spelersDAO = SpelersDAO.getSpelers();

        for (Speler speler : spelersDAO.getAllSpelers()){
            JsonObjectBuilder job = Json.createObjectBuilder();

            job.add("id: ", speler.getId());
            job.add("voornaam:", speler.getVoornaam());
            job.add("achternaam: ", speler.getAchternaam());
            job.add("leeftijd: ", speler.getLeeftijd());
            job.add("niveau: ", speler.getNiveau());

            jab.add(job);
        }

        JsonArray array =jab.build();
        return array.toString();
    }

    @GET
    @Path("/spelerslijst/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpelerById(@PathParam("id") int id){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Speler speler = SpelersDAO.getSpelers().getSpelerById(id);
        JsonObjectBuilder job = Json.createObjectBuilder();

        job.add("id: ", speler.getId());
        job.add("voornaam:", speler.getVoornaam());
        job.add("achternaam: ", speler.getAchternaam());
        job.add("leeftijd: ", speler.getLeeftijd());
        job.add("niveau: ", speler.getNiveau());

        jab.add(job);
        JsonArray array =jab.build();

        return array.toString();
    }

    


}
