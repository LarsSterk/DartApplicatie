package listeners;

import managers.PersistenceManager;
import model.Gebruiker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new Gebruiker("Lars", "koffie").setBeheerder();
        new Gebruiker("beheerder", "beheerder").setBeheerder();
        new Gebruiker("speler", "speler");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
