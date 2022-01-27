package Games.BattleshipWithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPlayer2 extends JFrame implements ActionListener{
    final Color MISS = new Color(77, 147, 218, 255);
    final Color OCEAN = new Color(44, 44, 115);
    final Color HIT = new Color(189, 37, 37);
    final Color SHIP = new Color(0, 128, 0);
    final String[] SHIP_NAMES = {"Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
    public JButton[][] buttonGrid = new JButton[10][10];
    boolean horizontal = false;
    boolean vertical = false;
    char currentShip = ' ';
    public JPanel playerOneGridPanel;
    public JPanel playerTwoGridPanel;
    static ArrayList<String[]> p1SolutionGrid = new ArrayList<>();
    static ArrayList<String[]> p2SolutionGrid = new ArrayList<>();
    static String[][] shipCoords = new String[10][10];
    Timer timer;
    int numShipsPlaced = 0;
    int cLeft = 5, bLeft = 4, dLeft = 3, sLeft = 3, pLeft = 2;
    Socket s = new Socket("localhost",1234);
    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());


    public static void main(String[] args) {
        try {
            new BoardPlayer2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BoardPlayer2() throws IOException {
        createUI();
    }

    /**
     * Self-explanatory
     */
    private void createUI() {
        JFrame window = new JFrame();
        window.setSize(1155,625);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.WHITE);
        window.setLayout(null);

        playerOneGridPanel = new JPanel();
        playerOneGridPanel.setBounds(0,0,520,500);
        playerOneGridPanel.setBackground(Color.GRAY);
        playerOneGridPanel.setLayout(new GridLayout(11,11));
        playerOneGridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        fillGrid(playerOneGridPanel);

        window.add(playerOneGridPanel);


        playerTwoGridPanel = new JPanel();
        playerTwoGridPanel.setBounds(620,0,520,500);
        playerTwoGridPanel.setBackground(Color.GRAY);
        playerTwoGridPanel.setLayout(new GridLayout(11,11));
        playerTwoGridPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        fillGrid(playerTwoGridPanel);

        window.add(playerTwoGridPanel);


        JPanel shipPanel = createShipPanel();

        window.add(shipPanel);
        setTimer();


        window.setVisible(true);

    }

    public void setTimer() {
        timer = new Timer(1000, e -> {
            System.out.println("timer went off");
            if (numShipsPlaced == 5) {
                try {
                    oos.writeObject(p1SolutionGrid);
                    p2SolutionGrid = (ArrayList<String[]>) ois.readObject();

                    timer.stop();
                    for (String[] S : p2SolutionGrid) {
                        System.out.println(Arrays.toString(S));
                    }
                    System.out.println(Arrays.deepToString(shipCoords));
                    setSolutionGrid(playerTwoGridPanel,p2SolutionGrid);
                    for (int i = 48; i <= 471; i += 47) {
                        for (int j = 47; j <= 452; j += 45) {
                            playerOneGridPanel.getComponentAt(i,j).setBackground(OCEAN);
                        }
                    }



                    oos.close();
                    ois.close();
                    s.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        timer.start();
    }

    /**
     * Creates a {@link JPanel} of 5 {@link JToggleButton}s to select which ship the user will place
     * @return a {@link JPanel}
     */
    private JPanel createShipPanel() {
        JPanel shipPanel = new JPanel();
        shipPanel.setBounds(15,510,490,50);
        shipPanel.setBackground(null);
        shipPanel.setLayout(new BoxLayout(shipPanel,BoxLayout.PAGE_AXIS));
        shipPanel.setBorder(BorderFactory.createTitledBorder("Ships"));

        JPanel shipsTogglePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        shipPanel.add(shipsTogglePanel);

        ButtonGroup shipsBG = new ButtonGroup();
        JToggleButton[] shipsButton = new JToggleButton[5];
        for (int i = 0; i < 5; i++) {
            shipsButton[i] = new JToggleButton(SHIP_NAMES[i]);
            shipsButton[i].addActionListener(this);
            shipsButton[i].setActionCommand("SHIP");
            shipsBG.add(shipsButton[i]);
            shipsTogglePanel.add(shipsButton[i]);
        }
        return shipPanel;
    }

    /**
     * Fills an empty 11x11 {@link JPanel} set to a {@link GridLayout} with {@link JButton}s
     * @param panel A player's {@link JPanel}
     */
    private void fillGrid(JPanel panel) {
        panel.add(new JButton("_"));
        for (int i = 1; i <= 10; i++) {
            panel.add(new JButton(String.valueOf(i)));
        }
        int i = 0;
        for (char c = 'A'; c <= 'J'; c++) {
            panel.add(new JButton(String.valueOf(c)));
            addOcean(panel,i);
            i++;
        }
        addOceanToGrid();
    }

    /**
     * Adds {@link JButton}s with their background set to OCEAN and adds an {@link ActionListener} with the ActionCommand set to "OCEAN" to the given panel
     * @param panel A player's {@link JPanel}
     */
    private void addOcean(JPanel panel, int row) {
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton();
            button.setBackground(OCEAN);
            button.addActionListener(this);
            button.setActionCommand("OCEAN");
            panel.add(button);
            shipCoords[i][row] = "OCEAN";
        }
    }

    private void addOceanToGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton button = new JButton();
                button.setBackground(OCEAN);
                button.addActionListener(this);
                button.setActionCommand("OCEAN");
                buttonGrid[i][j] = button;
            }
        }
    }

    private void setSolutionGrid(JPanel panel, ArrayList<String[]> shipCoords) {
        for (String[] S : shipCoords) {
            panel.getComponentAt(Integer.parseInt(S[1]),Integer.parseInt(S[2])).setBackground(SHIP);
            panel.getComponentAt(Integer.parseInt(S[1]),Integer.parseInt(S[2])).setName(S[0]);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String[] options = {"Vertical", "Horizontal"};
        char[] shipsArray = {'C', 'B', 'D', 'S', 'P'};

        switch (action) {
            case "OCEAN" -> {
                JButton jButton = (JButton) e.getSource();

//                jButton.setBackground(MISS);
                int x = jButton.getX(); //starts at 48 then +47 up to 471
                int y = jButton.getY(); //starts at 47 the +45 up to 452
                if (currentShip == 'C') {
                    if (vertical) {
                        if (
                                y + (45 * 4) <= 452 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + 45).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 2)).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 3)).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 4)).getBackground() != SHIP
                        ) {
                            for (int i = 0; i < 5; i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x, y+(45*i)).setName("Carrier");
                                temp[0] = "Carrier";
                                temp[1] = String.valueOf(x);
                                temp[2] = String.valueOf(y+(45*i));
                                p1SolutionGrid.add(temp);
                                int newX = (x/47) - 1;
                                int newY = ((y+(45*i))/45) - 1;
                                shipCoords[newX][newY] = "CARRIER";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }

                    } else if (horizontal) {
                        if (
                                x + (47 * 4) <= 471 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + 47, y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 2), y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 3), y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 4), y).getBackground() != SHIP
                        ) {
//                            jButton.setBackground(SHIP);
                            for (int i = 0;i<5;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setName("Carrier");
                                temp[0] = "Carrier";
                                temp[1] = String.valueOf(x+(47*i));
                                temp[2]= String.valueOf(y);
                                p1SolutionGrid.add(temp);
                                int newX = ((x+(47*i))/47) - 1;
                                int newY = (y/45) - 1;
                                shipCoords[newX][newY] = "CARRIER";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }

                    }
                }
                else if (currentShip == 'B') {
                    if (vertical) {
                        if (
                                y + (45 * 3) <= 452 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + 45).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 2)).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 3)).getBackground() != SHIP
                        ) {
                            for (int i = 0; i < 4; i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setName("Battleship");
                                temp[0] = "Battleship";
                                temp[1] = String.valueOf(x);
                                temp[2]= String.valueOf(y+(45*i));
                                p1SolutionGrid.add(temp);
                                int newX = (x/47) - 1;
                                int newY = ((y+(45*i))/45) - 1;
                                shipCoords[newX][newY] = "BATTLESHIP";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    } else if (horizontal) {
                        if (
                                x + (47 * 3) <= 471 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + 47, y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 2), y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 3), y).getBackground() != SHIP
                        ) {
                            for (int i = 0;i<4;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setName("Battleship");
                                temp[0] = "Battleship";
                                temp[1] = String.valueOf(x+(47*i));
                                temp[2]= String.valueOf(y);
                                p1SolutionGrid.add(temp);
                                int newX = ((x+(47*i))/47) - 1;
                                int newY = (y/45) - 1;
                                shipCoords[newX][newY] = "BATTLESHIP";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    }
                }
                else if (currentShip == 'D') {
                    if (vertical) {
                        if (
                                y + (45 * 2) <= 452 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + 45).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 2)).getBackground() != SHIP
                        ) {
                            for (int i=0;i<3;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setName("Destroyer");
                                temp[0] = "Destroyer";
                                temp[1] = String.valueOf(x);
                                temp[2]= String.valueOf(y+(45*i));
                                p1SolutionGrid.add(temp);
                                int newX = (x/47) - 1;
                                int newY = ((y+(45*i))/45) - 1;
                                shipCoords[newX][newY] = "DESTROYER";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    } else if (horizontal) {
                        if (
                                x + (47 * 2) <= 471 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + 47, y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 2), y).getBackground() != SHIP
                        ) {
                            for (int i=0;i<3;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setName("Destroyer");
                                temp[0] = "Destroyer";
                                temp[1] = String.valueOf(x+(47*i));
                                temp[2]= String.valueOf(y);
                                p1SolutionGrid.add(temp);
                                int newX = ((x+(47*i))/47) - 1;
                                int newY = (y/45) - 1;
                                shipCoords[newX][newY] = "DESTROYER";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    }
                }
                else if (currentShip == 'S') {
                    if (vertical) {
                        if (
                                y + (45 * 2) <= 452 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + 45).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + (45 * 2)).getBackground() != SHIP
                        ) {
                            for (int i=0;i<3;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setName("Submarine");
                                temp[0] = "Submarine";
                                temp[1] = String.valueOf(x);
                                temp[2]= String.valueOf(y+(45*i));
                                p1SolutionGrid.add(temp);
                                int newX = (x/47) - 1;
                                int newY = ((y+(45*i))/45) - 1;
                                shipCoords[newX][newY] = "SUBMARINE";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    } else if (horizontal) {
                        if (
                                x + (47 * 2) <= 471 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + 47, y).getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + (47 * 2), y).getBackground() != SHIP
                        ) {
                            for (int i=0;i<3;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setName("Submarine");
                                temp[0] = "Submarine";
                                temp[1] = String.valueOf(x+(47*i));
                                temp[2]= String.valueOf(y);
                                p1SolutionGrid.add(temp);
                                int newX = ((x+(47*i))/47) - 1;
                                int newY = (y/45) - 1;
                                shipCoords[newX][newY] = "SUBMARINE";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    }
                }
                else if (currentShip == 'P') {
                    if (vertical) {
                        if (
                                y + 45 <= 452 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x, y + 45).getBackground() != SHIP
                        ) {
                            for (int i=0;i<2;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x,y+(45*i)).setName("Patrol Boat");
                                temp[0] = "Patrol Boat";
                                temp[1] = String.valueOf(x);
                                temp[2]= String.valueOf(y+(45*i));
                                p1SolutionGrid.add(temp);
                                int newX = (x/47) - 1;
                                int newY = ((y+(45*i))/45) - 1;
                                shipCoords[newX][newY] = "PATROL_BOAT";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    } else if (horizontal) {
                        if (
                                x + 47 <= 471 &&
                                        jButton.getBackground() != SHIP &&
                                        playerOneGridPanel.getComponentAt(x + 47, y).getBackground() != SHIP
                        ) {
                            for (int i=0;i<2;i++) {
                                String[] temp = new String[3];
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setBackground(SHIP);
                                playerOneGridPanel.getComponentAt(x+(47*i),y).setName("Patrol Boat");
                                temp[0] = "Patrol Boat";
                                temp[1] = String.valueOf(x+(47*i));
                                temp[2]= String.valueOf(y);
                                p1SolutionGrid.add(temp);
                                int newX = ((x+(47*i))/47) - 1;
                                int newY = (y/45) - 1;
                                shipCoords[newX][newY] = "PATROL_BOAT";
                            }
                            currentShip = ' ';
                            numShipsPlaced++;
                        }
                    }
                }
                else if (currentShip == ' ' && numShipsPlaced < 5) {
                    JOptionPane.showMessageDialog(null,"Select a ship first","Warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    for (String[] S : p2SolutionGrid) {
                        if (x == Integer.parseInt(S[1]) && y == Integer.parseInt(S[2])) {
                            playerOneGridPanel.getComponentAt(x,y).setBackground(HIT);
                            if (S[0].equals(SHIP_NAMES[0])) {
                                cLeft--;
                                if (cLeft == 0) {
                                    System.out.println("You sunk your opponent's Carrier");
                                }
                            }
                            else if (S[0].equals(SHIP_NAMES[1])) {
                                bLeft--;
                                if (bLeft == 0) {
                                    System.out.println("You sunk your opponent's Battleship");
                                }
                            }
                            else if (S[0].equals(SHIP_NAMES[2])) {
                                dLeft--;
                                if (dLeft == 0) {
                                    System.out.println("You sunk your opponent's Destroyer");
                                }
                            }
                            else if (S[0].equals(SHIP_NAMES[3])) {
                                sLeft--;
                                if (sLeft == 0) {
                                    System.out.println("You sunk your opponent's Submarine");
                                }
                            }
                            else if (S[0].equals(SHIP_NAMES[4])) {
                                pLeft--;
                                if (pLeft == 0) {
                                    System.out.println("You sunk your opponent's Patrol Boat");
                                }
                            }
                        } else {
                            playerOneGridPanel.getComponentAt(x,y).setBackground(MISS);
                        }
                    }
                }
            }
            case "SHIP" -> {
                JToggleButton jToggleButton = (JToggleButton) e.getSource();
                if (jToggleButton.getText().equalsIgnoreCase("Carrier")) {
                    VorH(options);
                    currentShip = shipsArray[0];
                    jToggleButton.setEnabled(false);
                }
                else if (jToggleButton.getText().equalsIgnoreCase("Battleship")) {
                    VorH(options);
                    currentShip = shipsArray[1];
                    jToggleButton.setEnabled(false);
                }
                else if (jToggleButton.getText().equalsIgnoreCase("Destroyer")) {
                    VorH(options);
                    currentShip = shipsArray[2];
                    jToggleButton.setEnabled(false);
                }
                else if (jToggleButton.getText().equalsIgnoreCase("Submarine")) {
                    VorH(options);
                    currentShip = shipsArray[3];
                    jToggleButton.setEnabled(false);
                }
                else if (jToggleButton.getText().equalsIgnoreCase("Patrol Boat")) {
                    VorH(options);
                    currentShip = shipsArray[4];
                    jToggleButton.setEnabled(false);
                }
            }
        }
    }

    private void VorH(String[] options) {
        int n = JOptionPane.showOptionDialog(new JFrame(),"Do you want this ship to be placed horizontally or vertically?","Placement",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n == 0) {
            horizontal = false;
            vertical = true;
        } else {
            vertical = false;
            horizontal = true;
        }
    }
}
