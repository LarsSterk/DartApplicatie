//package webservices;
//
//import model.Spel;
//import model.Speler;
//
//import javax.json.*;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.awt.*;
//import java.io.StringReader;
//
//@Path("/spel")
//public class SpelResource {
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public String createSpeler(String jsonBody){
//        StringReader stringReader = new StringReader(jsonBody);
//        JsonStructure structure = Json.createReader(stringReader).read();
//
//        String message;
//
//        if (structure.getValueType() == JsonValue.ValueType.OBJECT){
//            JsonObject jsonObject = (JsonObject)structure;
//            String voornaam = jsonObject.getString("voornaam");
//            String achternaam = jsonObject.getString("achternaam");
//            String niveau = jsonObject.getString("niveau");
//
//            Speler newSpeler = new Speler(voornaam, achternaam, niveau);
//// Hier toeveogen aan de database via persistence manager
//            if (Spel.getSpel().addSpeler(newSpeler)){
//                message = "Speler aangemaakt.";
//            }else {
//                message = "Speler bestaat al.";
//            }
//        }else{
//            message = "Wrong Json format";
//        }
//        return Json.createObjectBuilder().add("message", message).build().toString();
//    }
//
//    @GET
//    @Path("/spelers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getAllSpelers(){
//        JsonArrayBuilder jab = Json.createArrayBuilder();
//        Spel spel = Spel.getSpel();
//
//        for (Speler speler : spel.getSpelersLijst()){
//            JsonObjectBuilder job = Json.createObjectBuilder();
//
//            if (speler.getLeeftijd() == 0){
//                job.add("voornaam:", speler.getVoornaam());
//                job.add("achternaam: ", speler.getAchternaam());
//                job.add("niveau: ", speler.getNiveau());
//
//            }else {
//                job.add("voornaam:", speler.getVoornaam());
//                job.add("achternaam: ", speler.getAchternaam());
//                job.add("leeftijd: ", speler.getLeeftijd());
//                job.add("niveau: ", speler.getNiveau());
//            }
//
//            jab.add(job);
//        }
//
//        JsonArray array =jab.build();
//        return array.toString();
//    }
//
//
//}
