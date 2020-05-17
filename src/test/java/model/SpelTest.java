package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpelTest {
    private Speler speler1;
    private Speler speler2;
    private ArrayList<Speler> spelers;
    private Leg leg1;
    private Leg leg2;
    private ArrayList<Leg> legs;
    private Spel spel;

    @BeforeEach
    public void init() {
        speler1 = new Speler("voornaam1", "achternaam1", 19, "Beginner");
        speler2 = new Speler("voornaam2", "achternaam2", "Amateur");
        spelers = new ArrayList<>();
        spelers.add(speler1);
        spelers.add(speler2);
        leg1 = new Leg(spelers, "501");
        leg2 = new Leg(spelers, "501");
        legs = new ArrayList<>();
        legs.add(leg1);
        legs.add(leg2);
        spel = new Spel("501", 3, spelers);
    }


    @Test
    void getSpelersLijst() {
        // To string is automatisch gegenereerd.
        assertEquals("[Speler{id=0, voornaam='voornaam1', achternaam='achternaam1', leeftijd=19, niveau='Beginner'}, Speler{id=0, voornaam='voornaam2', achternaam='achternaam2', leeftijd=0, niveau='Amateur'}]", spelers.toString());
    }

    @Test
    void getType() {
        assertEquals("501", spel.getType());
    }

    @Test
    void getStand() {
        int count = spelers.size();
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < count - 1; i++){
            sb.append("0, ");
        }
        sb.append("0]");

        assertEquals(sb.toString(), spel.getStand().toString());
    }

    @Test
    void getLegLijst() {
        spel.setLegLijst(legs);
        assertEquals(2, spel.getLegLijst().size());
        assertEquals("[Leg{legId=0, worp=1, scoreLijst=[501, 501]}, Leg{legId=0, worp=1, scoreLijst=[501, 501]}]", spel.getLegLijst().toString());
    }

    @Test
    void getWinnendAantalLegs() {
        assertEquals(3, spel.getWinnendAantalLegs());
    }

    @Test
    void getSpelId() {
        assertEquals(0, spel.getSpelId());
    }

    @Test
    void setSpelersLijst() {
        Speler speler3 = new Speler("voornaam3", "achternaam3", 20, "Amateur");
        spelers.add(speler3);
        spel.setSpelersLijst(spelers);

        assertEquals("[Speler{id=0, voornaam='voornaam1', achternaam='achternaam1', leeftijd=19, niveau='Beginner'}, " +
                     "Speler{id=0, voornaam='voornaam2', achternaam='achternaam2', leeftijd=0, niveau='Amateur'}, " +
                     "Speler{id=0, voornaam='voornaam3', achternaam='achternaam3', leeftijd=20, niveau='Amateur'}]", spelers.toString());
    }

    @Test
    void setType() {
        assertEquals("501", spel.getType());
        spel.setType("301");
        assertEquals("301", spel.getType());
    }

    @Test
    void setStand() {
        assertEquals("[0, 0]", spel.getStand().toString());
        ArrayList<Integer> nieuweStand = new ArrayList<>();

        nieuweStand.add(1);
        nieuweStand.add(2);
        spel.setStand(nieuweStand);

        assertEquals(nieuweStand.toString(), spel.getStand().toString());

    }

    @Test
    void setLegLijst() {
        assertEquals("[]", spel.getLegLijst().toString());
        Leg leg3 = new Leg(spelers, "301");

        legs.add(leg3);
        spel.setLegLijst(legs);
        assertEquals("[Leg{legId=0, worp=1, scoreLijst=[501, 501]}, " +
                     "Leg{legId=0, worp=1, scoreLijst=[501, 501]}, " +
                     "Leg{legId=0, worp=1, scoreLijst=[]}]", spel.getLegLijst().toString());

    }

    @Test
    void setWinnendAantalLegs() {
        assertEquals(3, spel.getWinnendAantalLegs());
        spel.setWinnendAantalLegs(5);
        assertEquals(5, spel.getWinnendAantalLegs());
    }



    @Test
    void setSpelId() {
        assertEquals(0, spel.getSpelId());

        spel.setSpelId(11);

        assertEquals(11,spel.getSpelId());

    }
}