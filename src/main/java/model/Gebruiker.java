package model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class Gebruiker implements Principal {
    private static List<Gebruiker> allGebruikers = new ArrayList<>();
    private String username;
    private String plainPassword;
    private String role;

    public Gebruiker(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
        role = "speler";

        if (!allGebruikers.contains(this)){
            allGebruikers.add(this);
        }
    }

    public void setBeheerder(){
        role="beheerder";
    }

    @Override
    public String getName() {
        return username;
    }

    public String getRole(){
        return role;
    }

    public static Gebruiker getGebruikerByNaam(String uname){
        for (Gebruiker gebruiker : allGebruikers){
            if (gebruiker.username.equals(uname)){
                return gebruiker;
            }
        }
        return null;
    }

    public static String validateLogin(String uname, String pwd){
        Gebruiker found = getGebruikerByNaam(uname);
        if (found != null){
            return pwd.equals(found.plainPassword) ? found.getRole(): null;
        }
        return null;
    }
}
