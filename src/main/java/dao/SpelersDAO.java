package dao;

import managers.PersistenceManager;
import model.Speler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SpelersDAO implements Serializable {

    private List<Speler> spelersList = new ArrayList<Speler>();
    private int maxID = 10;
    private static SpelersDAO spelersDAO = new SpelersDAO();

    public static SpelersDAO getSpelers() {
        return spelersDAO;
    }

    public List<Speler> getAllSpelers() {
        return spelersList;
    }

    public void setSpelersList(List<Speler> spelersList) {
        this.spelersList = spelersList;
    }

    public int getMaxId() {
        return maxID;
    }

    public void setMaxId(int newID) {
        maxID = newID;
    }

    public SpelersDAO(int maxID, List<Speler> spelersList) {
        this.maxID = maxID;
        this.spelersList = spelersList;
    }

    private SpelersDAO() {
        try {
            SpelersDAO loadedDAO = PersistenceManager.loadSpelerFromAzure();
            if (loadedDAO == null) {
                // Set 3 spelers hardcoded erin
                spelersList.add(new Speler(1, "Michael", "van Gerwen", 31, "Professional"));
                spelersList.add(new Speler(2, "Raymond", "van Barneveld", 53, "Professional"));
                spelersList.add(new Speler(3, "Lars", "Sterk", 19, "Amateur"));

            } else {
                this.setMaxId(loadedDAO.getMaxId());
                this.setSpelersList(loadedDAO.getAllSpelers());
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }


    public Speler getSpelerById(int ID) {
        for (Speler speler : spelersList) {
            if (speler.getId() == ID) {
                return speler;
            }
        }
        return null;
    }

    public Speler getSpelerByName(String achternaam) {
        for (Speler speler : spelersList) {
            if (speler.getAchternaam().equals(achternaam)) {
                return speler;
            }
        }
        return null;
    }

    public Speler getSpeler(int id) {
        return getAllSpelers().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }


    public boolean addSpeler(Speler newSpeler) throws IOException {
        setMaxId(maxID + 1);
        newSpeler.setId(getMaxId());
        boolean result = spelersList.add(newSpeler);
        PersistenceManager.saveSpelerToAzure(spelersDAO);
        return result;
    }

    public boolean updateSpeler(Speler upSpeler) throws IOException {
        for (Speler speler : spelersList) {
            if (speler.getId() == upSpeler.getId()) {

                int index = spelersList.indexOf(speler);
                if (!(upSpeler.getVoornaam().equals(""))) {
                    speler.setVoornaam(upSpeler.getVoornaam());
                }
                if (!(upSpeler.getAchternaam().equals(""))) {
                    speler.setAchternaam(upSpeler.getAchternaam());
                }

                if (!(upSpeler.getLeeftijd() == 0)) {
                    speler.setLeeftijd(upSpeler.getLeeftijd());
                }

                if (!(upSpeler.getNiveau().equals(speler.getNiveau()))) {
                    speler.setNiveau(upSpeler.getNiveau());
                }

                spelersList.set(index, speler);
                PersistenceManager.saveSpelerToAzure(spelersDAO);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSpeler(int id) throws IOException {
        for (Speler speler : spelersList) {
            if (speler.getId() == id) {
                int index = spelersList.indexOf(speler);
                spelersList.remove(index);
                PersistenceManager.saveSpelerToAzure(spelersDAO);
                return true;
            }
        }
        return false;
    }


}
