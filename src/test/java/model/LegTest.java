package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LegTest {

    private Speler speler1;
    private Speler speler2;
    private Speler speler3;
    private Leg leg;
    private ArrayList<Speler> spelers;
    private Spel spel;


    @BeforeEach
    public void init(){
        speler1 = new Speler("voornaam1", "achternaam1", 19, "Beginner");
        speler2 = new Speler("voornaam2", "achternaam2", "Amateur");
        speler3 = new Speler("voornaam3", "achternaam3", "Professional");
        spelers = new ArrayList<>();
        spelers.add(speler1);
        spelers.add(speler2);
        spelers.add(speler3);
        spel = new Spel("501", 3, spelers);
        leg = new Leg(spelers, "501");

    }

    @Test
    void getScoreLijst() {
        // Teller om altijd de juiste grootte van de spelerslijst te geven
        int count = spelers.size();
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < count - 1; i++){
            sb.append("501, ");
        }
        sb.append("501]");

        assertEquals(sb.toString(), leg.getScoreLijst().toString());
    }

    @Test
    void setScoreLijst() {
        ArrayList<Integer> setList = new ArrayList<>();

        assertEquals("Leg{legId=0, worp=1, scoreLijst=[501, 501, 501]}", leg.toString()); // test voordat de nieuwe waarde ge-set word.

        setList.add(301);
        setList.add(301);
        setList.add(301);

        leg.setScoreLijst(setList);

        assertEquals("Leg{legId=0, worp=1, scoreLijst=[301, 301, 301]}", leg.toString());
    }


    @Test
    void getLegId() {
        assertEquals(0, leg.getLegId());
    }

    @Test
    void getWorp() {
        assertEquals(1, leg.getWorp());
    }

    @Test
    void setLegId() {
        assertEquals(0, leg.getLegId());

        leg.setLegId(2);
        assertEquals(2, leg.getLegId());
    }

    @Test
    void setWorp() {
        assertEquals(1, leg.getWorp());

        leg.setWorp(13);
        assertEquals(13, leg.getWorp());
    }
}