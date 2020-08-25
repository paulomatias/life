package fr.paulo.life;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {
    private final BoardPanel board;

    public BoardFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) size.getHeight();
        int width = (int) size.getWidth();
        setSize(width, height); // Need to setSize() because pack() collapses the empty JFrame
        board = new BoardPanel(width, height);
        add(board);
    }

    public void computeNextGeneration() {
        board.computeNextGeneration();
    }
}
