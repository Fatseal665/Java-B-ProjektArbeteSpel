package se.martin.game;

import se.martin.game.models.Burglar;
import se.martin.game.models.Entity;
import se.martin.game.models.Resident;

import java.util.Scanner;


public class Game {

    private static final String LIVING_ROOM = "livingRoom";
    private static final String BEDROOM = "bedroom";
    private static final String KITCHEN = "kitchen";
    private static final String HALLWAY = "hallway";
    private static final String OFFICE = "office";
    private static final String START = "start";

    private static String currentLocation = START;


    private boolean fryingPanFound = true;
    static boolean running = true;

    //Instantiation of resident and burglar
    static Resident resident = new Resident(50, 10, "Resident");
    static Burglar burglar = new Burglar(50, 20, "Burglar");
    static Scanner scanner = new Scanner(System.in);

    //Game start method
    public void start() {
        printWelcomeMessage();

        while (running) {
            livingRoom();
        }
    }


    //Welcome and ending messages
    private void printWelcomeMessage() {
        System.out.println("\nYou are chilling in the living room at 1 AM. Suddenly you hear some strange noises.");
        System.out.println("Like drawers opening and closing. What could it be? Let's investigate...\n");
    }

    private void printEndMessage() {
        System.out.println("*Calls the police and informs them about the unconscious burglar in the hallway\n");
        System.out.println("The police arrive shortly and arrests the burglar. What an unexpected turn of events");
        System.out.println("Now you can finally go back to the living room and relax...\n");
        System.out.println("Congrats! You completed the game! \n\nGame by Martin Eriksson");
    }


    //Fighting Methods
    private void executeAttack(Entity Attacker, Entity Defender) {
        Attacker.punch(Defender);
        System.out.println(Attacker.getRole() + " hits " + Defender.getRole());

        if (Defender.isConscious()) {
            System.out.println(Defender.getRole() + "'s health is: " + Defender.getHealth() + "\n");
        } else {
            System.out.println(Defender.getRole()
                    + " has been knocked out\n");
        }
    }

    private void fightOneRound(Resident resident, Burglar burglar) {
        executeAttack(resident, burglar);

        if (burglar.isConscious()) {
            executeAttack(burglar, resident);
        }
    }


    //Rooms
    private void livingRoom() {
        if (!currentLocation.equals(LIVING_ROOM)) {
            currentLocation = LIVING_ROOM;
            System.out.println("\nYou are now in the living room");

            //Visual menu
            System.out.println("\nWhich room do you want to go to?\n");
            System.out.println("               ↑");
            System.out.println("             Bedroom");
            System.out.println("← Kitchen* Living room *Office →");
            System.out.println("             Hallway");
            System.out.println("               ↓\n");
            System.out.println("Exit game X\n");

            //Living room menu
            boolean livingRoomMenu = true;
            while (livingRoomMenu) {

                String userInput = scanner.nextLine().toLowerCase();
                switch (userInput) {
                    case "living room" -> livingRoom();
                    case "bedroom" -> bedroom();
                    case "kitchen" -> kitchen();
                    case "hallway" -> hallwayFight();
                    case "office" -> office();

                    case "exit" -> {
                        System.out.println("Exiting game...");
                        livingRoomMenu = false;
                        running = false;
                    }
                    default -> System.out.println("\nInvalid choice. Which room do you want to go to?");
                }
                if (!running) {
                    livingRoomMenu = false;
                }
            }

        } else {
            System.out.println("You can't go there right now\n");
        }
    }


