package Games.Battleship;

public class Ship {
    private String name;
    private int size;
    private boolean vertical;
    private boolean sunk;
    private String coordinate;

    public Ship(String name, boolean vertical) {
        setName(name);
        this.vertical = vertical;
        this.sunk = false;
        this.coordinate = "";
    }

    //Getters and Setters
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;

        if (name.equalsIgnoreCase("Carrier")) {
            setSize(5);
        }
        else if (name.equalsIgnoreCase("Battleship")) {
            setSize(4);
        }
        else if (name.equalsIgnoreCase("Destroyer")) {
            setSize(3);
        }
        else if (name.equalsIgnoreCase("Submarine")) {
            setSize(3);
        }
        else if (name.equalsIgnoreCase("Patrol Boat")) {
            setSize(2);
        } else {
            throw new IllegalStateException();
        }


    }
    public int getSize() {
        return size;
    }
    private void setSize(int size) {
        this.size = size;
    }
    public boolean isSunk() {
        return sunk;
    }
    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }
    public String getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
    public boolean isVertical() {
        return vertical;
    }
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
