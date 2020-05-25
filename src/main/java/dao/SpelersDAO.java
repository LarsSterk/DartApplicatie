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

    public int getMaxId(){
        return maxID;
    }
    public void setMaxId(int newID){
        maxID = newID;
    }

    public SpelersDAO (int maxID, List<Speler> spelersList){
        this.maxID = maxID;
        this.spelersList =  spelersList;
    }

    private SpelersDAO() {
        try {
            SpelersDAO loadedDAO = PersistenceManager.loadSpelerFromAzure();
            if (loadedDAO == null) {
                spelersList.add(new Speler(1, "Lars", "Sterk", 19, "Pro"));
                spelersList.add(new Speler(2, "Kaj", "Sterk", "Pro"));
            } else {
                this.setMaxId(loadedDAO.getMaxId());
                this.setSpelersList(loadedDAO.getAllSpelers());
            }
        }catch (IOException io){
            System.out.println(io.getMessage());
        }
    }


    public Speler getSpelerById(int ID){
        for (Speler speler:spelersList){
            if (speler.getId() == ID) {
                return speler;
            }
        }
        return null;
    }

    public Speler getSpelerByName(String achternaam){
        for (Speler speler : spelersList){
            if (speler.getAchternaam().equals(achternaam)){
                return speler;
            }
        }
        return null;
    }

    public Speler getSpeler(int id){
        return getAllSpelers().stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }


    public boolean addSpeler(Speler newSpeler) throws IOException {

        setMaxId(maxID + 1);
        newSpeler.setId(getMaxId());
        boolean result = spelersList.add(newSpeler);
        PersistenceManager.saveSpelerToAzure(spelersDAO);
        return result;
    }

    public boolean updateSpeler(Speler upSpeler) throws IOException {
        for (Speler speler : spelersList){
            if (speler.getId() == upSpeler.getId()){
                int index = spelersList.indexOf(speler);
                spelersList.set(index, upSpeler);
                PersistenceManager.saveSpelerToAzure(spelersDAO);
                return true;
            }
        }
        return false;
  }

    public boolean deleteSpeler(int id) throws IOException {
        for(Speler speler : spelersList){
            if(speler.getId() == id){
                int index = spelersList.indexOf(speler);
                spelersList.remove(index);
                PersistenceManager.saveSpelerToAzure(spelersDAO);
                return true;
            }
        }
        return false;
    }


}
