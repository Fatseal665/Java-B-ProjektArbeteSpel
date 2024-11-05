package se.martin.game;

import se.martin.game.models.Burglar;
import se.martin.game.models.Entity;
import se.martin.game.models.Resident;

import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    static boolean running = true;

    //Game start method
    public static  void start() {
        printWelcomeMessage();
        Room.livingRoom();
        menyInput();
    }


    //Meny methods
    public static void printMeny() {
        System.out.println("Which room do you want to go to?\n");
        System.out.println("               ↑");
        System.out.println("             Bedroom");
        System.out.println("← Kitchen* Living room *Office →");
        System.out.println("             Hallway");
        System.out.println("               ↓\n");
        System.out.println("Exit game X\n");
    }

    public static void menyInput() {
        while (running) {

            String userInput = scanner.nextLine().toLowerCase();

            switch (userInput) {
                case "living room" -> Room.livingRoom();
                case "bedroom" -> Room.bedroom();
                case "kitchen" -> Room.kitchen();
                case "hallway" -> Room.hallwayFight();
                case "office" -> Room.office();

                case "exit" -> {
                    System.out.println("Exiting game...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Which room do you want to go to?");
            }
        }
    }


    //Welcome and ending messages
    public static void printWelcomeMessage() {
        System.out.println("\nYou are in chilling the living room at 1 AM. Suddenly you hear some strange noises.");
        System.out.println("Like drawers opening and closing. What could it be? Let's investigate...\n");
    }

    public static void printEndMessage() {
        System.out.println("*Calls the police and informs them about the unconscious burglar in the hallway\n");
        System.out.println("The police arrive shortly and arrests the burglar. What an unexpected turn of events");
        System.out.println("Now you can finally go back to the living room and relax...\n");
        System.out.println("Congrats! You completed the game! \n\nGame by Martin Eriksson");
    }


    //Fight Methods
    static Resident resident = new Resident(50, 10, "Resident");
    static Burglar burglar = new Burglar(50, 15, "Burglar");

    static void executeAttack(Entity Attacker, Entity Defender) {
        Attacker.punch(Defender);
        System.out.println(Attacker.getRole() + " hits " + Defender.getRole());

        if (Defender.isConscious()) {
            System.out.println(Defender.getRole() + "'s health is: " + Defender.getHealth());
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

