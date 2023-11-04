package oopproje;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdultTest {
    private Adult adult;

    @Test
    void getName() {
        adult=new Adult("furkan","1234");
        assertTrue(adult.getName().compareToIgnoreCase("furkan")==0);
    }

    @Test
    void setName() {
        adult=new Adult("furkan","1234");
        adult.setName("harun");
        assertTrue(adult.getName().compareToIgnoreCase("harun")==0);
    }

    @Test
    void getPassword() {
        adult=new Adult("furkan","1234");
        assertTrue(adult.getPassword().compareToIgnoreCase("1234")==0);
    }

    @Test
    void setPassword() {
        adult=new Adult("furkan","1234");
        adult.setPassword("4321");
        assertTrue(adult.getPassword().compareToIgnoreCase("4321")==0);
    }
}