    private void hallwayFight(){
        currentLocation = HALLWAY;

        if (burglar.isConscious()) {
            System.out.println("\nYou have spotted the source of the strange noises. A burglar is standing in the hallway!");

            boolean fighting = true;
            while (fighting && resident.isConscious() && burglar.isConscious()) {
                System.out.println("Do you want to attack the burglar? yes/no\n");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("yes")) {
                    fightOneRound(resident, burglar);

                    if (!resident.isConscious()) {
                        System.out.println("\nGame Over");
                        running = false;
                        fighting = false;

                    } else if (!burglar.isConscious()) {
                        System.out.println("Congrats! You managed to knock out the burglar.\nNow find the phone and call the police.\n");
                        fighting = false;
                        hallway();
                    }

                } else if (choice.equals("no")) {
                    System.out.println("\nYou walk back to the living room slowly");
                    fighting = false;
                    livingRoom();

                } else {
                    System.out.println("Invalid choice\n");
                }
            }

        } else {
            hallway();
        }
    }


    private void hallway() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("\nYou are now in the hallway");
            currentLocation = HALLWAY;

        } else if (currentLocation.equals(HALLWAY)) {

            //Hallway menu. Appears when burglar is unconscious
            boolean hallwayMenu = true;

            while (hallwayMenu) {
                System.out.println("What do you want to do?\n");
                System.out.println("1. Examine front door\n2. Go back to living room");

                String inputHallway = scanner.nextLine().toLowerCase();
                switch (inputHallway) {
                    case "examine front door" -> {
                        System.out.println("The front door is not damaged at all.");
                        System.out.println("The burglar must've used a fake key to get in.\n");
                    }
                    case "living room" -> {
                        livingRoom();
                        hallwayMenu = false;
                    }
                    default -> System.out.println("Invalid choice\n");
                }
            }

        } else {
            System.out.println("You can't go there right now\n");
        }
    }


    private void office() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("\nYou are now in the office");
            currentLocation = OFFICE;
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Office menu
        boolean officeMenu = true;

        while (officeMenu) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Take a seat in the office chair\n2. Look inside drawer\n3. Use the phone to call someone\n4. Go back to living room");

            String inputOffice = scanner.nextLine().toLowerCase();
            switch (inputOffice) {
                case "take a seat in the office chair" -> System.out.println("*Takes a seat*. Feels cozy to sit in it\n");
                case "look inside drawer" -> System.out.println("There's nothing useful here. Only pens and empty sheets of paper\n");
                case "use the phone to call someone" -> {
                    if (burglar.isConscious()) {
                        System.out.println("Who would pick up the phone at this late hour?\n");

                    } else {
                        printEndMessage();
                        officeMenu = false;
                        running = false;
                    }
                }
                case "living room" -> {
                    livingRoom();
                    officeMenu = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }


    private void bedroom() {
        if (currentLocation.equals(LIVING_ROOM)) {
            currentLocation = BEDROOM;
            System.out.println("\nYou are now in the bedroom");
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Bedroom menu
        boolean bedroomMenu = true;

        while (bedroomMenu) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Sit on bed\n2. Go back to living room");

            String inputBedroom = scanner.nextLine().toLowerCase();
            switch (inputBedroom) {
                case "sit on bed" -> System.out.println("*Sits on bed*\n");
                case "living room" -> {
                    livingRoom();
                    bedroomMenu = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }


    private void kitchen() {
        if (currentLocation.equals(LIVING_ROOM)) {
            System.out.println("\nYou are now in the kitchen");
            currentLocation = KITCHEN;
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Kitchen menu
        boolean kitchenMenu = true;

        while (kitchenMenu) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Eat leftovers\n2. Pick up frying pan\n3. Go back to living room");

            String inputKitchen = scanner.nextLine().toLowerCase();

            switch (inputKitchen) {
                case "eat leftovers" -> System.out.println("*Eating*. Kinda tasty not gonna lie\n");

                case "pick up frying pan" -> {
                    if (fryingPanFound) {
                        System.out.println("\nYou picked up a frying pan!");
                        System.out.println("A great tool for cooking and a great weapon in times of need.");

                        int previousDamage = resident.getDamage();
                        resident.setDamage(25);
                        System.out.println("Resident damage increased from " + previousDamage + " to " + resident.getDamage() + "\n");
                        fryingPanFound = false;
                    } else {
                        System.out.println("You've already picked up the frying pan\n");
                    }
                }
                case "living room" -> {
                    livingRoom();
                    kitchenMenu = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }
}

