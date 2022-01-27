package Games.Minesweeper;

import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {

    public Minesweeper() {
        initUI();
    }

    private void initUI() {

        JLabel statusBar = new JLabel("");
        add(statusBar, BorderLayout.SOUTH);

        add(new BoardMS(statusBar));

        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Minesweeper ex = new Minesweeper();
            ex.setVisible(true);
        });
    }
}