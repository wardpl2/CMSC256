package Games.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    public JPanel grid;
    public boolean xTurn = true;
    public int turns = 0;
    public int size = 400;
    boolean result;
    String winner;

    public TicTacToe() {
        createUI();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    private void createUI() {
        JFrame window = new JFrame();
        window.setSize(size,size);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.WHITE);
        window.setLayout(null);

        grid = new JPanel();
        grid.setBounds(0,0,size - 15,size - 30);
        grid.setBackground(Color.gray);
        grid.setLayout(new GridLayout(3,3));
        grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.addActionListener(this);
            grid.add(button);
        }
        window.add(grid);

        window.setVisible(true);
    }


    private Pair<Boolean, String> checkWinner(JPanel grid) {
        for (int i = 0; i < 3; i++) {
            if ((!((JButton)grid.getComponent(i)).getText().equals("")) &&
                    ((JButton)grid.getComponent(i)).getText().equals(((JButton)grid.getComponent(i + 1)).getText()) &&
                    ((JButton)grid.getComponent(i + 1)).getText().equals(((JButton)grid.getComponent(i + 2)).getText())) {
                return new Pair<>(true, ((JButton)grid.getComponent(i)).getText());
            }
            if ((!((JButton)grid.getComponent(i)).getText().equals("")) &&
                    ((JButton)grid.getComponent(i)).getText().equals(((JButton)grid.getComponent(i + 3)).getText()) &&
                    ((JButton)grid.getComponent(i + 3)).getText().equals(((JButton)grid.getComponent(i + 6)).getText())) {
                return new Pair<>(true, ((JButton)grid.getComponent(i)).getText());
            }
        }
        if ((!((JButton)grid.getComponent(4)).getText().equals("")) &&
                ((JButton)grid.getComponent(0)).getText().equals(((JButton)grid.getComponent(4)).getText()) &&
                ((JButton)grid.getComponent(4)).getText().equals(((JButton)grid.getComponent(8)).getText())) {
            return new Pair<>(true, ((JButton) grid.getComponent(4)).getText());
        }
        if ((!((JButton)grid.getComponent(4)).getText().equals("")) &&
                ((JButton)grid.getComponent(2)).getText().equals(((JButton)grid.getComponent(4)).getText()) &&
                ((JButton)grid.getComponent(4)).getText().equals(((JButton)grid.getComponent(6)).getText())) {
            return new Pair<>(true, ((JButton)grid.getComponent(4)).getText());
        }

        return new Pair<>(false,"");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (!(button.getText().equals("X") || button.getText().equals("O"))) {
            if (xTurn) {
                button.setText("X");
                xTurn = false;
            } else {
                button.setText("O");
                xTurn = true;
            }
            result = checkWinner(grid).getKey();
            winner = checkWinner(grid).getValue();
            turns++;
        }
        System.out.println(result + " " + winner);
    }


    public class Pair<T, T1> {
        private T key;
        private T1 value;

        public Pair(T key, T1 value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public T1 getValue() {
            return value;
        }
    }
}