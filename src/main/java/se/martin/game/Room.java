package se.martin.game;

import static se.martin.game.Game.*;

public class Room {

    private static final String LIVINGROOM = "livingRoom";
    private static final String BEDROOM = "bedroom";
    private static final String KITCHEN = "kitchen";
    private static final String HALLWAY = "hallway";
    private static final String OFFICE = "office";
    private static final String START = "start";
    private static String currentLocation = START;

    private static boolean fryingPanFound = true;


    public static void livingRoom() {
        if (!currentLocation.equals(LIVINGROOM)) {
            currentLocation = LIVINGROOM;
            System.out.println("\nYou are now in the living room");
            printMeny();

        } else {
            System.out.println("You can't go there right now\n");
        }
    }


    public static void hallwayFight(){
        Room.currentLocation = HALLWAY;

        if (burglar.isConscious()) {
            System.out.println("You have spotted the source of the strange noises. A burglar is standing in the hallway!");

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
                        Room.hallway();
                    }

                } else if (choice.equals("no")) {
                    System.out.println("\nYou walk back to the living room slowly");
                    fighting = false;
                    Room.livingRoom();

                } else {
                    System.out.println("Invalid choice\n");
                }
            }

        } else {
            Room.hallway();
        }
    }


    public static void hallway() {
        if (currentLocation.equals(LIVINGROOM)) {
            System.out.println("\nYou are now in the hallway");
            currentLocation = HALLWAY;

        } else if (currentLocation.equals(HALLWAY)) {

            //Hallway meny. Appears when burglar is unconscious
            boolean hallwayMeny = true;

            while (hallwayMeny) {
                System.out.println("What do you want to do?\n");
                System.out.println("1. Examine front door\n2. Go back to living room");

                String inputHallway = scanner.nextLine().toLowerCase();
                switch (inputHallway) {
                    case "examine front door" -> {
                        System.out.println("The front door is not damaged at all.");
                        System.out.println("The burglar must've used a fake key to get in.\n");
                    }
                    case "living room" -> {
                        Room.livingRoom();
                        hallwayMeny = false;
                    }
                    default -> System.out.println("Invalid choice\n");
                }
            }

        } else {
            System.out.println("You can't go there right now\n");
        }
    }


    public static void office() {
        if (currentLocation.equals(LIVINGROOM)) {
            System.out.println("\nYou are now in the office");
            currentLocation = OFFICE;
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Office meny
        boolean officeMeny = true;

        while (officeMeny) {
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
                        officeMeny = false;
                        running = false;
                    }
                }
                case "living room" -> {
                    Room.livingRoom();
                    officeMeny = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }


    public static void bedroom() {
        if (currentLocation.equals(LIVINGROOM)) {
            currentLocation = BEDROOM;
            System.out.println("\nYou are now in the bedroom");
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Bedroom meny
        boolean bedroomMeny = true;

        while (bedroomMeny) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Sit on bed\n2. Go back to living room");

            String inputBedroom = scanner.nextLine().toLowerCase();
            switch (inputBedroom) {
                case "sit on bed" -> System.out.println("*Sits on bed*");
                case "living room" -> {
                    Room.livingRoom();
                    bedroomMeny = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }


    public static void kitchen() {
        if (currentLocation.equals(LIVINGROOM)) {
            System.out.println("\nYou are now in the kitchen");
            currentLocation = KITCHEN;
        } else {
            System.out.println("You can't go there right now\n");
        }

        //Kitchen meny
        boolean kitchenMeny = true;

        while (kitchenMeny) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Eat leftovers\n2. Pick up frying pan\n3. Go back to living room");

            String inputKitchen = scanner.nextLine().toLowerCase();

            switch (inputKitchen) {
                case "eat leftovers" -> System.out.println("*Eating*. Kinda tasty not gonna lie\n");

                case "pick up frying pan" -> {
                    if (fryingPanFound) {
                    System.out.println("\nYou picked up a frying pan!");
                    System.out.println("A great tool for cooking and a great weapon in times of need.");
                    System.out.println("Resident damage increased from " + resident.getDamage() + " to 25\n");
                    resident.setDamage(25);
                    fryingPanFound = false;
                    } else {
                        System.out.println("You've already picked up the frying pan\n");
                    }
                }
                case "living room" -> {
                    Room.livingRoom();
                    kitchenMeny = false;
                }
                default -> System.out.println("Invalid choice\n");
            }
        }
    }
}