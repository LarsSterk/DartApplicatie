package managers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import dao.SpelersDAO;
import model.Speler;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import javax.json.*;
import java.io.*;
import java.util.Scanner;

public class PersistenceManager {
    private final static String ENDPOINT = "https://dartapplicatiestorage.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2021-01-22T21:51:23Z&st=2020-05-24T12:51:23Z&spr=https&sig=i9JRiXNaZ8wTTyDdCQD4TMFPXWag4kY5bMXMWvINT0Y%3D";
    private final static String CONTAINER = "spelercontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();


    private static String createJSONStringfromSpelersDAO(SpelersDAO spelersDAO) {

        List<Speler> spelersList = spelersDAO.getAllSpelers();

         // initialiseer de json objecten die we nodig hebben in de string

        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder spelerDetailJob = Json.createObjectBuilder();


        // Voeg de het max ID toe via een jsonobject
        job.add("maxID", spelersDAO.getMaxId());

        // Voeg de JSONarray met spelersdetails toe via een jsonarray
        for (Speler speler : spelersList) {
            spelerDetailJob.add("id", speler.getId());
            spelerDetailJob.add("voornaam", speler.getVoornaam());
            spelerDetailJob.add("achternaam", speler.getAchternaam());
            spelerDetailJob.add("leeftijd", speler.getLeeftijd());
            spelerDetailJob.add("niveau", speler.getNiveau());
            jab.add(spelerDetailJob);
        }
        job.add("Spelers", jab);

        // geef de SpelersDao terug als json object string < ---

        return job.build().toString();
    }

    private static SpelersDAO createSpelersDAOFromJSONString(String jSONString) {

        List<Speler> spelersList = new ArrayList<>();

        // converteer de string naar een json object
        StringReader stringReader = new StringReader(jSONString);
        JsonStructure structure = Json.createReader(stringReader).read();
        JsonObject job = (JsonObject) structure;

        // Lees alle spelers uit de json string en stop ze in een spelerslist
        JsonArray jab = job.getJsonArray("Spelers");

        for (int i = 0; i < jab.size(); i++) {

            int id = jab.getJsonObject(i).getInt("id");
            String voornaam = jab.getJsonObject(i).getString("voornaam");
            String achternaam = jab.getJsonObject(i).getString("achternaam");
            int leeftijd = jab.getJsonObject(i).getInt("leeftijd");
            String niveau = jab.getJsonObject(i).getString("niveau");

            Speler newSpeler = new Speler(id, voornaam, achternaam, leeftijd, niveau);
            spelersList.add(newSpeler);
        }

        // alles gelezen. Nu in de SpelersDAO stoppen
        return new SpelersDAO(job.getInt("maxID"), spelersList);
    }

    // Save de spelers naar Azure
    public static void saveSpelerToAzure(SpelersDAO spelersDAO) throws IOException {
        if (!blobContainer.exists()) {
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("spelerBlob");
        /*converteer de SpelersDAO naar een JSON String*/
        String spelerDB = createJSONStringfromSpelersDAO(spelersDAO);

        /*Save de json string als BLOB in azure*/
        ByteArrayInputStream bais = new ByteArrayInputStream(spelerDB.getBytes());
        blob.upload(bais, spelerDB.length(), true);
        bais.close();
    }

    // Load de spelers van Azure
    public static SpelersDAO loadSpelerFromAzure() throws IOException {
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("spelerBlob");

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);
                String spelerDB = baos.toString();
                baos.close();

                /* De spelers zijn opgeslagen als JSON string in azure. Hieronder maak ik er een Spelers DAO van */
                return createSpelersDAOFromJSONString(spelerDB);

            }

        }

        return null;
    }

    // Save naar een file
    public static void saveSpelerToFile(SpelersDAO spelersDAO) throws IOException {
        try {
            System.out.println("save test");
            File file = new File("Spelers.dat");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("file aangemaakt");
                } else {
                    System.out.println("file NIET aangemaakt");
                }
            }
            FileWriter fw = new FileWriter(file);
            fw.write(createJSONStringfromSpelersDAO(spelersDAO));
            fw.flush();
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load speler van een file
    public static SpelersDAO loadSpelerFromFile() throws IOException, ClassNotFoundException {
        try {
            File file = new File("Spelers.dat");
            if (!file.exists()) {
                System.out.println("file niet gevonden");

                return null;
            } else {
                StringBuilder fileContents = new StringBuilder((int) file.length());
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        fileContents.append(scanner.nextLine() + System.lineSeparator());
                    }
                    return createSpelersDAOFromJSONString(fileContents.toString());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
