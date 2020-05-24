package managers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import dao.SpelersDAO;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://dartapplicatiestorage.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2021-01-22T21:51:23Z&st=2020-05-24T12:51:23Z&spr=https&sig=i9JRiXNaZ8wTTyDdCQD4TMFPXWag4kY5bMXMWvINT0Y%3D";
    private final static String CONTAINER = "spelercontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();


    // Save naar Azure
    public static void saveSpelerToAzure(SpelersDAO spelersDAO) throws IOException {
        if (!blobContainer.exists()) {
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("spelerBlob");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(spelersDAO);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();
    }

    // Save naar een file
    public static void saveSpelerToFile(SpelersDAO spelersDAO) throws IOException {
        try {
            System.out.println("save test");
            File file = new File("Spelers.dat");
            FileOutputStream fos;

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("file aangemaakt");
                } else {
                    System.out.println("file NIET aangemaakt");
                }
            }

            fos = new FileOutputStream(file);


            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(spelersDAO);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load van Azure
    public static SpelersDAO loadSpelerFromAzure() throws IOException, ClassNotFoundException {
        if (blobContainer.exists()) {
            System.out.println("blob exists");
            BlobClient blob = blobContainer.getBlobClient("spelerBlob");

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                byte[] bytez = baos.toByteArray();

                ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object obj = ois.readObject();
                if (obj instanceof SpelersDAO) {
                    SpelersDAO loadedSpelerdao = (SpelersDAO) obj;
                    return loadedSpelerdao;
                }

                baos.close();
                ois.close();
            }

        }
        return null;
    }

    // Load speler van een file
    public static SpelersDAO loadSpelerFromFile() throws IOException, ClassNotFoundException {
        try {
            System.out.println("load test");
            File file = new File("Spelers.dat");
            if (!file.exists()) {
                System.out.println("file niet gevonden");

                return null;
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object obj = ois.readObject();
                if (obj instanceof SpelersDAO) {
                    SpelersDAO loadedSpelerdao = (SpelersDAO) obj;
//                    SpelersDAO.setSpelers(loadedSpelerdao);
                    return loadedSpelerdao;
                }
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
