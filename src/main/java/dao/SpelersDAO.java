package dao;

import model.Spel;
import model.Speler;

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

    public static void setSpelers(SpelersDAO spelers) {
        spelersDAO = spelers;
    }

    private int getMaxId(){
        return maxID;
    }
    private void setMaxId(int newID){
        maxID = newID;
    }


    private SpelersDAO() {
        spelersList.add(new Speler(1, "Lars", "Sterk", 19, "Pro"));
        spelersList.add(new Speler(2, "Kaj", "Sterk", "Pro"));
    }

    public List<Speler> getAllSpelers() {
        return spelersList;
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

    public boolean addSpeler(Speler newSpeler){

        setMaxId(maxID + 1);
        newSpeler.setId(getMaxId());

        return spelersList.add(newSpeler);
    }

    public boolean updateSpeler(Speler upSpeler){
        for (Speler speler : spelersList){
            if (speler.getId() == upSpeler.getId()){
                int index = spelersList.indexOf(speler);
                spelersList.set(index, upSpeler);
                return true;
            }
        }
        return false;
  }

    public boolean deleteSpeler(int id){
        for(Speler speler : spelersList){
            if(speler.getId() == id){
                int index = spelersList.indexOf(speler);
                spelersList.remove(index);
                return true;
            }
        }
        return false;
    }


}
