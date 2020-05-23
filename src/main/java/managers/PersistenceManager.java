//package managers;
//
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobContainerClientBuilder;
//import model.Spel;
//import model.Speler;
//
//import java.io.*;
//
//public class PersistenceManager {
//    private final static String ENDPOINT = "https://dartapplicatiestorage.blob.core.windows.net/";
//    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2020-05-22T21:19:16Z&st=2020-05-22T13:19:16Z&spr=https&sig=hdhKXK4dHZNrgpdjz0592CRM5dk0NuYI%2F9wubUesdZ4%3D";
//    private final static String CONTAINER = "speler_container";
//
//    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
//            .endpoint(ENDPOINT)
//            .sasToken(SASTOKEN)
//            .containerName(CONTAINER)
//            .buildClient();
//
//    // Save
//    public static void saveSpelerToAzure() throws IOException {
//
//        if (!blobContainer.exists()){
//            blobContainer.create();
//        }
//
//        BlobClient blob = blobContainer.getBlobClient("speler_blob");
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(Spel.getSpel());
//
//        byte[] bytez = baos.toByteArray();
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
//        blob.upload(bais, bytez.length, true);
//
//        oos.close();
//        bais.close();
//    }
//
//    public static void loadSpelerFromAzure() throws IOException, ClassNotFoundException {
//        if (blobContainer.exists()){
//            BlobClient blob = blobContainer.getBlobClient("speler_blob");
//
//            if (blob.exists()){
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                blob.download(baos);
//
//                byte[] bytez = baos.toByteArray();
//
//                ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
//                ObjectInputStream ois = new ObjectInputStream(bais);
//
//                Object obj = ois.readObject();
//                if(obj instanceof Spel){
//                    Spel loadedSpel = (Spel)obj;
//                    Spel.setSpel(loadedSpel);
//                }
//
//                baos.close();
//                ois.close();
//            }
//        }
//    }
//
//}
