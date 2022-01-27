package Games.Battleship;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        Grid computerGrid = new Grid();
        Grid playerGrid = new Grid();
        computerAddShips(computerGrid);
        addShips(playerGrid);

        //Play the game
        boolean compGameOver = false;
        boolean playerGameOver = false;
        while (!compGameOver && !playerGameOver) {
            computerFire(playerGrid);

            compGameOver = playerGrid.areShipsSunk();
            if (!compGameOver) {
                System.out.println("Human field");
                playerGrid.printField(true);
                System.out.println("Computer field");
                computerGrid.printField(false);

                fire(computerGrid);
                playerGameOver = computerGrid.areShipsSunk();
            }
        }
        if (compGameOver) {
            System.out.println("Computer Wins!");
        } else {
            System.out.println("You Win!");
        }

        in.close();
    }

    public static void addShips(Grid grid) {
        System.out.println("Please enter a grid coordinate row column. Example: C4");
        System.out.println("Please enter H for horizontal orientation and V for vertical orientation");
        System.out.println("Please separate a grid coordinate from an orientation using a space");

        String[] printOuts = { "Please specify a coordinate and orientation to place your Carrier at",
                "Please specify a coordinate and orientation to place your Battleship at",
                "Please specify a coordinate and orientation to place your Cruiser at",
                "Please specify a coordinate and orientation to place your Submarine at",
                "Please specify a coordinate and orientation to place your Destroyer at" };

        String[] shipNames = {"Carrier","Battleship","Destroyer","Submarine","Patrol Boat"};

        for (int i = 0; i < printOuts.length; i++) {
            boolean shipAdded;

            do {
                grid.printField(true);
                System.out.println(printOuts[i]);
                String[] shipParts = in.nextLine().split(" ");
                shipAdded = grid.addShip(new Ship(shipNames[i], (shipParts[1].charAt(0) == 'V')), shipParts[0]);
                if(!shipAdded) {
                    System.out.println("Ship doesn't fit there, please try again");
                }
            } while(!shipAdded);
        }
    }

    public static void computerAddShips(Grid grid) {
        String[] shipNames = {"Carrier","Battleship","Destroyer","Submarine","Patrol Boat"};
        String[] coordinates = {
                "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10",
                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
                "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
                "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10",
                "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10",
                "J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"
        };
        char[] orientation = { 'H', 'V' };
        for (String shipName : shipNames) {
            boolean shipAdded;
            do {
                Random r = new Random();
                String pos = coordinates[r.nextInt(100)];
                char ori = orientation[r.nextInt(2)];
                shipAdded = grid.addShip(new Ship(shipName, (ori == 'V')), pos);
            } while(!shipAdded);
        }
    }

    public static void fire(Grid grid) {
        System.out.println("Please enter a coordinate to fire at");
        String coordinate = in.nextLine();
        State result = grid.fire(coordinate);

        if (result == State.SHIP) {
            System.out.println("Hit!");
        } else if (result == State.HIT) {
            System.out.println("Already Hit");
        } else {
            System.out.println("Miss");
        }

        String sunkShip = grid.didShipSink();

        if (!sunkShip.equalsIgnoreCase("")) {
            System.out.println("You sunk their "+sunkShip+"!");
        }
    }

    public static void computerFire(Grid grid) {
        String[] coordinates = {
                "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10",
                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
                "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
                "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10",
                "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10",
                "J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"
        };
        boolean alreadyHit = true;
        while (alreadyHit) {
            Random r = new Random();
            String coordinate = coordinates[r.nextInt(100)];
            alreadyHit = (grid.fire(coordinate) == State.HIT);
        }
        grid.didShipSink();
    }
}
