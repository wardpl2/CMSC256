package Games.Battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    State[][] playField;
    ArrayList<Ship> ships;

    public Grid() {
        ships = new ArrayList<>();
        playField = new State[10][10];
        for (State[] row : playField) {
            Arrays.fill(row, State.OCEAN);
        }
    }

    public boolean addShip(Ship ship, String coordinate) {
        //C4 || D10 || F8
        int row = getRow(coordinate.charAt(0));
        int col = Integer.parseInt(coordinate.substring(1)) - 1;

        //check if the ship can fit on the grid
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return false;
        }
        else if (!ship.isVertical() && col + ship.getSize() > 10) {
            return false;
        }
        else if (ship.isVertical() && row + ship.getSize() > 10) {
            return false;
        }

        if (ship.isVertical()) {
            for (int i = row; i < row + ship.getSize(); i++) {
                if (playField[i][col] != State.OCEAN) {
                    return false;
                }
            }
            for (int i = row; i < row + ship.getSize(); i++) {
                playField[i][col] = State.SHIP;
            }
        } else {
            for (int i = col; i < col + ship.getSize(); i++) {
                if (playField[row][i] != State.OCEAN) {
                    return false;
                }
            }
            for (int i = col; i < col + ship.getSize(); i++) {
                playField[row][i] = State.SHIP;
            }
        }

        ship.setCoordinate(coordinate);
        ships.add(ship);
        return true;
    }

    public State fire(String coordinate) {
        int row = getRow(coordinate.charAt(0));
        int col = Integer.parseInt(coordinate.substring(1)) - 1;

        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return State.MISS;
        }

        if (playField[row][col] == State.SHIP) {
            playField[row][col] = State.HIT;
            return State.SHIP;
        } else if (playField[row][col] == State.HIT) {
            return State.HIT;
        } else {
            playField[row][col] = State.MISS;
            return State.OCEAN;
        }
    }

    public static int getRow(char row) {
        int toReturn = -1;
        if (row == 'A') {
            toReturn = 0;
        } else if (row == 'B') {
            toReturn = 1;
        } else if (row == 'C') {
            toReturn = 2;
        } else if (row == 'D') {
            toReturn = 3;
        } else if (row == 'E') {
            toReturn = 4;
        } else if (row == 'F') {
            toReturn = 5;
        } else if (row == 'G') {
            toReturn = 6;
        } else if (row == 'H') {
            toReturn = 7;
        } else if (row == 'I') {
            toReturn = 8;
        } else if (row == 'J') {
            toReturn = 9;
        }
        return toReturn;
    }

    public String didShipSink() {
        String sunkShip = "";
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                int row = getRow(ship.getCoordinate().charAt(0));
                int col = Integer.parseInt(ship.getCoordinate().substring(1)) - 1;
                boolean isSunk = true;

                if (ship.isVertical()) {
                    for (int i = row; i < row + ship.getSize(); i++) {
                        if (playField[i][col] != State.HIT) {
                            isSunk = false;
                        }
                    }
                } else {
                    for (int i = col; i < col + ship.getSize(); i++) {
                        if (playField[row][i] != State.HIT) {
                            isSunk = false;
                        }
                    }
                }

                if (isSunk) {
                    ship.setSunk(true);
                    sunkShip = ship.getName();
                }
            }
        }
        return sunkShip;
    }

    public boolean areShipsSunk() {
        boolean allSunk = true;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                allSunk = false;
            }
        }
        return allSunk;
    }

    public void printField(boolean showShips) {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        char row = 'A';
        for (int i = 0; i < playField.length; i++) {
            System.out.print(row + "  ");
            for (int j = 0; j < playField[i].length; j++) {
                if (playField[i][j] == State.OCEAN) {
                    System.out.print("- ");
                } else if (playField[i][j] == State.MISS) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
            row++;
        }
        System.out.println();
    }
}
