package se.martin.game;

import se.martin.game.models.Burglar;
import se.martin.game.models.Entity;
import se.martin.game.models.Resident;


public class Game {

    static Resident resident = new Resident(50, 10, "Resident");
    static Burglar burglar = new Burglar(50, 20, "Burglar");

    //Game start method
    public static  void start() {
        printWelcomeMessage();
        Room.livingRoom();
    }


    //Welcome and ending messages
    public static void printWelcomeMessage() {
        System.out.println("\nYou are chilling in the living room at 1 AM. Suddenly you hear some strange noises.");
        System.out.println("Like drawers opening and closing. What could it be? Let's investigate...\n");
    }

    public static void printEndMessage() {
        System.out.println("*Calls the police and informs them about the unconscious burglar in the hallway\n");
        System.out.println("The police arrive shortly and arrests the burglar. What an unexpected turn of events");
        System.out.println("Now you can finally go back to the living room and relax...\n");
        System.out.println("Congrats! You completed the game! \n\nGame by Martin Eriksson");
    }


    //Fighting Methods
    public static void executeAttack(Entity Attacker, Entity Defender) {
        Attacker.punch(Defender);
        System.out.println(Attacker.getRole() + " hits " + Defender.getRole());

        if (Defender.isConscious()) {
            System.out.println(Defender.getRole() + "'s health is: " + Defender.getHealth() + "\n");
        } else {
            System.out.println(Defender.getRole()
                    + " has been knocked out\n");
        }
    }

    public static void fightOneRound(Resident resident, Burglar burglar) {
        executeAttack(resident, burglar);

        if (burglar.isConscious()) {
            executeAttack(burglar, resident);
        }
    }
}

