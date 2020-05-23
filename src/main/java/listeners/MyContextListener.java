//package listeners;
//
//import managers.PersistenceManager;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.IOException;
//
//@WebListener
//public class MyContextListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            PersistenceManager.loadSpelerFromAzure();
//            System.out.println("Spel geladen.");
//        } catch (IOException e) {
//            System.out.println("Kan Spel niet laden.");
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Kan Spel niet laden.");
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        try {
//            PersistenceManager.saveSpelerToAzure();
//            System.out.println("Spel opgeslagen.");
//        } catch (IOException e) {
//            System.out.println("Kan Spel niet opslaan.");
//            e.printStackTrace();
//        }
//    }
//}
