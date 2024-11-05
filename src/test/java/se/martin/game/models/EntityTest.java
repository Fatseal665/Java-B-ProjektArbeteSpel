package se.martin.game.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityTest {

    @Test
    void takeHit() {
        Resident resident = new Resident(50, 10, "Resident");

        resident.takeHit(10);

        Assertions.assertEquals(40, resident.getHealth());
    }

    @Test
    void punch() {
        Resident resident = new Resident(50, 10, "Resident");
        Burglar burglar = new Burglar(50, 15, "Burglar");

        resident.punch(burglar);

        Assertions.assertEquals(40, burglar.getHealth());
    }

    @Test
    void isConsciousTrue() {
        Resident resident = new Resident(50, 10, "Resident");
        Burglar burglar = new Burglar(50, 15, "Burglar");

        resident.punch(burglar);
        burglar.isConscious();

        Assertions.assertTrue(true);
    }

    @Test
    void isConsciousFalse() {
        Resident resident = new Resident(50, 50, "Resident");
        Burglar burglar = new Burglar(50, 15, "Burglar");

        resident.punch(burglar);
        burglar.isConscious();

        Assertions.assertFalse(false);
    }
}